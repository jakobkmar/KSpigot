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
import org.bukkit.inventory.meta.BookMeta
import org.bukkit.persistence.PersistentDataType

fun Player.awaitChatInput(
    question: String = "Type your input in the chat!",
    timeoutSeconds: Int = 1 * 60,
    callback: (PlayerInputResult<String>) -> Unit
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
    callback: (PlayerInputResult<String>) -> Unit
) {
    PlayerInputAnvilInv(this, callback, timeoutSeconds, invTitle, startText, renameItemDescription)
}

fun Player.awaitBookInput(
    timeoutSeconds: Int = 1 * 60,
    callback: (PlayerInputResult<String>) -> Unit
) {
    PlayerInputBookComprehensive(this, callback, timeoutSeconds)
}

class PlayerInputResult<T> internal constructor(val input: T?)

internal abstract class PlayerInput<T>(
    protected val player: Player,
    private val callback: (PlayerInputResult<T>) -> Unit,
    timeoutSeconds: Int
) {

    private var received = false

    protected abstract val inputListeners: List<Listener>

    protected fun onReceive(input: T?) {
        if (!received) {
            inputListeners.forEach { it.unregister() }
            received = true
            callback.invoke(PlayerInputResult(input))
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
    callback: (PlayerInputResult<String>) -> Unit,
    timeoutSeconds: Int,
    question: String
) : PlayerInput<String>(player, callback, timeoutSeconds) {

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
    callback: (PlayerInputResult<String>) -> Unit,
    timeoutSeconds: Int,
    invTitle: String,
    startText: String,
    renameItemDescription: List<String>
) : PlayerInput<String>(player, callback, timeoutSeconds) {

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

internal class PlayerInputBookComprehensive(
    player: Player,
    callback: (PlayerInputResult<String>) -> Unit,
    timeoutSeconds: Int
) : PlayerInputBook<String>(player, callback, timeoutSeconds) {
    override fun loadBookContent(bookMeta: BookMeta) = bookMeta.content
}

internal class PlayerInputBookPaged(
    player: Player,
    callback: (PlayerInputResult<List<String>>) -> Unit,
    timeoutSeconds: Int
) : PlayerInputBook<List<String>>(player, callback, timeoutSeconds) {
    override fun loadBookContent(bookMeta: BookMeta): List<String> = bookMeta.pages
}

internal abstract class PlayerInputBook<T>(
    player: Player,
    callback: (PlayerInputResult<T>) -> Unit,
    timeoutSeconds: Int
) : PlayerInput<T>(player, callback, timeoutSeconds) {

    private val id = getID()

    init {
        player.openBook(itemStack(Material.WRITABLE_BOOK) {
            meta {
                persistentDataContainer[idKey, PersistentDataType.INTEGER] = id
            }
        })
    }

    abstract fun loadBookContent(bookMeta: BookMeta): T

    override val inputListeners = listOf(
        listen<PlayerEditBookEvent> {
            val meta = it.newBookMeta
            if (meta.persistentDataContainer[idKey, PersistentDataType.INTEGER] == id) {
                onReceive(loadBookContent(meta))
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