package net.axay.kspigot.structures

import net.axay.kspigot.extensions.geometry.plus
import org.bukkit.Location

fun Structure.buildAt(loc: Location) {
    structureData.forEach { it.structureData.createAt(loc + it.location) }
}