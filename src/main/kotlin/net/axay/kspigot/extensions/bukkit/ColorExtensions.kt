@file:Suppress("unused")

package net.axay.kspigot.extensions.bukkit

import net.kyori.adventure.text.format.TextColor
import org.bukkit.Color as BukkitColor
import java.awt.Color as JavaColor

// FROM ADVENTURE TEXTCOLOR

/**
 * Returns the corresponding Bukkit Color object.
 */
val TextColor.bukkitColor
    get() = BukkitColor.fromRGB(value())

/**
 * Returns the corresponding Java Color object.
 * @see net.md_5.bungee.api.ChatColor.color
 */
val TextColor.javaAwtColor: JavaColor
    get() = JavaColor(value())

// FROM BUKKIT COLOR

/**
 * Returns the corresponding Bungee Color object.
 */
val BukkitColor.textColor: TextColor
    get() = TextColor.color(asRGB())

/**
 * Returns the corresponding Java Color object.
 */
val BukkitColor.javaAwtColor: JavaColor
    get() = JavaColor(asRGB())

// FROM JAVA AWT COLOR

/**
 * Returns the corresponding Bukkit Color object.
 */
val JavaColor.bukkitColor
    get() = BukkitColor.fromRGB(rgb)

/**
 * Returns the corresponding Bungee Color object.
 */
val JavaColor.textColor: TextColor
    get() = TextColor.color(rgb)
