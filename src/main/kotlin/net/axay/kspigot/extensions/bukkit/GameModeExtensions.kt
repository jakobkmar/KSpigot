package net.axay.kspigot.extensions.bukkit

import org.bukkit.GameMode

val GameMode.isDamageable: Boolean
    get() = when (this) {
        GameMode.SURVIVAL, GameMode.ADVENTURE -> true
        GameMode.SPECTATOR, GameMode.CREATIVE -> false
    }