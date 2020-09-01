package net.axay.kspigot.extensions.geometry

import org.bukkit.Location
import org.bukkit.World

data class SimpleLocation2D(val x: Double, val y: Double) {
    constructor(x: Number, y: Number) : this(x.toDouble(), y.toDouble())
}

data class SimpleLocation3D(val x: Double, val y: Double, val z: Double) {
    constructor(x: Number, y: Number, z: Number) : this(x.toDouble(), y.toDouble(), z.toDouble())
}

// EXTENSIONS

fun SimpleLocation3D.withWorld(world: World) = Location(world, x, y, z)

val Location.worldOrException: World get() = world ?: throw NullPointerException("The world of the location is null!")