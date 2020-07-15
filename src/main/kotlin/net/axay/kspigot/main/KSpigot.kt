package net.axay.kspigot.main

import net.axay.kspigot.runnables.KRunnableHolder
import org.bukkit.plugin.java.JavaPlugin

/**
 * This is the main instance of kSpigot.
 *
 * It should be initialized when a plugin using
 * kSpigot gets enabled.
 *
 * Do not forget to call the [shutdown] method
 * when your plugin gets disabled.
 */
class KSpigot(val plugin: JavaPlugin) {

    val kRunnableHolder = KRunnableHolder()

    /**
     * This function should be invoked
     * in the onDisable() method of your plugin.
     */
    fun shutdown() {
        kRunnableHolder.shutdown()
    }

}