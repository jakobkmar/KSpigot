package net.axay.kspigot.extensions.entities

import org.bukkit.Material
import org.bukkit.entity.Player

val Player.isInWater: Boolean get() = this.location.block.type == Material.WATER