package net.axay.kspigot.chat.input

import net.axay.kspigot.chat.KColors
import net.axay.kspigot.event.listen
import net.axay.kspigot.event.unregister
import net.axay.kspigot.extensions.bukkit.closeForViewers
import net.axay.kspigot.extensions.bukkit.content
import net.axay.kspigot.items.itemStack
import net.axay.kspigot.items.meta
import net.axay.kspigot.main.KSpigotMainInstance
import net.axay.kspigot.runnables.taskRunLater
import net.wesjd.anvilgui.AnvilGUI
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerEditBookEvent
import org.bukkit.persistence.PersistentDataType

fun Player.awaitChatInput(
    question: String = "Type your input in the chat!",
    timeoutSeconds: Int = 1 * 60,
    callback: (String?) -> Unit
) {
    PlayerInputChat(this, callback, timeoutSeconds, question)
}

fun Player.awaitAnvilInput(
    invTitle: String = "Type your input!",
    startText: String = "RENAME ME",
    renameItemDescription: List<String> = listOf(
        "Rename this item to",
        "submit your input!"
    ),
    timeoutSeconds: Int = 1 * 60,
    callback: (String?) -> Unit
) {
    PlayerInputAnvilInv(this, callback, timeoutSeconds, invTitle, startText, renameItemDescription)
}

fun Player.awaitBookInput(
    timeoutSeconds: Int = 1 * 60,
    callback: (String?) -> Unit
) {
    PlayerInputBook(this, callback, timeoutSeconds)
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
            if (!received) onTimeout()
            onReceive(null)
        }
    }

}

internal class PlayerInputChat(
    player: Player,
    callback: (String?) -> Unit,
    timeoutSeconds: Int,
    question: String
) : PlayerInput(player, callback, timeoutSeconds) {

    init {
        player.sendMessage("${KColors.ORANGERED}$question")
    }

    override val inputListeners = listOf(
        listen<AsyncPlayerChatEvent>(EventPriority.LOWEST) {
            if (it.player == player) {
                it.isCancelled = true
                onReceive(it.message)
            }
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
                return@onComplete if (text.isNotEmpty()) {
                    onReceive(text)
                    AnvilGUI.Response.close()
                } else
                    AnvilGUI.Response.text("Type here...")
            }
            .title("${KColors.ORANGERED}$invTitle")
            .item(
                itemStack(Material.PAPER) {
                    meta {
                        lore = renameItemDescription.map { "${KColors.INDIANRED}$it" }
                    }
                }
            )
            .text("${KColors.ORANGERED}$startText")
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

internal class PlayerInputBook(
    player: Player,
    callback: (String?) -> Unit,
    timeoutSeconds: Int
) : PlayerInput(player, callback, timeoutSeconds) {

    private val id = getID()

    init {

        player.openBook(itemStack(Material.WRITABLE_BOOK) {
            meta {
                persistentDataContainer[idKey, PersistentDataType.INTEGER] = id
            }
        })
    }

    override val inputListeners = listOf(
        listen<PlayerEditBookEvent> {
            val meta = it.newBookMeta
            if (meta.persistentDataContainer[idKey, PersistentDataType.INTEGER] == id) {
                onReceive(meta.content)
                usedIDs -= id
            }
        }
    )

    override fun onTimeout() {
        player.closeInventory()
        usedIDs -= id
    }

    companion object {

        val idKey = NamespacedKey(KSpigotMainInstance, "kspigot_bookinput_id")

        internal val usedIDs = ArrayList<Int>()
        fun getID(): Int {
            var returnID = (0..Int.MAX_VALUE).random()
            while (usedIDs.contains(returnID)) returnID = (0..Int.MAX_VALUE).random()
            return returnID
        }

    }

}