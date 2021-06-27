package net.axay.kspigot.commands

import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.builder.ArgumentBuilder
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder
import com.mojang.brigadier.context.CommandContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture
import net.minecraft.commands.CommandListenerWrapper

/**
 * Create a new command.
 *
 * @param name the name of the root command
 * @param register if true, the command will be automatically registered
 * when the plugin is fully enabled
 */
inline fun command(
    name: String,
    register: Boolean = true,
    builder: LiteralArgumentBuilder<CommandListenerWrapper>.() -> Unit
): LiteralArgumentBuilder<CommandListenerWrapper> =
    LiteralArgumentBuilder.literal<CommandListenerWrapper>(name).apply(builder).apply {
        if (register)
            BrigardierSupport.commands += this
    }

/**
 * Add custom execution logic for this command.
 */
inline fun <S> ArgumentBuilder<S, *>.simpleExecutes(
    crossinline executor: (CommandContext<S>) -> Unit
) {
    executes wrapped@{
        executor.invoke(it)
        return@wrapped 1
    }
}

/**
 * Add a new literal to this command.
 *
 * @param name the name of the literal
 */
inline fun ArgumentBuilder<CommandListenerWrapper, *>.literal(
    name: String,
    builder: LiteralArgumentBuilder<CommandListenerWrapper>.() -> Unit
) {
    then(command(name, false, builder))
}

/**
 * Add an argument.
 *
 * @param name the name of the argument
 * @param type the type of the argument - e.g. IntegerArgumentType.integer() or StringArgumentType.string()
 */
inline fun <T> ArgumentBuilder<CommandListenerWrapper, *>.argument(
    name: String,
    type: ArgumentType<T>,
    builder: RequiredArgumentBuilder<CommandListenerWrapper, T>.() -> Unit
) {
    then(RequiredArgumentBuilder.argument<CommandListenerWrapper, T>(name, type).apply(builder))
}

private val argumentCoroutineScope = CoroutineScope(Dispatchers.IO)

/**
 * Add custom suspending suggestion logic for an argument.
 */
fun <S> RequiredArgumentBuilder<S, *>.simpleSuggests(
    suggestionBuilder: suspend (CommandContext<S>) -> Iterable<Any?>?
) {
    suggests { context, builder ->
        argumentCoroutineScope.async {
            suggestionBuilder.invoke(context)?.forEach {
                if (it is Int)
                    builder.suggest(it)
                else
                    builder.suggest(it.toString())
            }
            builder.build()
        }.asCompletableFuture()
    }
}

/**
 * Get the value of this argument.
 */
inline fun <reified T> CommandContext<CommandListenerWrapper>.getArgument(name: String): T =
    getArgument(name, T::class.java)
