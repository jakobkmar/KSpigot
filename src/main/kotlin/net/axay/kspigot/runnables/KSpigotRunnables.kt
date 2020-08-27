package net.axay.kspigot.runnables

import net.axay.kspigot.main.KSpigot
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable

class KRunnableHolder {

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
        sync: Boolean = true,
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
                Bukkit.getScheduler().runTaskLater(kSpigot, mergedRunnable, delay)
            else
                Bukkit.getScheduler().runTask(kSpigot, mergedRunnable)
        } else {
            if (delay != null && delay >= 1)
                Bukkit.getScheduler().runTaskLaterAsynchronously(kSpigot, mergedRunnable, delay)
            else
                Bukkit.getScheduler().runTaskAsynchronously(kSpigot, mergedRunnable)
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
                        kSpigot.kRunnableHolder.runnableEndCallbacks -= this
                    }

                    return

                }

            }

        }

        if (safe)
            if (endCallback != null)
                kSpigot.kRunnableHolder.runnableEndCallbacks[bukkitRunnable] = endCallback

        if (sync)
            bukkitRunnable.runTaskTimer(kSpigot, realDelay, realPeriod)
        else
            bukkitRunnable.runTaskTimerAsynchronously(kSpigot, realDelay, realPeriod)

    }

}

fun bukkitSync(kSpigot: KSpigot, runnable: () -> Unit)
        = Bukkit.getScheduler().runTask(kSpigot, runnable)

fun bukkitAsync(kSpigot: KSpigot, runnable: () -> Unit)
        = Bukkit.getScheduler().runTaskAsynchronously(kSpigot, runnable)