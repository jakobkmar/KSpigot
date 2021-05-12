package net.axay.kspigot.extensions

import net.axay.kspigot.main.KSpigotMainInstance
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * Shortcut to get all online players.
 * @see Bukkit.getOnlinePlayers
 */
val onlinePlayers: Collection<Player> get() = Bukkit.getOnlinePlayers()

/**
 * Shortcut to get a collection of all
 * online players plus the console command sender.
 */
val onlineSenders: Collection<CommandSender> get() = Bukkit.getOnlinePlayers().plus(Bukkit.getConsoleSender())

/**
 * Shortcut to get the Server.
 * @see Bukkit.getServer
 */
val server get() = Bukkit.getServer()

/**
 * Shortcut to get the PluginManager.
 * @see Bukkit.getPluginManager
 */
val pluginManager get() = Bukkit.getPluginManager()

/**
 * Broadcasts a message ([msg]) on the server.
 * @return the number of recipients
 * @see Bukkit.broadcastMessage
 */
fun broadcast(msg: String) = Bukkit.broadcastMessage(msg)

/**
 * Shortcut to get the ConsoleSender.
 * @see Bukkit.getConsoleSender
 */
val console get() = Bukkit.getConsoleSender()

/**
 * Shortcut for creating a new [NamespacedKey]
 */
fun pluginKey(key: String) = NamespacedKey(KSpigotMainInstance, key)