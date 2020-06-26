package net.axay.kspigot.extensions.bukkit

import org.bukkit.GameMode

val GameMode.isDamageable: Boolean
    get() = when (this) {
        GameMode.SURVIVAL -> true
        GameMode.ADVENTURE -> true
        GameMode.SPECTATOR -> false
        GameMode.CREATIVE -> false
    }