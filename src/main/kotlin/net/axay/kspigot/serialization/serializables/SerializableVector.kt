@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.serialization.serializables

import net.axay.kspigot.serialization.SpigotSerializable
import net.axay.kspigot.serialization.SpigotSerializableCompanion
import org.bukkit.util.Vector

data class SerializableVector(
    val x: Double,
    val y: Double,
    val z: Double,
) : SpigotSerializable<Vector> {
    companion object : SpigotSerializableCompanion<SerializableVector>

    constructor(vec: Vector) : this(vec.x, vec.y, vec.z)

    override fun toSpigot() = Vector(x, y, z)
}