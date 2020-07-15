package net.axay.kspigot.main

import net.axay.kspigot.runnables.KRunnableHolder
import org.bukkit.plugin.java.JavaPlugin

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