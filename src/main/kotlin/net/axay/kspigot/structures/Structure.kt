package net.axay.kspigot.structures

import net.axay.kspigot.extensions.geometry.SimpleLocation3D
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.data.BlockData

interface StructureData {
    fun createAt(loc: Location)
}

class SingleStructureData(
    val location: SimpleLocation3D,
    val structureData: StructureData,
)

data class Structure(
    val structureData: Set<SingleStructureData>,
) {
    constructor(vararg structureDataSets: Set<SingleStructureData>)
        : this(structureDataSets.flatMapTo(HashSet()) { it })
}

data class StructureDataMaterial(
    val material: Material,
) : StructureData {
    override fun createAt(loc: Location) {
        loc.block.type = material
    }
}

data class StructureDataBlock(
    val material: Material,
    val blockData: BlockData,
) : StructureData {
    constructor(block: Block) : this(block.type, block.blockData)

    override fun createAt(loc: Location) {
        loc.block.let {
            it.type = material
            it.blockData = blockData
        }
    }
}