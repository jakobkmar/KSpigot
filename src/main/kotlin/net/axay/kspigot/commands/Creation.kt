package net.axay.kspigot.commands

import com.mojang.brigadier.builder.ArgumentBuilder
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.axay.kspigot.commands.internal.BrigardierSupport
import net.minecraft.commands.CommandSourceStack

/**
 * Creates a new command.
 *
 * @param name the name of the root command
 * @param register if true, the command will automatically be registered
 */
inline fun command(
    name: String,
    register: Boolean = true,
    builder: LiteralArgumentBuilder<CommandSourceStack>.() -> Unit
): LiteralArgumentBuilder<CommandSourceStack> =
    LiteralArgumentBuilder.literal<CommandSourceStack>(name).apply(builder).apply {
        if (register)
            BrigardierSupport.commands += this
    }

/**
 * Adds a new literal to this command.
 *
 * @param name the name of the literal
 */
inline fun ArgumentBuilder<CommandSourceStack, *>.literal(
    name: String,
    builder: LiteralArgumentBuilder<CommandSourceStack>.() -> Unit = {}
) = command(name, false, builder).also { then(it) }
