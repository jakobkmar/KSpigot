package net.axay.kspigot.extensions.bukkit

import net.axay.kspigot.main.PluginInstance
import net.md_5.bungee.api.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.plugin.Plugin

/** @see printColoredPrefix */
fun CommandSender.print(text: String, plugin: Plugin? = PluginInstance) =
    printColoredPrefix(text, ChatColor.RESET, plugin?.name ?: "INFO", ChatColor.GRAY)

/** @see printColoredPrefix */
fun CommandSender.info(text: String, plugin: Plugin? = PluginInstance) =
    printColoredPrefix(text, ChatColor.WHITE, plugin?.name ?: "INFO", ChatColor.DARK_AQUA)

/** @see printColoredPrefix */
fun CommandSender.success(text: String, plugin: Plugin? = PluginInstance) =
    printColoredPrefix(text, ChatColor.GREEN, plugin?.name ?: "SUCCESS", ChatColor.DARK_AQUA)

/** @see printColoredPrefix */
fun CommandSender.warn(text: String, plugin: Plugin? = PluginInstance) =
    printColoredPrefix(text, ChatColor.WHITE, plugin?.name?.plus(" - WARN") ?: "WARN", ChatColor.YELLOW)

/** @see printColoredPrefix */
fun CommandSender.error(text: String, plugin: Plugin? = PluginInstance) =
    printColoredPrefix(text, ChatColor.RED, plugin?.name?.plus(" - ERROR") ?: "ERROR", ChatColor.DARK_RED)

/**
 * Sends the given message and adds the given prefix with the given color to it.
 */
fun CommandSender.printColoredPrefix(text: String, textColor: ChatColor, prefix: String, prefixColor: ChatColor) =
    sendMessage("${prefixColor}[${prefix}]${textColor} $text")
