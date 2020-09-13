package net.axay.kspigot.structures

import net.axay.kspigot.extensions.geometry.plus
import net.axay.kspigot.extensions.geometry.toSimpleLoc
import net.axay.kspigot.extensions.geometry.toVector
import org.bukkit.Location
import org.bukkit.util.Vector

fun Structure.buildAt(loc: Location) {
    structureData.forEach { it.structureData.createAt(loc + it.location) }
}

fun Structure.rotateAroundX(angle: Number)
    = rotate { it.rotateAroundX(angle.toDouble()) }

fun Structure.rotateAroundY(angle: Number)
    = rotate { it.rotateAroundY(angle.toDouble()) }

fun Structure.rotateAroundZ(angle: Number)
    = rotate { it.rotateAroundZ(angle.toDouble()) }

inline fun Structure.rotate(vectorRotation: (Vector) -> Vector)
    = Structure(
        HashSet<SingleStructureData>().apply {
            structureData.forEach {
                this += SingleStructureData(
                    vectorRotation.invoke(it.location.toVector()).toSimpleLoc(),
                    it.structureData
                )
            }
        }
    )