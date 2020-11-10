package net.axay.kspigot.extensions.bukkit

// FROM BUNGEE COLOR

/**
 * Returns the corresponding Bukkit Color object.
 */
val net.md_5.bungee.api.ChatColor.bukkitColor
    get() = org.bukkit.Color.fromRGB(color.rgb)

/**
 * Returns the corresponding Java Color object.
 * @see net.md_5.bungee.api.ChatColor.color
 */
val net.md_5.bungee.api.ChatColor.javaAwtColor: java.awt.Color
    get() = color

// FROM BUKKIT COLOR

/**
 * Returns the corresponding Bungee Color object.
 */
val org.bukkit.Color.bungeeColor: net.md_5.bungee.api.ChatColor
    get() = net.md_5.bungee.api.ChatColor.of(java.awt.Color(asRGB()))

/**
 * Returns the corresponding Java Color object.
 */
val org.bukkit.Color.javaAwtColor: java.awt.Color
    get() = java.awt.Color(asRGB())

// FROM JAVA AWT COLOR

/**
 * Returns the corresponding Bukkit Color object.
 */
val java.awt.Color.bukkitColor
    get() = org.bukkit.Color.fromRGB(rgb)

/**
 * Returns the corresponding Bungee Color object.
 */
val java.awt.Color.bungeeColor: net.md_5.bungee.api.ChatColor
    get() = net.md_5.bungee.api.ChatColor.of(this)