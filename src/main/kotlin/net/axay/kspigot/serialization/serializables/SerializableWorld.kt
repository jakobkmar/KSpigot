@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.serialization.serializables

import net.axay.kspigot.serialization.SpigotSerializable
import net.axay.kspigot.serialization.SpigotSerializableCompanion
import org.bukkit.Bukkit
import org.bukkit.World

class SerializableWorld(
    val name: String,
) : SpigotSerializable<World> {
    companion object : SpigotSerializableCompanion<World>

    constructor(world: World) : this(world.name)

    override fun toSpigot() = Bukkit.getWorld(name)
        ?: throw NullPointerException("The world \"$name\" does not exist")
}