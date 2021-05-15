package net.axay.kspigot.particles

import net.axay.kspigot.extensions.bukkit.worldOrException
import net.axay.kspigot.languageextensions.kotlinextensions.applyIfNotNull
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.entity.Player
import org.bukkit.util.Vector

/**
 * @param particle The type of the particle.
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
    var force: Boolean = false,
) {
    /**
     * Spawns the particle at the location. It
     * will be visible for everyone near it.
     */
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

    /**
     * Spawns the particle at the location of the
     * player. It will be only visible for the player.
     */
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

/**
 * Accesses the particle builder.
 * @see KSpigotParticle
 */
fun particle(particle: Particle, builder: KSpigotParticle.() -> Unit) = KSpigotParticle(particle).apply(builder)

/**
 * Accesses the particle builder and then immediately
 * spawns the particle at the given location.
 * @see KSpigotParticle
 */
fun Location.particle(particle: Particle, builder: (KSpigotParticle.() -> Unit)? = null) =
    KSpigotParticle(particle).applyIfNotNull(builder).spawnAt(this)

/**
 * Accesses the particle builder and then immediately
 * spawns the particle for the player.
 * @see KSpigotParticle
 */
fun Player.particle(particle: Particle, builder: (KSpigotParticle.() -> Unit)? = null) =
    KSpigotParticle(particle).applyIfNotNull(builder).spawnFor(this)
