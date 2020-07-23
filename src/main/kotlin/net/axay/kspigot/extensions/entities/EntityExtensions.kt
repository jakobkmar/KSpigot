package net.axay.kspigot.extensions.entities

import org.bukkit.Material
import org.bukkit.entity.Entity

val Entity.isInWater: Boolean get() = this.location.block.type == Material.WATER

val Entity.isGroundSolid: Boolean get() = this.location.add(0.0, -0.01, 0.0).block.type.isSolid