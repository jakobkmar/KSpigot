@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.runnables

import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin

/*
 * Chainable bukkit runnable.
 */

class ChainedRunnablePart<T, R>(
        val plugin: Plugin,
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
            Bukkit.getScheduler().runTask(plugin, realRunnable)
        else
            Bukkit.getScheduler().runTaskAsynchronously(plugin, realRunnable)
    }

}

// FIRST
fun <R> Plugin.firstDo(sync: Boolean, runnable: (Unit?) -> R)
        = ChainedRunnablePart<Unit, R>(this, runnable, sync)
fun <R> Plugin.firstSync(runnable: (Unit?) -> R) = firstDo(true, runnable)
fun <R> Plugin.firstAsync(runnable: (Unit?) -> R) = firstDo(false, runnable)

// THEN
fun <T, R, U> ChainedRunnablePart<T, R>.thenDo(sync: Boolean, runnable: (R?) -> U): ChainedRunnablePart<R, U> {
    ChainedRunnablePart<R, U>(plugin, runnable, sync).apply {
        previous = this@thenDo
        this@thenDo.next = this
        return this
    }
}
fun <T, R, U> ChainedRunnablePart<T, R>.thenSync(runnable: (R?) -> U) = thenDo(true, runnable)
fun <T, R, U> ChainedRunnablePart<T, R>.thenAsync(runnable: (R?) -> U) = thenDo(false, runnable)