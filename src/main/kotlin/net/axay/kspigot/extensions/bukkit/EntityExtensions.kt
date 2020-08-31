package net.axay.kspigot.extensions.bukkit

import net.axay.kspigot.annotations.NMS_General
import net.axay.kspigot.extensions.onlinePlayers
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld
import org.bukkit.entity.*
import org.bukkit.plugin.Plugin

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
fun Player.disappear(plugin: Plugin) {
    onlinePlayers.filter { it != this }.forEach { it.hidePlayer(plugin, this) }
}

/**
 * Shows the player for all [onlinePlayers].
 */
fun Player.appear(plugin: Plugin) {
    onlinePlayers.filter { it != this }.forEach { it.showPlayer(plugin, this) }
}

/**
 * Kicks the player from the server.
 */
fun Player.kick(reason: String? = "You got kicked!") {
    kickPlayer(reason)
}

/**
 * Spawns an entity without any variations in color, type etc...
 */
@NMS_General
fun Location.spawnCleanEntity(entityType: EntityType): Entity? {
    val craftWorld = world as? CraftWorld ?: return null
    return craftWorld.createEntity(this, entityType.entityClass)?.let {
        craftWorld.handle.addEntity(it)
        return@let it.bukkitEntity
    }
}