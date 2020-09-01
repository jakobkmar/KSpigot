package net.axay.kspigot.structures

import net.axay.kspigot.annotations.NMS_General
import net.axay.kspigot.data.NBTData
import net.axay.kspigot.data.nbtData
import net.axay.kspigot.extensions.bukkit.spawnCleanEntity
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.data.BlockData
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType

interface StructureData {
    fun createAt(loc: Location)
}

data class StructureDataBlock(
        val material: Material,
        val blockData: BlockData
) : StructureData {

    constructor(block: Block) : this(block.type, block.blockData)

    override fun createAt(loc: Location) {
        loc.block.let {
            it.type = material
            it.blockData = blockData
        }
    }

}

@NMS_General
data class StructureDataEntity(
        val entityType: EntityType,
        val nbtData: NBTData
) : StructureData {

    constructor(entity: Entity) : this(entity.type, entity.nbtData)

    override fun createAt(loc: Location) {
        loc.spawnCleanEntity(entityType)?.nbtData = nbtData
    }

}

class SingleStructureData(
        val location: Location,
        val structureData: StructureData
)

data class Structure(
        val blocks: Set<SingleStructureData> = emptySet(),
        val entities: Set<SingleStructureData> = emptySet()
)