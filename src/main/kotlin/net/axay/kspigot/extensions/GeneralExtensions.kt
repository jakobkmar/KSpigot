package net.axay.kspigot.extensions

import org.bukkit.Bukkit
import org.bukkit.entity.Player

/**
 * Shortcut to get all online players.
 * @see Bukkit.getOnlinePlayers
 */
val onlinePlayers: Collection<Player> get() = Bukkit.getOnlinePlayers()

/**
 * Shortcut to get the Server.
 * @see Bukkit.getServer
 */
val server by lazy { Bukkit.getServer() }

/**
 * Shortcut to get the PluginManager.
 * @see Bukkit.getPluginManager
 */
val pluginManager by lazy { Bukkit.getPluginManager() }

/**
 * Broadcasts a message ([msg]) on the server.
 * @return the number of recipients
 * @see Bukkit.broadcastMessage
 */
fun broadcast(msg: String) = Bukkit.broadcastMessage(msg)