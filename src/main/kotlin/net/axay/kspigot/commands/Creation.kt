package net.axay.kspigot.commands

import com.mojang.brigadier.builder.ArgumentBuilder
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.axay.kspigot.commands.internal.BrigardierSupport
import net.axay.kspigot.commands.internal.ServerCommandSource

/**
 * Creates a new command.
 *
 * @param name the name of the root command
 * @param register if true, the command will automatically be registered
 */
inline fun command(
    name: String,
    register: Boolean = true,
    builder: LiteralArgumentBuilder<ServerCommandSource>.() -> Unit
): LiteralArgumentBuilder<ServerCommandSource> =
    LiteralArgumentBuilder.literal<ServerCommandSource>(name).apply(builder).apply {
        if (register)
            BrigardierSupport.commands += this
    }

/**
 * Adds a new literal to this command.
 *
 * @param name the name of the literal
 */
inline fun ArgumentBuilder<ServerCommandSource, *>.literal(
    name: String,
    builder: LiteralArgumentBuilder<ServerCommandSource>.() -> Unit = {}
) = command(name, false, builder).also { then(it) }
