package net.axay.kspigot.commands

import com.mojang.brigadier.context.CommandContext
import net.minecraft.commands.CommandSourceStack
import org.bukkit.Location
import org.bukkit.Server
import org.bukkit.World
import org.bukkit.entity.Player

class CommandContext(val nmsContext: CommandContext<CommandSourceStack>) {
    /**
     * Get the value of the given argument.
     */
    inline fun <reified T> getArgument(name: String): T =
        nmsContext.getArgument(name, T::class.java)

    /**
     * The source / sender which executed this command.
     */
    val sender: CommandSourceStack get() = nmsContext.source

    /**
     * Validates that this command was executes by a player (sends an error message to the sender if this is not the case)
     * and returns the [Player].
     */
    val player: Player get() = nmsContext.source.bukkitSender as? Player ?: throw CommandSourceStack.ERROR_NOT_PLAYER.create()

    /**
     * The world where the source of this command currently is in.
     *
     * Please note: this could be null and therefore throw an exception if called, but
     * these cases are rare (e.g. command called by a datapack function), therefore
     * this property is not nullable for convenience
     */
    val world: World get() = nmsContext.source.bukkitWorld!!

    /**
     * The position of the source of this command.
     */
    val position: Location get() = with(nmsContext.source.position) {
        Location(nmsContext.source.bukkitWorld, x, y, z)
    }

    /**
     * The current server instance.
     */
    val server: Server get() = nmsContext.source.server.server as Server
}
