package net.axay.kspigot.chat.input.implementations

import io.papermc.paper.event.player.AsyncChatEvent
import net.axay.kspigot.chat.input.PlayerInput
import net.axay.kspigot.chat.input.PlayerInputResult
import net.axay.kspigot.event.listen
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player
import org.bukkit.event.EventPriority

internal class PlayerInputChat(
    player: Player,
    callback: (PlayerInputResult<Component>) -> Unit,
    timeoutSeconds: Int,
    question: Component,
) : PlayerInput<Component>(player, callback, timeoutSeconds) {
    init {
        player.sendMessage(question)
    }

    override val inputListeners = listOf(
        listen<AsyncChatEvent>(EventPriority.LOWEST) {
            if (it.player == player) {
                onReceive(it.message())
                it.isCancelled = true
            }
        }
    )
}
