package net.axay.kspigot.runnables

import net.axay.kspigot.main.KSpigot
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable

class KRunnableHolder {

    private val runnableEndCallbacks = HashMap<BukkitRunnable, () -> Unit>()

    fun shutdown() {
        runnableEndCallbacks.values.forEach { it.invoke() }
        runnableEndCallbacks.clear()
    }

    fun add(runnable: BukkitRunnable, callback: () -> Unit) { runnableEndCallbacks[runnable] = callback }
    fun remove(runnable: BukkitRunnable) = runnableEndCallbacks.remove(runnable)
    fun activate(runnable: BukkitRunnable) = runnableEndCallbacks.remove(runnable)?.invoke()

}

abstract class KSpigotRunnable(
        var counterUp: Long? = null,
        var counterDownToOne: Long? = null,
        var counterDownToZero: Long? = null,
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
fun KSpigot.task(
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
                    kRunnableHolder.activate(this)
                else
                    kRunnableHolder.remove(this)
            }

        }

    }

    if (endCallback != null) kRunnableHolder.add(bukkitRunnable, endCallback)

    if (sync)
        bukkitRunnable.runTaskTimer(this, delay, period ?: 20)
    else
        bukkitRunnable.runTaskTimerAsynchronously(this, delay, period ?: 20)

}

/**
 * Starts a synchronous task.
 */
fun Plugin.sync(runnable: () -> Unit)
        = Bukkit.getScheduler().runTask(this, runnable)

/**
 * Starts an asynchronous task.
 */
fun Plugin.async(runnable: () -> Unit)
        = Bukkit.getScheduler().runTaskAsynchronously(this, runnable)