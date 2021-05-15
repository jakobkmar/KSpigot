package net.axay.kspigot.chat.input.implementations

import net.axay.kspigot.chat.KColors
import net.axay.kspigot.chat.input.PlayerInput
import net.axay.kspigot.chat.input.PlayerInputResult
import net.axay.kspigot.event.listen
import org.bukkit.entity.Player
import org.bukkit.event.EventPriority
import org.bukkit.event.player.AsyncPlayerChatEvent

internal class PlayerInputChat(
    player: Player,
    callback: (PlayerInputResult<String>) -> Unit,
    timeoutSeconds: Int,
    question: String,
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
