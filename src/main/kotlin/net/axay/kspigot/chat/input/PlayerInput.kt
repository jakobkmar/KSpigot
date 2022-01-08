@file:Suppress("MemberVisibilityCanBePrivate", "Unused")

package net.axay.kspigot.chat.input

import net.axay.kspigot.chat.input.implementations.PlayerInputBookComprehensive
import net.axay.kspigot.chat.input.implementations.PlayerInputBookPaged
import net.axay.kspigot.chat.input.implementations.PlayerInputChat
import net.axay.kspigot.event.unregister
import net.axay.kspigot.runnables.sync
import net.axay.kspigot.runnables.taskRunLater
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import org.bukkit.entity.Player
import org.bukkit.event.Listener

/**
 * Asks the player a question and uses the next
 * chat input of the player as his input.
 */
fun Player.awaitChatInput(
    question: Component = text("Type your input in the chat!"),
    timeoutSeconds: Int = 1 * 60,
    callback: (PlayerInputResult<Component>) -> Unit,
) {
    PlayerInputChat(this, callback, timeoutSeconds, question)
}

/**
 * Asks the player a question and uses the next
 * chat input of the player as his input.
 */
fun Player.awaitChatInput(
    question: String = "Type your input in the chat!",
    timeoutSeconds: Int = 1 * 60,
    callback: (PlayerInputResult<Component>) -> Unit,
) {
    awaitChatInput(text(question), timeoutSeconds, callback)
}

/**
 * Opens a book and uses the text the player inserted
 * on all sites as the players' input.
 * In this case, all pages will be flattened to a single string.
 */
fun Player.awaitBookInputAsString(
    timeoutSeconds: Int = 1 * 60,
    callback: (PlayerInputResult<String>) -> Unit,
) = PlayerInputBookComprehensive(this, callback, timeoutSeconds).bookItemStack

/**
 * Opens a book and uses the text the player inserted
 * on all sites as the players' input.
 * In this case, every page is represented by one string
 * element in a list of strings.
 */
fun Player.awaitBookInputAsList(
    timeoutSeconds: Int = 1 * 60,
    callback: (PlayerInputResult<List<Component>>) -> Unit,
) = PlayerInputBookPaged(this, callback, timeoutSeconds).bookItemStack

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
