@file:Suppress("unused")

package net.axay.kspigot.structures

import net.axay.kspigot.extensions.geometry.LocationArea
import net.axay.kspigot.extensions.geometry.blockLoc
import net.axay.kspigot.extensions.geometry.relationTo
import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.entity.Entity

/**
 * @return A [Structure] containing all data of the given [LocationArea].
 */
fun LocationArea.loadStructure(includeBlocks: Boolean = true, includeEntities: Boolean = false) = Structure(
    if (includeBlocks)
        fillBlocks.mapTo(HashSet()) {
            SingleStructureData(it.location relationTo minLoc.blockLoc, StructureDataBlock(it))
        }
    else emptySet(),
    if (includeEntities)
        entities.mapTo(HashSet()) {
            SingleStructureData(it.location relationTo minLoc.blockLoc, StructureDataEntity(it))
        }
    else emptySet()
)

/**
 * @return All blocks in the given [LocationArea].
 * Sorted by their coordinates.
 */
val LocationArea.fillBlocks: Set<Block>
    get()
    = LinkedHashSet<Block>().apply {
        (minLoc.blockX until maxLoc.blockX + 1).forEach { x ->
            (minLoc.blockY until maxLoc.blockY + 1).forEach { y ->
                (minLoc.blockZ until maxLoc.blockZ + 1).forEach { z ->
                    this += Location(world, x.toDouble(), y.toDouble(), z.toDouble()).block
                }
            }
        }
    }

/**
 * @return All entities in the given [LocationArea].
 */
val LocationArea.entities: Set<Entity>
    get()
    = HashSet<Entity>().apply {
        touchedChunks.forEach {
            it.entities.forEach { en ->
                if (simpleLocationPair.isInArea(en.location))
                    this += en
            }
        }
    }