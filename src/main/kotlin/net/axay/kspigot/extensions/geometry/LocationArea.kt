@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.extensions.geometry

import net.axay.kspigot.extensions.bukkit.worldOrException
import org.bukkit.Chunk
import org.bukkit.Location
import org.bukkit.World
import kotlin.math.max
import kotlin.math.min

class SimpleLocationPair(loc1: Location, loc2: Location) {
    val world = loc1.worldOrException.let {
        if (it == loc2.worldOrException) it
        else throw IllegalArgumentException("The given locations worlds are not the same!")
    }
    val minSimpleLoc = SimpleLocation3D(min(loc1.x, loc2.x), min(loc1.y, loc2.y), min(loc1.z, loc2.z))
    val maxSimpleLoc = SimpleLocation3D(max(loc1.x, loc2.x), max(loc1.y, loc2.y), max(loc1.z, loc2.z))
    fun isInArea(
        loc: Location,
        check3d: Boolean = true,
        tolerance: Int = 0,
    ): Boolean {
        // checking world
        if (loc.world != world) return false

        return if (
        // checking x
            loc.x >= minSimpleLoc.x - tolerance && loc.x <= maxSimpleLoc.x + tolerance &&
            // checking z
            loc.z >= minSimpleLoc.z - tolerance && loc.z <= maxSimpleLoc.z + tolerance
        ) {
            // checking y
            if (check3d) loc.y >= minSimpleLoc.y - tolerance && loc.y <= maxSimpleLoc.y + tolerance else true
        } else false
    }

    val touchedSimpleChunks: Set<SimpleChunkLocation> by lazy {
        val foundChunks = HashSet<SimpleChunkLocation>()

        (minSimpleLoc.chunk.x until maxSimpleLoc.chunk.x + 1).forEach { curX ->
            (minSimpleLoc.chunk.z until maxSimpleLoc.chunk.z + 1).forEach { curZ ->
                foundChunks += SimpleChunkLocation(curX, curZ)
            }
        }

        return@lazy foundChunks
    }
}

class LocationArea(loc1: Location, loc2: Location) {
    var loc1: Location = loc1
        set(value) {
            field = value
            simpleLocationPair = SimpleLocationPair(value, loc2)
        }
    var loc2: Location = loc2
        set(value) {
            field = value
            simpleLocationPair = SimpleLocationPair(loc1, value)
        }
    var simpleLocationPair = SimpleLocationPair(loc1, loc2); private set
    val world: World get() = simpleLocationPair.world
    val minLoc: Location get() = simpleLocationPair.minSimpleLoc.withWorld(simpleLocationPair.world)
    val maxLoc: Location get() = simpleLocationPair.maxSimpleLoc.withWorld(simpleLocationPair.world)
    val touchedChunks: Set<Chunk> get() = simpleLocationPair.touchedSimpleChunks.mapTo(HashSet()) { it.withWorld(world) }
    fun isInArea(
        loc: Location,
        check3d: Boolean = true,
        tolerance: Int = 0,
    ) = simpleLocationPair.isInArea(loc, check3d, tolerance)
}