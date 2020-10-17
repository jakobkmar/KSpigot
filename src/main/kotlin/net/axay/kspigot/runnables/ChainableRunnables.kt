@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.runnables

import net.axay.kspigot.main.KSpigotMainInstance
import org.bukkit.Bukkit
import kotlin.reflect.KClass

abstract class ChainedRunnablePart<T, R>(
    val sync: Boolean
) {

    var next: ChainedRunnablePart<R, *>? = null

    abstract fun execute()

    abstract fun <E : Exception> executeCatching(clazz: KClass<E>, handler: (E) -> Unit = {})

    protected abstract fun invoke(data: T): R

    protected fun start(data: T) {
        run {
            val result = invoke(data)
            next?.start(result)
        }
    }

    protected fun <E : Exception> startCatching(
        data: T,
        clazz: KClass<E>,
        exceptionHandler: (E) -> Unit = {}
    ) {
        run {
            try {
                val result = invoke(data)
                next?.startCatching(result, clazz, exceptionHandler)
            } catch (e: Exception) {
                if (clazz.isInstance(e)) {
                    @Suppress("UNCHECKED_CAST")
                    exceptionHandler(e as E)
                } else {
                    throw e
                }
            }
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

    override fun execute() = start(Unit)

    override fun <E : Exception> executeCatching(clazz: KClass<E>, handler: (E) -> Unit) =
        startCatching(Unit, clazz, handler)

    override fun invoke(data: Unit) = runnable.invoke()

}

class ChainedRunnablePartThen<T, R>(
    val runnable: (T) -> R,
    sync: Boolean,
    val previous: ChainedRunnablePart<*, T>
) : ChainedRunnablePart<T, R>(sync) {

    override fun execute() = previous.execute()

    override fun <E : Exception> executeCatching(clazz: KClass<E>, handler: (E) -> Unit) =
        previous.executeCatching(clazz, handler)

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