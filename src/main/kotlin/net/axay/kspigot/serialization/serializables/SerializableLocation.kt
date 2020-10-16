@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.serialization.serializables

import net.axay.kspigot.serialization.SpigotSerializable
import net.axay.kspigot.serialization.SpigotSerialzableCompanion
import org.bukkit.Bukkit
import org.bukkit.Location

class SerializableLocation(
    val world: String?,
    val x: Double,
    val y: Double,
    val z: Double,
    val direction: SerializableVector
) : SpigotSerializable<Location> {

    companion object : SpigotSerialzableCompanion<SerializableLocation>

    constructor(loc: Location) : this(loc.world?.name, loc.x, loc.y, loc.z, SerializableVector(loc.direction))

    override fun toSpigot()
        = Location(world?.let { Bukkit.getWorld(world) }, x, y, z)
            .apply { direction = this@SerializableLocation.direction.toSpigot() }

}