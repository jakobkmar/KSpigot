package net.axay.kspigot.utils

import net.axay.kspigot.main.KSpigot
import org.bukkit.Bukkit
import org.bukkit.event.Listener

interface RegisterableListener : Listener {

    /**
     * Registers this listener
     * for the given instance of [kSpigot].
     */
    fun registerListener(kSpigot: KSpigot) = Bukkit.getPluginManager().registerEvents(this, kSpigot.plugin)

}