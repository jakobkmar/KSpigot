package net.axay.kspigot.chat.input

import net.axay.kspigot.chat.KColors
import net.axay.kspigot.event.listen
import net.axay.kspigot.event.unregister
import net.axay.kspigot.extensions.bukkit.closeForViewers
import net.axay.kspigot.items.itemStack
import net.axay.kspigot.items.meta
import net.axay.kspigot.items.name
import net.axay.kspigot.main.KSpigotMainInstance
import net.axay.kspigot.runnables.taskRunLater
import net.wesjd.anvilgui.AnvilGUI
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.AsyncPlayerChatEvent

fun Player.awaitChatInput(timeoutSeconds: Int = 1 * 60, callback: (String?) -> Unit) {
    PlayerInputChat(this, callback, timeoutSeconds)
}

fun Player.awaitAnvilInput(
    timeoutSeconds: Int = 1 * 60,
    invTitle: String = "${KColors.ORANGERED}Type your input!",
    startText: String = "${KColors.ORANGERED}RENAME ME",
    renameItemDescription: List<String> = listOf(
        "${KColors.INDIANRED}Rename this item to",
        "${KColors.INDIANRED}submit your input!"
    ),
    callback: (String?) -> Unit
) {
    PlayerInputAnvilInv(this, callback, timeoutSeconds, invTitle, startText, renameItemDescription)
}

internal abstract class PlayerInput(
    protected val player: Player,
    private val callback: (String?) -> Unit,
    timeoutSeconds: Int
) {

    private var received = false

    protected abstract val inputListeners: List<Listener>

    protected fun onReceive(input: String?) {
        if (!received) {
            inputListeners.forEach { it.unregister() }
            received = true
            callback.invoke(input)
        }
    }

    open fun onTimeout() {}

    init {
        taskRunLater(delay = (20 * timeoutSeconds).toLong()) {
            onReceive(null)
        }
    }

}

internal class PlayerInputChat(
    player: Player,
    callback: (String?) -> Unit,
    timeoutSeconds: Int
) : PlayerInput(player, callback, timeoutSeconds) {

    override val inputListeners = listOf(
        listen<AsyncPlayerChatEvent> {
            it.isCancelled = true
            onReceive(it.message)
        }
    )

}

internal class PlayerInputAnvilInv(
    player: Player,
    callback: (String?) -> Unit,
    timeoutSeconds: Int,
    invTitle: String,
    startText: String,
    renameItemDescription: List<String>
) : PlayerInput(player, callback, timeoutSeconds) {

    private val anvilInv =
        AnvilGUI.Builder().plugin(KSpigotMainInstance)
            .onClose { onReceive(null) }
            .onComplete { _, text ->
                if (text.isNotEmpty()) {
                    onReceive(text)
                    AnvilGUI.Response.close()
                } else
                    AnvilGUI.Response.text("Type here...")
            }
            .title(invTitle)
            .item(
                itemStack(Material.PAPER) {
                    meta {
                        lore = renameItemDescription
                    }
                }
            )
            .text(startText)
            .open(player)

    override val inputListeners = listOf(
        listen<InventoryClickEvent> {
            if (it.clickedInventory == anvilInv.inventory)
                it.isCancelled = true
        }
    )

    override fun onTimeout() {
        anvilInv.inventory.closeForViewers()
    }

}