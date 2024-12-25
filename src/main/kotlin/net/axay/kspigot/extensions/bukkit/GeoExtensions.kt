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
 * @see Chunk.blocks if you want a sequence. (much faster)
 */
val Chunk.allBlocks: Set<Block>
    get() = blocks.toSet()

/**
 * @return All blocks in this chunk as [Sequence].
 * @see Chunk.allBlocks if you want a set.
 */
val Chunk.blocks: Sequence<Block>
    get() = sequence {
        for (y in world.minHeight until world.maxHeight) {
            for (x in 0 until 16) {
                for (z in 0 until 16) {
                    yield(getBlock(x, y, z))
                }
            }
        }
    }