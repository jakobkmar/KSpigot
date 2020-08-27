package net.axay.kspigot.extensions

import org.bukkit.Bukkit
import org.bukkit.entity.Player

/**
 * @see Bukkit.getOnlinePlayers
 */
val onlinePlayers: Collection<Player> get() = Bukkit.getOnlinePlayers()

/**
 * @see Bukkit.getServer
 */
val server by lazy { Bukkit.getServer() }

/**
 * @see Bukkit.getPluginManager
 */
val pluginManager by lazy { Bukkit.getPluginManager() }