package net.axay.kspigot.extensions.geometry

import org.bukkit.Location
import org.bukkit.World

data class SimpleLocation2D(val x: Double, val y: Double) {
    constructor(x: Number, y: Number) : this(x.toDouble(), y.toDouble())
}

data class SimpleLocation3D(val x: Double, val y: Double, val z: Double) {
    constructor(x: Number, y: Number, z: Number) : this(x.toDouble(), y.toDouble(), z.toDouble())
    val chunk: SimpleChunkLocation get() = SimpleChunkLocation(x.toInt() shr 4, z.toInt() shr 4)
}

data class SimpleChunkLocation(val x: Int, val z: Int)

// EXTENSIONS

fun SimpleLocation3D.withWorld(world: World) = Location(world, x, y, z)
fun SimpleChunkLocation.withWorld(world: World) = world.getChunkAt(x, z)

val Location.worldOrException: World get() = world ?: throw NullPointerException("The world of the location is null!")