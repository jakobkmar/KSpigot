package net.axay.kspigot.commands

import com.mojang.brigadier.Message
import com.mojang.brigadier.builder.RequiredArgumentBuilder
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture

@PublishedApi
internal val argumentCoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

/**
 * Suggest the value which is the result of the [suggestionBuilder].
 */
inline fun <S> RequiredArgumentBuilder<S, *>.suggestSingle(
    crossinline suggestionBuilder: (CommandContext<S>) -> Any?
) {
    suggests { context, builder ->
        builder.applyAny(suggestionBuilder(context))
        builder.buildFuture()
    }
}

/**
 * Suggest the value which is the result of the [suggestionBuilder].
 * Additionaly, a separate tooltip associated with the suggestion
 * will be shown as well.
 */
inline fun <S> RequiredArgumentBuilder<S, *>.suggestSingleWithTooltip(
    crossinline suggestionBuilder: (CommandContext<S>) -> Pair<Any, Message>?
) {
    suggests { context, builder ->
        builder.applyAnyWithTooltip(suggestionBuilder(context))
        builder.buildFuture()
    }
}

/**
 * Suggest the value which is the result of the [suggestionBuilder].
 *
 * @param coroutineScope the [CoroutineScope] where the suggestion should be built in - an async scope by default
 */
inline fun <S> RequiredArgumentBuilder<S, *>.suggestSingleSuspending(
    coroutineScope: CoroutineScope = argumentCoroutineScope,
    crossinline suggestionBuilder: suspend (CommandContext<S>) -> Any?
) {
    suggests { context, builder ->
        coroutineScope.async {
            builder.applyAny(suggestionBuilder(context))
            builder.build()
        }.asCompletableFuture()
    }
}

/**
 * Suggest the value which is the result of the [suggestionBuilder].
 * Additionaly, a separate tooltip associated with the suggestion
 * will be shown as well.
 *
 * @param coroutineScope the [CoroutineScope] where the suggestion should be built in - an async scope by default
 */
inline fun <S> RequiredArgumentBuilder<S, *>.suggestSingleWithTooltipSuspending(
    coroutineScope: CoroutineScope = argumentCoroutineScope,
    crossinline suggestionBuilder: suspend (CommandContext<S>) -> Pair<Any?, Message>?
) {
    suggests { context, builder ->
        coroutineScope.async {
            builder.applyAnyWithTooltip(suggestionBuilder(context))
            builder.build()
        }.asCompletableFuture()
    }
}

/**
 * Suggest the entries of the iterable which is the result of the
 * [suggestionsBuilder].
 */
inline fun <S> RequiredArgumentBuilder<S, *>.suggestList(
    crossinline suggestionsBuilder: (CommandContext<S>) -> Iterable<Any?>?
) {
    suggests { context, builder ->
        builder.applyIterable(suggestionsBuilder(context))
        builder.buildFuture()
    }
}

/**
 * Suggest the entries of the iterable which is the result of the
 * [suggestionsBuilder].
 * Additionaly, a separate tooltip associated with each suggestion
 * will be shown as well.
 */
inline fun <S> RequiredArgumentBuilder<S, *>.suggestListWithTooltips(
    crossinline suggestionsBuilder: (CommandContext<S>) -> Iterable<Pair<Any?, Message>?>?
) {
    suggests { context, builder ->
        builder.applyIterableWithTooltips(suggestionsBuilder(context))
        builder.buildFuture()
    }
}

/**
 * Suggest the entries of the iterable which is the result of the
 * [suggestionsBuilder].
 *
 * @param coroutineScope the [CoroutineScope] where the suggestions should be built in - an async scope by default
 */
inline fun <S> RequiredArgumentBuilder<S, *>.suggestListSuspending(
    coroutineScope: CoroutineScope = argumentCoroutineScope,
    crossinline suggestionsBuilder: suspend (CommandContext<S>) -> Iterable<Any?>?
) {
    suggests { context, builder ->
        coroutineScope.async {
            builder.applyIterable(suggestionsBuilder(context))
            builder.build()
        }.asCompletableFuture()
    }
}

/**
 * Suggest the entries of the iterable which is the result of the
 * [suggestionsBuilder].
 * Additionaly, a separate tooltip associated with each suggestion
 * will be shown as well.
 *
 * @param coroutineScope the [CoroutineScope] where the suggestions should be built in - an async scope by default
 */
inline fun <S> RequiredArgumentBuilder<S, *>.suggestListWithTooltipsSuspending(
    coroutineScope: CoroutineScope = argumentCoroutineScope,
    crossinline suggestionsBuilder: (CommandContext<S>) -> Iterable<Pair<Any?, Message>?>?
) {
    suggests { context, builder ->
        coroutineScope.async {
            builder.applyIterableWithTooltips(suggestionsBuilder(context))
            builder.build()
        }.asCompletableFuture()
    }
}

@PublishedApi
internal fun SuggestionsBuilder.applyAny(any: Any?) {
    when (any) {
        is Int -> suggest(any)
        is String -> suggest(any)
        else -> suggest(any.toString())
    }
}


@PublishedApi
internal fun SuggestionsBuilder.applyAnyWithTooltip(pair: Pair<Any?, Message>?) {
    if (pair == null) return
    val (any, message) = pair
    when (any) {
        is Int -> suggest(any, message)
        is String -> suggest(any, message)
        else -> suggest(any.toString(), message)
    }
}

@PublishedApi
internal fun SuggestionsBuilder.applyIterable(iterable: Iterable<Any?>?) =
    iterable?.forEach(::applyAny)

@PublishedApi
internal fun SuggestionsBuilder.applyIterableWithTooltips(iterable: Iterable<Pair<Any?, Message>?>?) =
    iterable?.forEach(::applyAnyWithTooltip)

/**
 * Adds custom suspending suggestion logic for an argument.
 *
 * @param coroutineScope the [CoroutineScope] where the suggestions should be built in - an async scope by default
 */
@Deprecated(
    "The name 'simpleSuggests' has been superseded by 'suggestListSuspending'",
    ReplaceWith("suggestListSuspending(coroutineScope, suggestionBuilder)")
)
fun <S> RequiredArgumentBuilder<S, *>.simpleSuggests(
    coroutineScope: CoroutineScope = argumentCoroutineScope,
    suggestionBuilder: suspend (CommandContext<S>) -> Iterable<Any?>?
) = suggestListSuspending(coroutineScope, suggestionBuilder)
