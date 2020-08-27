package net.axay.kspigot.extensions.bukkit

import net.axay.kspigot.extensions.onlinePlayers
import net.axay.kspigot.main.KSpigot
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Damageable
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

/**
 * Checks if the entity is completely in water.
 */
val LivingEntity.isInWater: Boolean get() = isFeetInWater && isHeadInWater

/**
 * Checks if the entities' head is in water.
 */
val LivingEntity.isHeadInWater: Boolean get() = this.eyeLocation.block.type == Material.WATER

/**
 * Checks if the entities' feet are in water.
 */
val Entity.isFeetInWater: Boolean get() = this.location.block.type == Material.WATER

/**
 * Checks if the entity stands on solid ground.
 */
val Entity.isGroundSolid: Boolean get() = this.location.add(0.0, -0.01, 0.0).block.type.isSolid

/**
 * Kills the damageable.
 */
fun Damageable.kill() {
    health = 0.0
}

/**
 * Sets the entities' health to the max possible value.
 * @throws NullPointerException if the entity does not have a max health value
 */
fun LivingEntity.heal() {
    health = getAttribute(Attribute.GENERIC_MAX_HEALTH)?.value
            ?: throw NullPointerException("The entity does not have a max health value!")
}

/**
 * Sets the players' foodLevel to the
 * max possible value.
 */
fun Player.feed() {
    foodLevel = 20
}

/**
 * Sets the players' saturation to the
 * current max possible value.
 */
fun Player.saturate() {
    saturation = foodLevel.toFloat()
}

/**
 * Feeds and saturates the player.
 */
fun Player.feedSaturate() {
    foodLevel = 20
    saturation = 20f
}

/**
 * Hides the player for all [onlinePlayers].
 */
fun Player.disappear(kSpigot: KSpigot) {
    onlinePlayers.filter { it != this }.forEach { it.hidePlayer(kSpigot, this) }
}

/**
 * Shows the player for all [onlinePlayers].
 */
fun Player.appear(kSpigot: KSpigot) {
    onlinePlayers.filter { it != this }.forEach { it.showPlayer(kSpigot, this) }
}