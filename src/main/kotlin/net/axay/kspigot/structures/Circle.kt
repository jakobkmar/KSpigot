@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.structures

import net.axay.kspigot.extensions.geometry.SimpleLocation2D
import net.axay.kspigot.extensions.geometry.SimpleLocation3D
import net.axay.kspigot.particles.KSpigotParticle
import org.bukkit.Material
import org.bukkit.entity.EntityType

private fun circleEdgeLocations(radius: Number) = HashSet<SimpleLocation2D>().apply {

    val currentRadius = radius.toDouble()

    var d = -currentRadius
    var x = currentRadius
    var y = 0

    while (y <= x) {

        addSimpleLoc2D(x, y)
        addSimpleLoc2D(x, -y)
        addSimpleLoc2D(-x, y)
        addSimpleLoc2D(-x, -y)
        addSimpleLoc2D(y, x)
        addSimpleLoc2D(y, -x)
        addSimpleLoc2D(-y, x)
        addSimpleLoc2D(-y, -x)

        d += 2 * y + 1
        y++

        if (d > 0) {
            d += -2 * x + 2
            x--
        }

    }

}

private fun MutableSet<SimpleLocation2D>.addSimpleLoc2D(first: Number, second: Number) {
    this += SimpleLocation2D(first, second)
}

abstract class Circle(val radius: Number) {

    protected abstract val data: StructureData

    val fillLocations by lazy {

        var currentRadius = radius.toDouble()

        HashSet<SimpleLocation2D>().apply {
            while (currentRadius >= 0) {
                this += circleEdgeLocations(currentRadius).mapTo(HashSet()) {
                    mutableSetOf(it).apply {
                        this += SimpleLocation2D(it.x + when { it.x >= 1 -> -1; it.x <= -1 -> 1; else -> 0 }, it.y)
                        this += SimpleLocation2D(it.x, it.y + when { it.y >= 1 -> -1; it.y <= -1 -> 1; else -> 0 })
                    }
                }.flatten()
                currentRadius--
            }
        }

    }

    val edgeLocations by lazy {
        circleEdgeLocations(radius)
    }

    val filledStructure by lazy { structure(true) }

    val edgeStructure by lazy { structure(false) }

    fun structure(filled: Boolean) = Structure(
        HashSet<SingleStructureData>().apply {
            val locations = if (filled) fillLocations else edgeLocations
            for (it in locations)
                this += SingleStructureData(SimpleLocation3D(it.x, 0, it.y), data)
        }
    )

}

class MaterialCircle(radius: Number, material: Material) : Circle(radius) {
    override val data = StructureDataMaterial(material)
}

class ParticleCircle(radius: Number, particle: KSpigotParticle) : Circle(radius) {
    override val data = StructureDataParticle(particle)
}

class EntityCircle(radius: Number, entityType: EntityType) : Circle(radius) {
    override val data = StructureDataEntity(entityType)
}