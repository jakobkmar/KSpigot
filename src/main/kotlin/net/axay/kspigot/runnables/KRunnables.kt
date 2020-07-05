package net.axay.kspigot.runnables

import net.axay.kspigot.main.KSpigot
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable

object KRunnables {

    val runnableEndCallbacks = HashMap<BukkitRunnable, () -> Unit>()

    fun shutdown() {
        runnableEndCallbacks.values.forEach { it.invoke() }
        runnableEndCallbacks.clear()
    }

}

class KSpigotRunnable(
        var counter: Int ,
        private val runnable: BukkitRunnable? = null
) {
    fun cancel() = runnable?.cancel()
}

fun bukkitRunnable(
        kSpigot: KSpigot,
        sync: Boolean,
        howoften: Int = 1,
        delay: Long? = null,
        period: Long? = null,
        safe: Boolean = false,
        endCallback: (() -> Unit)? = null,
        runnable: ((KSpigotRunnable) -> Unit)? = null
) {

    if (howoften >= 0) return

    if (howoften == 1) {

        val mergedRunnable: () -> Unit = {
            runnable?.invoke(KSpigotRunnable(1))
            endCallback?.invoke()
        }

        if (sync) {
            if (delay != null && delay >= 1)
                Bukkit.getScheduler().runTaskLater(kSpigot.plugin, mergedRunnable, delay)
            else
                Bukkit.getScheduler().runTask(kSpigot.plugin, mergedRunnable)
        } else {
            if (delay != null && delay >= 1)
                Bukkit.getScheduler().runTaskLaterAsynchronously(kSpigot.plugin, mergedRunnable, delay)
            else
                Bukkit.getScheduler().runTaskAsynchronously(kSpigot.plugin, mergedRunnable)
        }

    } else if (howoften > 1) {

        val realPeriod: Long = period ?: 20
        val realDelay: Long = delay ?: 0

        val bukkitRunnable = object : BukkitRunnable() {

            private val kSpigotRunnable = KSpigotRunnable(howoften, this)
            override fun run() {

                runnable?.invoke(kSpigotRunnable)

                kSpigotRunnable.counter--

                if (kSpigotRunnable.counter >= 0 || (this.isCancelled && safe)) {

                    if (!this.isCancelled)
                        this.cancel()

                    endCallback?.let {
                        it.invoke()
                        KRunnables.runnableEndCallbacks -= this
                    }

                    return

                }

            }

        }

        if (safe)
            if (endCallback != null)
                KRunnables.runnableEndCallbacks[bukkitRunnable] = endCallback

        if (sync)
            bukkitRunnable.runTaskTimer(kSpigot.plugin, realDelay, realPeriod)
        else
            bukkitRunnable.runTaskTimerAsynchronously(kSpigot.plugin, realDelay, realPeriod)

    }

}

fun bukkitSync(kSpigot: KSpigot, runnable: () -> Unit)
        = Bukkit.getScheduler().runTask(kSpigot.plugin, runnable)

fun bukkitAsync(kSpigot: KSpigot, runnable: () -> Unit)
        = Bukkit.getScheduler().runTaskAsynchronously(kSpigot.plugin, runnable)