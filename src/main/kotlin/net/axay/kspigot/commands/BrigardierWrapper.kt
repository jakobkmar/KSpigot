package net.axay.kspigot.commands

import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.builder.ArgumentBuilder
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder
import com.mojang.brigadier.tree.LiteralCommandNode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
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
    builder: LiteralArgumentBuilder<CommandListenerWrapper>.() -> Unit,
): LiteralCommandNode<CommandListenerWrapper> =
    LiteralArgumentBuilder.literal<CommandListenerWrapper>(name).apply(builder).build().apply {
        if (register)
            BrigardierSupport.register(name, this)
    }

/**
 * Add custom execution logic for this command.
 */
inline fun ArgumentBuilder<CommandListenerWrapper, *>.simpleExecutes(
    crossinline executor: CommandContext.() -> Unit,
) {
    executes wrapped@{
        executor.invoke(CommandContext(it))
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
    builder: LiteralArgumentBuilder<CommandListenerWrapper>.() -> Unit,
) = command(name, false, builder).also { then(it) }

/**
 * Add an argument.
 *
 * @param name the name of the argument
 * @param type the type of the argument - e.g. IntegerArgumentType.integer() or StringArgumentType.string()
 */
inline fun <T> ArgumentBuilder<CommandListenerWrapper, *>.argument(
    name: String,
    type: ArgumentType<T>,
    builder: RequiredArgumentBuilder<CommandListenerWrapper, T>.() -> Unit,
): RequiredArgumentBuilder<CommandListenerWrapper, T> =
    RequiredArgumentBuilder.argument<CommandListenerWrapper, T>(name, type).apply(builder).also { then(it) }

private val argumentCoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

/**
 * Add custom suspending suggestion logic for an argument.
 */
fun RequiredArgumentBuilder<CommandListenerWrapper, *>.simpleSuggests(
    suggestionBuilder: suspend CommandContext.() -> Iterable<Any?>?,
) {
    suggests { context, builder ->
        argumentCoroutineScope.async {
            suggestionBuilder.invoke(CommandContext(context))?.forEach {
                if (it is Int)
                    builder.suggest(it)
                else
                    builder.suggest(it.toString())
            }
            builder.build()
        }.asCompletableFuture()
    }
}
