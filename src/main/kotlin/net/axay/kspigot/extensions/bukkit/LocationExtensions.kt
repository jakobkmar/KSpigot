package net.axay.kspigot.extensions.bukkit

import org.bukkit.Location
import org.bukkit.World

/**
 * Assumes that this Location has world data.
 * If not, an exception will be thrown.
 */
val Location.worldOrException: World
    get() = world
        ?: throw NullPointerException("The world of the location is null!")