package net.axay.kspigot.commands

import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.builder.ArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder
import com.mojang.brigadier.context.CommandContext
import net.axay.kspigot.commands.internal.ArgumentTypeUtils
import net.minecraft.commands.CommandSourceStack

/**
 * Adds an argument.
 *
 * @param name the name of the argument
 * @param type the type of the argument - e.g. IntegerArgumentType.integer() or StringArgumentType.string()
 */
inline fun <T> ArgumentBuilder<CommandSourceStack, *>.argument(
    name: String,
    type: ArgumentType<T>,
    builder: RequiredArgumentBuilder<CommandSourceStack, T>.() -> Unit = {}
): RequiredArgumentBuilder<CommandSourceStack, T> =
    RequiredArgumentBuilder.argument<CommandSourceStack, T>(name, type).apply(builder).also { then(it) }

/**
 * Adds an argument. The argument type will be resolved via the reified
 * type [T].
 *
 * @param name the name of the argument
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T> ArgumentBuilder<CommandSourceStack, *>.argument(
    name: String,
    builder: RequiredArgumentBuilder<CommandSourceStack, T>.() -> Unit = {}
): RequiredArgumentBuilder<CommandSourceStack, T> =
    RequiredArgumentBuilder.argument<CommandSourceStack, T>(name, ArgumentTypeUtils.fromReifiedType<T>()).apply(builder).also { then(it) }

/**
 * Get the value of this argument.
 */
inline fun <reified T> CommandContext<CommandSourceStack>.getArgument(name: String): T =
    getArgument(name, T::class.java)
