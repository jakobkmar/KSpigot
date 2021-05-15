@file:Suppress("unused")

package net.axay.kspigot.structures

import net.axay.kspigot.extensions.geometry.blockLoc
import net.axay.kspigot.extensions.geometry.plus
import net.axay.kspigot.extensions.geometry.toSimpleLoc
import net.axay.kspigot.extensions.geometry.toVector
import org.bukkit.Location
import org.bukkit.util.Vector

fun Structure.buildAt(loc: Location) {
    val buildLoc = loc.blockLoc
    structureData.forEach { it.structureData.createAt(buildLoc + it.location) }
}

/** @see Structure.rotate */
fun Structure.rotateAroundX(angle: Number) = rotate(angle) { it, rad -> it.rotateAroundZ(rad) }

/** @see Structure.rotate */
fun Structure.rotateAroundY(angle: Number) = rotate(angle) { it, rad -> it.rotateAroundY(rad) }

/** @see Structure.rotate */
fun Structure.rotateAroundZ(angle: Number) = rotate(angle) { it, rad -> it.rotateAroundZ(rad) }

/** @param angle The angle of rotation in degrees.*/
inline fun Structure.rotate(angle: Number, vectorRotation: (Vector, Double) -> Vector) = Structure(
    HashSet<SingleStructureData>().apply {
        structureData.forEach {
            this += SingleStructureData(
                vectorRotation.invoke(it.location.toVector(), Math.toRadians(angle.toDouble())).toSimpleLoc(),
                it.structureData
            )
        }
    }
)
