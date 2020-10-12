@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.runnables

import net.axay.kspigot.main.KSpigotMainInstance
import org.bukkit.Bukkit

/*
 * Chainable bukkit runnable.
 */

class ChainedRunnablePart<T, R>(
        val runnable: (T?) -> R,
        val sync: Boolean,
        var previous: ChainedRunnablePart<*, T>? = null,
        var next: ChainedRunnablePart<R, *>? = null
) {

    fun execute() {
        previous?.execute() ?: kotlin.run { start(null) }
    }

    private fun start(data: T?) {
        val realRunnable = Runnable {
            val result = runnable.invoke(data)
            next?.start(result)
        }
        if (sync)
            Bukkit.getScheduler().runTask(KSpigotMainInstance, realRunnable)
        else
            Bukkit.getScheduler().runTaskAsynchronously(KSpigotMainInstance, realRunnable)
    }

}

// FIRST
fun <R> firstDo(sync: Boolean, runnable: (Unit?) -> R)
        = ChainedRunnablePart<Unit, R>(runnable, sync)
fun <R> firstSync(runnable: (Unit?) -> R) = firstDo(true, runnable)
fun <R> firstAsync(runnable: (Unit?) -> R) = firstDo(false, runnable)

// THEN
fun <T, R, U> ChainedRunnablePart<T, R>.thenDo(sync: Boolean, runnable: (R?) -> U): ChainedRunnablePart<R, U> {
    ChainedRunnablePart<R, U>(runnable, sync).apply {
        previous = this@thenDo
        this@thenDo.next = this
        return this
    }
}
fun <T, R, U> ChainedRunnablePart<T, R>.thenSync(runnable: (R?) -> U) = thenDo(true, runnable)
fun <T, R, U> ChainedRunnablePart<T, R>.thenAsync(runnable: (R?) -> U) = thenDo(false, runnable)