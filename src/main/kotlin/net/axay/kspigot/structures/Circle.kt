@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.structures

import net.axay.kspigot.extensions.geometry.SimpleLocation2D
import net.axay.kspigot.particles.KSpigotParticle
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.EntityType

abstract class Circle(val radius: Number) {

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

    final fun buildAtX(loc: Location) {
        for (it in fillLocations)
            setAt(Location(loc.world, loc.x, loc.y + it.x, loc.z + it.x))
    }

    final fun buildAtY(loc: Location) {
        for (it in fillLocations)
            setAt(Location(loc.world, loc.x + it.x, loc.y, loc.z + it.x))
    }

    final fun buildAtZ(loc: Location) {
        for (it in fillLocations)
            setAt(Location(loc.world, loc.x + it.x, loc.y + it.x, loc.z))
    }

    abstract fun setAt(loc: Location)

}

class BlockCircle(radius: Number, val material: Material) : Circle(radius) {
    override fun setAt(loc: Location) {
        loc.block.type = material
    }
}

class ParticleCircle(radius: Number, val particle: KSpigotParticle) : Circle(radius) {
    override fun setAt(loc: Location) {
        particle.spawnAt(loc)
    }
}

class EntityCircle(radius: Number, val entityType: EntityType) : Circle(radius) {
    override fun setAt(loc: Location) {
        loc.world?.spawnEntity(loc, entityType) ?: throw IllegalArgumentException("The world of the given location is null!")
    }
}