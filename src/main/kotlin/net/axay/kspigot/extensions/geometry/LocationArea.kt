@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.extensions.geometry

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

    val minLoc = SimpleLocation3D(min(loc1.x, loc2.x), min(loc1.y, loc2.y), min(loc1.z, loc2.z))
    val maxLoc = SimpleLocation3D(max(loc1.x, loc2.x), max(loc1.y, loc2.y), max(loc1.z, loc2.z))

    fun isInArea(
            loc: Location,
            check3d: Boolean = true,
            tolerance: Int = 0
    ): Boolean {

        // checking world
        if (loc.world != world) return false

        return if (
            // checking x
            loc.x >= minLoc.x - tolerance && loc.x <= maxLoc.x + tolerance &&
            // checking z
            loc.z >= minLoc.z - tolerance && loc.z <= maxLoc.z + tolerance
        ) {
            // checking y
            if (check3d) loc.y >= minLoc.x - tolerance && loc.y <= maxLoc.y + tolerance else true
        } else false

    }

    val touchedChunks: Set<SimpleChunkLocation> by lazy {

        val foundChunks = HashSet<SimpleChunkLocation>()

        (minLoc.chunk.x until maxLoc.chunk.x + 1).forEach { curX ->
        (minLoc.chunk.z until maxLoc.chunk.z + 1).forEach { curZ ->
            foundChunks += SimpleChunkLocation(curX, curZ)
        } }

        return@lazy foundChunks

    }

}

class LocationArea(loc1: Location, loc2: Location) {

    var loc1: Location = loc1
        set(value) {
            field = value
            locationPair = SimpleLocationPair(value, loc2)
        }
    var loc2: Location = loc2
        set(value) {
            field = value
            locationPair = SimpleLocationPair(loc1, value)
        }

    var locationPair = SimpleLocationPair(loc1, loc2); private set

    val world: World get() = locationPair.world
    val minLoc: Location get() = locationPair.minLoc.withWorld(locationPair.world)
    val maxLoc: Location get() = locationPair.maxLoc.withWorld(locationPair.world)

    val touchedChunks: Set<Chunk> get() = locationPair.touchedChunks.mapTo(HashSet()) { it.withWorld(world) }

}