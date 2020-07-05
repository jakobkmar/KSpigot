package net.axay.kspigot.main

import net.axay.kspigot.runnables.KRunnables
import org.bukkit.plugin.java.JavaPlugin

class KSpigot(val plugin: JavaPlugin) {

    /**
     * This function should be invoked
     * in the onDisable() method of your plugin.
     */
    fun shutdown() {
        KRunnables.shutdown()
    }

}