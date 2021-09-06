package net.axay.kspigot.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.axay.kspigot.annotations.NMS_General
import net.axay.kspigot.commands.internal.BrigardierSupport
import net.minecraft.commands.CommandListenerWrapper

/**
 * Registers this command at the [CommandDispatcher] of the server.
 *
 * @param sendToPlayers whether the new command tree should be send to
 * all players, this is true by default, but you can disable it if you are
 * calling this function as the server is starting
 */
@NMS_General
fun LiteralArgumentBuilder<CommandListenerWrapper>.register(sendToPlayers: Boolean = true) {
    if (!BrigardierSupport.executedDefaultRegistration)
        BrigardierSupport.commands += this
    else {
        BrigardierSupport.commandDispatcher.register(this)
        if (sendToPlayers)
            BrigardierSupport.updateCommandTree()
    }
}
