@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.runnables

import net.axay.kspigot.main.KSpigotMainInstance
import org.bukkit.Bukkit

abstract class ChainedRunnablePart<T, R>(
    val sync: Boolean
) {

    var next: ChainedRunnablePart<R, *>? = null

    abstract fun execute()
    
    protected abstract fun invoke(data: T): R

    protected fun start(data: T) {
        val realRunnable = Runnable {
            val result = invoke(data)
            next?.start(result)
        }
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

    override fun invoke(data: Unit) = runnable.invoke()

}

class ChainedRunnablePartThen<T, R>(
    val runnable: (T) -> R,
    sync: Boolean,
    val previous: ChainedRunnablePart<*, T>
) : ChainedRunnablePart<T, R>(sync) {

    override fun execute() = previous.execute()

    override fun invoke(data: T) = runnable.invoke(data)

}

// FIRST
fun <R> firstDo(sync: Boolean, runnable: () -> R) = ChainedRunnablePartFirst(runnable, sync)
fun <R> firstSync(runnable: () -> R) = firstDo(true, runnable)
fun <R> firstAsync(runnable: () -> R) = firstDo(false, runnable)

// THEN
fun <T, R, U> ChainedRunnablePart<T, R>.thenDo(sync: Boolean, runnable: (R) -> U)
    = ChainedRunnablePartThen(runnable, sync, this).apply { previous.next = this }
fun <T, R, U> ChainedRunnablePart<T, R>.thenSync(runnable: (R) -> U) = thenDo(true, runnable)
fun <T, R, U> ChainedRunnablePart<T, R>.thenAsync(runnable: (R) -> U) = thenDo(false, runnable)