@file:Suppress("unused")

package net.axay.kspigot.extensions.bukkit

import net.axay.kspigot.chat.KColors
import net.axay.kspigot.main.PluginInstance
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.plugin.Plugin

/** @see printColoredPrefix */
fun CommandSender.print(text: String, plugin: Plugin? = PluginInstance) =
    printColoredPrefix(text, KColors.WHITE, plugin?.name ?: "INFO", KColors.GRAY)

/** @see printColoredPrefix */
fun CommandSender.info(text: String, plugin: Plugin? = PluginInstance) =
    printColoredPrefix(text, KColors.WHITE, plugin?.name ?: "INFO", KColors.DARKAQUA)

/** @see printColoredPrefix */
fun CommandSender.success(text: String, plugin: Plugin? = PluginInstance) =
    printColoredPrefix(text, KColors.GREEN, plugin?.name ?: "SUCCESS", KColors.DARKAQUA)

/** @see printColoredPrefix */
fun CommandSender.warn(text: String, plugin: Plugin? = PluginInstance) =
    printColoredPrefix(text, KColors.WHITE, plugin?.name?.plus(" - WARN") ?: "WARN", KColors.YELLOW)

/** @see printColoredPrefix */
fun CommandSender.error(text: String, plugin: Plugin? = PluginInstance) =
    printColoredPrefix(text, KColors.RED, plugin?.name?.plus(" - ERROR") ?: "ERROR", KColors.DARKRED)

/**
 * Sends the given message and adds the given prefix with the given color to it.
 */
fun CommandSender.printColoredPrefix(text: String, textColor: TextColor, prefix: String, prefixColor: TextColor) =
    sendMessage(Component.text(prefix).color(prefixColor).append(Component.text(text).color(textColor)))

/**
 * Dispatches the command given by [commandLine].
 *
 * @param commandLine the command without a leading /
 */
fun CommandSender.dispatchCommand(commandLine: String) =
    Bukkit.dispatchCommand(this, commandLine)
