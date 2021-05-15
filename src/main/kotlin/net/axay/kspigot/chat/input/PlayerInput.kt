@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.chat.input

import net.axay.kspigot.chat.input.implementations.PlayerInputAnvilInv
import net.axay.kspigot.chat.input.implementations.PlayerInputBookComprehensive
import net.axay.kspigot.chat.input.implementations.PlayerInputBookPaged
import net.axay.kspigot.chat.input.implementations.PlayerInputChat
import net.axay.kspigot.event.unregister
import net.axay.kspigot.runnables.sync
import net.axay.kspigot.runnables.taskRunLater
import org.bukkit.entity.Player
import org.bukkit.event.Listener

/**
 * Asks the player a question and uses the next
 * chat input of the player as his input.
 */
fun Player.awaitChatInput(
    question: String = "Type your input in the chat!",
    timeoutSeconds: Int = 1 * 60,
    callback: (PlayerInputResult<String>) -> Unit,
) {
    PlayerInputChat(this, callback, timeoutSeconds, question)
}

/**
 * Opens an anvil GUI and uses the new name of the
 * renamed item as the players' input.
 */
fun Player.awaitAnvilInput(
    invTitle: String = "Type your input!",
    startText: String = "RENAME ME",
    renameItemDescription: List<String> = listOf(
        "Rename this item to",
        "submit your input!"
    ),
    timeoutSeconds: Int = 1 * 60,
    callback: (PlayerInputResult<String>) -> Unit,
) {
    PlayerInputAnvilInv(this, callback, timeoutSeconds, invTitle, startText, renameItemDescription)
}

/**
 * Opens a book and uses the text the player inserted
 * on all sites as the players' input.
 * In this case, all pages will be flattened to a single string.
 */
fun Player.awaitBookInputAsString(
    timeoutSeconds: Int = 1 * 60,
    callback: (PlayerInputResult<String>) -> Unit,
) {
    PlayerInputBookComprehensive(this, callback, timeoutSeconds)
}

/**
 * Opens a book and uses the text the player inserted
 * on all sites as the players' input.
 * In this case, every page is represented by one string
 * element in a list of strings.
 */
fun Player.awaitBookInputAsList(
    timeoutSeconds: Int = 1 * 60,
    callback: (PlayerInputResult<List<String>>) -> Unit,
) {
    PlayerInputBookPaged(this, callback, timeoutSeconds)
}

/**
 * @param input The input the player gave. Null on timeout or invalid input.
 */
class PlayerInputResult<T> internal constructor(val input: T?)
internal abstract class PlayerInput<T>(
    protected val player: Player,
    private val callback: (PlayerInputResult<T>) -> Unit,
    timeoutSeconds: Int,
) {
    private var received = false

    protected abstract val inputListeners: List<Listener>

    protected fun onReceive(input: T?) {
        if (!received) {
            inputListeners.forEach { it.unregister() }
            received = true
            sync {
                callback.invoke(PlayerInputResult(input))
            }
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
