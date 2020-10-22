@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.runnables

import kotlin.reflect.KClass

abstract class ChainedRunnablePart<T, R>(
    val sync: Boolean
) {

    var next: ChainedRunnablePart<R, *>? = null

    protected abstract fun invoke(data: T): R

    /**
     * Begins execution of this chained runnable.
     */
    abstract fun execute()

    /**
     * Begins execution of this chained runnable, catching any exception of
     * type [E] and passing it to the optional [exceptionHandler].
     *
     * @param exceptionSync whether the exception handler should be executed
     * synchronously or asynchronously, defaults to `true` (Note that usage of
     * any Spigot API functions requires it to be sync)
     */
    inline fun <reified E : Exception> executeCatching(
        exceptionSync: Boolean = true,
        noinline exceptionHandler: ((E) -> Unit)? = null
    ) {
        executeCatchingImpl(E::class, exceptionSync, exceptionHandler)
    }

    /**
     * Has to be public for use in inline function [executeCatching], not
     * intended to be used directly.
     */
    abstract fun <E : Exception> executeCatchingImpl(
        exceptionClass: KClass<E>,
        exceptionSync: Boolean,
        exceptionHandler: ((E) -> Unit)?,
    )

    protected fun start(data: T) {
        taskRun(sync) {
            val result = invoke(data)
            next?.start(result)
        }
    }

    protected fun <E : Exception> startCatching(
        data: T,
        exceptionClass: KClass<E>,
        exceptionSync: Boolean,
        exceptionHandler: ((E) -> Unit)?,
    ) {
        taskRun(sync) {
            val result = try {
                invoke(data)
            } catch (e: Exception) {
                if (exceptionClass.isInstance(e)) {
                    @Suppress("UNCHECKED_CAST")
                    if (sync == exceptionSync) {
                        exceptionHandler?.invoke(e as E)
                    } else if (exceptionHandler != null) {
                        taskRun(exceptionSync) {
                            exceptionHandler.invoke(e as E)
                        }
                    }
                    return@taskRun
                } else throw e
            }
            next?.startCatching(result, exceptionClass, exceptionSync, exceptionHandler)
        }
    }

}

class ChainedRunnablePartFirst<R>(
    val runnable: () -> R,
    sync: Boolean
) : ChainedRunnablePart<Unit, R>(sync) {

    override fun execute() = start(Unit)

    override fun <E : Exception> executeCatchingImpl(
        exceptionClass: KClass<E>,
        exceptionSync: Boolean,
        exceptionHandler: ((E) -> Unit)?
    ) = startCatching(Unit, exceptionClass, exceptionSync, exceptionHandler)

    override fun invoke(data: Unit) = runnable.invoke()

}

class ChainedRunnablePartThen<T, R>(
    val runnable: (T) -> R,
    sync: Boolean,
    val previous: ChainedRunnablePart<*, T>
) : ChainedRunnablePart<T, R>(sync) {

    override fun execute() = previous.execute()

    override fun <E : Exception> executeCatchingImpl(
        exceptionClass: KClass<E>,
        exceptionSync: Boolean,
        exceptionHandler: ((E) -> Unit)?
    ) = previous.executeCatchingImpl(exceptionClass, exceptionSync, exceptionHandler)

    override fun invoke(data: T) = runnable.invoke(data)

}

// FIRST
fun <R> firstDo(sync: Boolean, runnable: () -> R) = ChainedRunnablePartFirst(runnable, sync)
fun <R> firstSync(runnable: () -> R) = firstDo(true, runnable)
fun <R> firstAsync(runnable: () -> R) = firstDo(false, runnable)

// THEN
fun <T, R, U> ChainedRunnablePart<T, R>.thenDo(sync: Boolean, runnable: (R) -> U) =
    ChainedRunnablePartThen(runnable, sync, this).apply { previous.next = this }

fun <T, R, U> ChainedRunnablePart<T, R>.thenSync(runnable: (R) -> U) = thenDo(true, runnable)
fun <T, R, U> ChainedRunnablePart<T, R>.thenAsync(runnable: (R) -> U) = thenDo(false, runnable)