package net.axay.kspigot.particles

import net.axay.kspigot.extensions.geometry.worldOrException
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.entity.Player
import org.bukkit.util.Vector

/**
 * @param particle The type of [Particle].
 * @param amount The amount of particles.
 * @param offset The offset at which particles should appear.
 * @param extra Particle specific data, often specifying an aspect like speed.
 * @param data Particle specific data, often specifying BlockData or an ItemStack.
 * @param force Determines whether the client should be encouraged to display the particles.
 */
data class KSpigotParticle(
        val particle: Particle,
        var amount: Int = 1,
        var offset: Vector? = null,
        var extra: Number = 1.0,
        var data: Any? = null,
        var force: Boolean = false
) {

    fun spawnAt(loc: Location) {
        loc.worldOrException.spawnParticle(
                particle,
                loc,
                amount,
                offset?.x ?: 0.0,
                offset?.y ?: 0.0,
                offset?.z ?: 0.0,
                extra.toDouble(),
                data,
                force
        )
    }

    fun spawnFor(player: Player) {
        player.spawnParticle(
                particle,
                player.location,
                amount,
                offset?.x ?: 0.0,
                offset?.y ?: 0.0,
                offset?.z ?: 0.0,
                extra.toDouble(),
                data
        )
    }

}

fun particle(particle: Particle, builder: KSpigotParticle.() -> Unit)
        = KSpigotParticle(particle).apply(builder)

fun Player.particle(particle: Particle, builder: KSpigotParticle.() -> Unit)
        = KSpigotParticle(particle).apply(builder).spawnFor(this)

fun Location.particle(particle: Particle, builder: KSpigotParticle.() -> Unit)
        = KSpigotParticle(particle).apply(builder).spawnAt(this)