package net.axay.kspigot.commands

import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType
import net.axay.kspigot.commands.internal.ServerCommandSource
import net.minecraft.network.chat.ChatMessage
import org.bukkit.Location
import org.bukkit.Server
import org.bukkit.World
import org.bukkit.entity.Player

class CommandContext(val nmsContext: CommandContext<ServerCommandSource>) {
    companion object {
        private val REQUIRES_PLAYER_EXCEPTION = SimpleCommandExceptionType(ChatMessage("permissions.requires.player"))
    }

    /**
     * Get the value of the given argument.
     */
    inline fun <reified T> getArgument(name: String): T =
        nmsContext.getArgument(name, T::class.java)

    /**
     * The source / sender which executed this command.
     */
    val sender get() = nmsContext.source.bukkitSender

    /**
     * Validates that this command was executes by a player (sends an error message to the sender if this is not the case)
     * and returns the [Player].
     */
    val player get() = nmsContext.source.bukkitSender as? Player ?: throw REQUIRES_PLAYER_EXCEPTION.create()

    /**
     * The world where the source of this command currently is in.
     *
     * Please note: this could be null and therefore throw an exception if called, but
     * these cases are rare (e.g. command called by a datapack function), therefore
     * this property is not nullable for convenience
     */
    val world get() = nmsContext.source.world.world as World

    /**
     * The position of the source of this command.
     */
    val position get() = with(nmsContext.source.position) {
        Location(nmsContext.source.world.world as? World?, x, y, z)
    }

    /**
     * The current server instance.
     */
    val server get() = nmsContext.source.server.server as Server
}
