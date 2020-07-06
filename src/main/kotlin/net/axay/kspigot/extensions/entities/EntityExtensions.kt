package net.axay.kspigot.extensions.entities

import org.bukkit.Material
import org.bukkit.entity.Entity

val Entity.isInWater: Boolean get() = this.location.block.type == Material.WATER