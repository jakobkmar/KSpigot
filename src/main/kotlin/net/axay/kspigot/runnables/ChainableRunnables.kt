@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.runnables

import net.axay.kspigot.main.KSpigotMainInstance
import org.bukkit.Bukkit
import kotlin.reflect.KClass

abstract class ChainedRunnablePart<T, R>(
    val sync: Boolean
) {

    var next: ChainedRunnablePart<R, *>? = null

    protected abstract fun invoke(data: T): R

    abstract fun execute()

    abstract fun <E : Exception> executeCatching(
            @Suppress("UNCHECKED_CAST") exceptionClass: KClass<E> = Exception::class as KClass<E>,
            exceptionHandler: ((E) -> Unit)? = null,
    )

    protected fun start(data: T) {
        this.run {
            val result = invoke(data)
            next?.start(result)
        }
    }

    protected fun <E : Exception> startCatching(
        data: T,
        exceptionClass: KClass<E>,
        exceptionHandler: ((E) -> Unit)?
    ) {
        this.run {
            val result = try {
                invoke(data)
            } catch (e: Exception) {
                if (exceptionClass.isInstance(e)) {
                    @Suppress("UNCHECKED_CAST")
                    exceptionHandler?.invoke(e as E)
                    null
                } else throw e
            }
            if (result != null)
                next?.startCatching(result, exceptionClass, exceptionHandler)
        }
    }

    private fun run(realRunnable: () -> Unit) {
        if (sync)
            Bukkit.getScheduler().runTask(KSpigotMainInstance, realRunnable)
        else
            Bukkit.getScheduler().runTaskAsynchronously(KSpigotMainInstance, realRunnable)
    }

}

class ChainedRunnablePartFirst<R>(
    val runnable: () -> R,
    sync: Boolean
) : ChainedRunnablePart<Unit, R>(sync) {

    override fun execute()
            = start(Unit)

    override fun <E : Exception> executeCatching(exceptionClass: KClass<E>, exceptionHandler: ((E) -> Unit)?)
            = startCatching(Unit, exceptionClass, exceptionHandler)

    override fun invoke(data: Unit) = runnable.invoke()

}

class ChainedRunnablePartThen<T, R>(
    val runnable: (T) -> R,
    sync: Boolean,
    val previous: ChainedRunnablePart<*, T>
) : ChainedRunnablePart<T, R>(sync) {

    override fun execute()
            = previous.execute()

    override fun <E : Exception> executeCatching(exceptionClass: KClass<E>, exceptionHandler: ((E) -> Unit)?)
            = previous.executeCatching(exceptionClass, exceptionHandler)

    override fun invoke(data: T) = runnable.invoke(data)

}

// FIRST
fun <R> firstDo(sync: Boolean, runnable: () -> R)
        = ChainedRunnablePartFirst(runnable, sync)
fun <R> firstSync(runnable: () -> R) = firstDo(true, runnable)
fun <R> firstAsync(runnable: () -> R) = firstDo(false, runnable)

// THEN
fun <T, R, U> ChainedRunnablePart<T, R>.thenDo(sync: Boolean, runnable: (R) -> U)
        = ChainedRunnablePartThen(runnable, sync, this).apply { previous.next = this }
fun <T, R, U> ChainedRunnablePart<T, R>.thenSync(runnable: (R) -> U) = thenDo(true, runnable)
fun <T, R, U> ChainedRunnablePart<T, R>.thenAsync(runnable: (R) -> U) = thenDo(false, runnable)