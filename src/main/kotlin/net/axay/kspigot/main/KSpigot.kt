package net.axay.kspigot.main

import net.axay.kspigot.runnables.KRunnableHolder
import org.bukkit.plugin.java.JavaPlugin

/**
 * This is the main instance of kSpigot.
 *
 * It should be initialized when a plugin using
 * kSpigot gets enabled.
 *
 * **Instead** of overriding [onLoad()], [onEnable()]
 * and [onDisable()] **override**:
 * - [load()] (called first)
 * - [startup()]  (called second)
 * - [shutdown()] (called in the "end")
 */
abstract class KSpigot : JavaPlugin() {

    val kRunnableHolder = KRunnableHolder()

    /**
     * Called when the plugin was loaded
     */
    open fun load() { }

    /**
     * Called when the plugin was enabled
     */
    open fun startup() { }

    /**
     * Called when the plugin gets disabled
     */
    open fun shutdown() { }

    final override fun onLoad() {
        load()
    }

    final override fun onEnable() {
        startup()
    }

    final override fun onDisable() {
        shutdown()
        kRunnableHolder.shutdown()
    }

}