@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.structures

import net.axay.kspigot.extensions.geometry.SimpleLocation2D
import net.axay.kspigot.extensions.geometry.SimpleLocation3D
import net.axay.kspigot.particles.KSpigotParticle
import org.bukkit.Material
import org.bukkit.entity.EntityType

abstract class Circle(val radius: Number) {

    abstract val data: StructureData

    val fillLocations: Set<SimpleLocation2D> by lazy {

        val locationList: MutableSet<SimpleLocation2D> = HashSet()

        var currentRadius = radius.toDouble()
        while (currentRadius >= 0) {

            var d = -currentRadius
            var x = currentRadius
            var y = 0

            while (y <= x) {

                locationList.addCircleLoc(x, y)
                locationList.addCircleLoc(x, -y)
                locationList.addCircleLoc(-x, y)
                locationList.addCircleLoc(-x, -y)
                locationList.addCircleLoc(y, x)
                locationList.addCircleLoc(y, -x)
                locationList.addCircleLoc(-y, x)
                locationList.addCircleLoc(-y, -x)

                d += 2 * y + 1
                y++

                if (d > 0) {
                    d += -2 * 3 + 2
                    x--
                }

            }

            currentRadius--

        }

        return@lazy locationList

    }

    private fun MutableSet<SimpleLocation2D>.addCircleLoc(first: Number, second: Number) {
        this += SimpleLocation2D(first, second)
    }

    val structure by lazy {
        Structure(
            HashSet<SingleStructureData>().apply {
                for (it in fillLocations)
                    this += SingleStructureData(SimpleLocation3D(it.x, 0, it.y), data)
            }
        )
    }

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