@file:Suppress("unused")

package net.axay.kspigot.runnables

import net.axay.kspigot.main.KSpigotMainInstance
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable

class KRunnableHolder : AutoCloseable {

    /**
     * [BukkitRunnable] for tracking the responsible runnable.
     * [Pair] of callback for the endCallback code and [Boolean]
     * for holding the information if the endCallback is safe
     * or not.
     */
    private val runnableEndCallbacks = HashMap<BukkitRunnable, Pair<() -> Unit, Boolean>>()

    override fun close() {
        runnableEndCallbacks.values.forEach { if (it.second) it.first.invoke() }
        runnableEndCallbacks.clear()
    }

    fun add(runnable: BukkitRunnable, callback: () -> Unit, safe: Boolean)
            = runnableEndCallbacks.put(runnable, Pair(callback, safe))
    fun remove(runnable: BukkitRunnable)
            = runnableEndCallbacks.remove(runnable)
    fun activate(runnable: BukkitRunnable)
            = runnableEndCallbacks.remove(runnable)?.first?.invoke()

}

abstract class KSpigotRunnable(
        var counterUp: Long? = null,
        var counterDownToOne: Long? = null,
        var counterDownToZero: Long? = null
) : BukkitRunnable()

/**
 * Starts a new BukkitRunnable.
 *
 * @param sync if the runnable should run sync (true) or async (false)
 * @param howOften how many times the task should be executed - null for infinite execution
 * @param delay the delay (in ticks) until the first execution of the task
 * @param period at which interval (in ticks) the task should be repeated
 * @param safe if the endCallback of the runnable should always be executed,
 * even if the server shuts down or the runnable ends prematurely
 * @param endCallback code that should always be executed when the runnable ends
 * @param runnable the runnable which should be executed each repetition
 */
fun task(
        sync: Boolean = true,
        delay: Long = 0,
        period: Long? = null,
        howOften: Long? = null,
        safe: Boolean = false,
        endCallback: (() -> Unit)? = null,
        runnable: ((KSpigotRunnable) -> Unit)? = null
) {

    if (howOften != null && howOften == 0L) return

    val bukkitRunnable = object : KSpigotRunnable() {

        private var curCounter = 0L

        override fun run() {

            var ranOut = false
            if (howOften != null) {

                counterDownToOne = howOften - curCounter
                counterDownToZero = counterDownToOne?.minus(1)

                curCounter++
                if (curCounter >= howOften)
                    ranOut = true

                counterUp = curCounter

            }

            runnable?.invoke(this)

            if (ranOut) cancel()

            if (isCancelled) {
                if (safe || ranOut)
                    KSpigotMainInstance.kRunnableHolder.activate(this)
                else
                    KSpigotMainInstance.kRunnableHolder.remove(this)
            }

        }

    }

    if (endCallback != null) KSpigotMainInstance.kRunnableHolder.add(bukkitRunnable, endCallback, safe)

    if (sync)
        bukkitRunnable.runTaskTimer(KSpigotMainInstance, delay, period ?: 20)
    else
        bukkitRunnable.runTaskTimerAsynchronously(KSpigotMainInstance, delay, period ?: 20)

}

/**
 * Starts a synchronous task.
 */
fun sync(runnable: () -> Unit)
        = Bukkit.getScheduler().runTask(KSpigotMainInstance, runnable)

/**
 * Starts an asynchronous task.
 */
fun async(runnable: () -> Unit)
        = Bukkit.getScheduler().runTaskAsynchronously(KSpigotMainInstance, runnable)