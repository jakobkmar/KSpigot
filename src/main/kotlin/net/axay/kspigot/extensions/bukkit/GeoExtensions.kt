package net.axay.kspigot.extensions.bukkit

import org.bukkit.Chunk
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.block.Block

/**
 * Assumes that this Location has world data.
 * If not, an exception will be thrown.
 */
val Location.worldOrException: World
    get() = world
        ?: throw NullPointerException("The world of the location is null!")

/**
 * @return All blocks in this chunk.
 */
val Chunk.allBlocks
    get() = LinkedHashSet<Block>().apply {
        for (y in -64 until 320) {
            for (x in 0 until 16)
                for (z in 0 until 16)
                    add(getBlock(x, y, z))
        }
    }
