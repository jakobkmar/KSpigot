@file:Suppress("Unused")

package net.axay.kspigot.extensions.bukkit

import net.axay.kspigot.annotations.NMS_General
import net.axay.kspigot.chat.literalText
import net.axay.kspigot.extensions.onlinePlayers
import net.axay.kspigot.main.PluginInstance
import net.axay.kspigot.pluginmessages.PluginMessageConnect
import net.kyori.adventure.text.Component
import net.kyori.adventure.title.Title
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Damageable
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import java.time.Duration

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
 * Returns the material that is present under the feet of this entity.
 */
val Entity.groundMaterial get() = this.location.add(0.0, -0.01, 0.0).block.type

/**
 * @returns true if the entity is supported by a block.
 * The method from bukkit is deprecated because it can be spoofed by the client.
 * This can't be spoofed.
 */
val Entity.isStandingOnBlock: Boolean
    get() = groundMaterial.isSolid

/**
 * @returns true if the entity is standing in mid air.
 */
val Entity.isStandingInMidAir: Boolean
    get() = !isStandingOnBlock && vehicle == null && !location.clone().add(0.0, 0.1, 0.0).block.type.isSolid && !location.block.type.isSolid

/**
 * @return The max health of the entity
 * @throws NullPointerException if the entity doesn't have a max health value
 */
val LivingEntity.realMaxHealth: Double
    get() = getAttribute(Attribute.GENERIC_MAX_HEALTH)?.value
        ?: throw NullPointerException("The entity does not have a max health value!")

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
fun Player.disappear() {
    onlinePlayers.filter { it != this }.forEach { it.hidePlayer(PluginInstance, this) }
}

/**
 * Shows the player for all [onlinePlayers].
 */
fun Player.appear() {
    onlinePlayers.filter { it != this }.forEach { it.showPlayer(PluginInstance, this) }
}

/**
 * Hides all online players from this player.
 */
fun Player.hideOnlinePlayers() {
    onlinePlayers.filter { it != this }.forEach { this.hidePlayer(PluginInstance, it) }
}

/**
 * Shows all online players to this player.
 */
fun Player.showOnlinePlayers() {
    onlinePlayers.filter { it != this }.forEach { this.showPlayer(PluginInstance, it) }
}

/**
 * Spawns an entity without any variations in color, type etc...
 */
@Deprecated("This function is unstable and it cannot be guaranteed that it will work at any time in the future.")
@NMS_General
fun Location.spawnCleanEntity(entityType: EntityType): Entity? {
    val craftWorld = world as? org.bukkit.craftbukkit.v1_19_R2.CraftWorld ?: return null
    return craftWorld.createEntity(this, entityType.entityClass)?.let {
        craftWorld.handle.addFreshEntity(it)
        return@let it.bukkitEntity
    }
}

/**
 * @param mainText title text
 * @param subText subtitle text
 * @param fadeIn time in ticks for titles to fade in
 * @param stay time in ticks for titles to stay
 * @param fadeOut time in ticks for titles to fade out
 */
fun Player.title(
    mainText: Component = Component.empty(),
    subText: Component = Component.empty(),
    fadeIn: Duration = Duration.ofMillis(500),
    stay: Duration = Duration.ofMillis(3500),
    fadeOut: Duration = Duration.ofMillis(1000),
) {
    showTitle(Title.title(mainText, subText, Title.Times.times(fadeIn, stay, fadeOut)))
}

/**
 * Returns the itemInHand of the given [EquipmentSlot]
 * if it is an hand slot.
 */
fun Player.getHandItem(hand: EquipmentSlot?) = when (hand) {
    EquipmentSlot.HAND -> inventory.itemInMainHand
    EquipmentSlot.OFF_HAND -> inventory.itemInOffHand
    else -> null
}

/**
 * Sends the given [text] as an action bar message.
 */
fun Player.actionBar(text: String) {
    sendActionBar(literalText { legacyText(text) })
}

/**
 * Sends the player to the given server in the
 * BungeeCord network.
 */
fun Player.sendToServer(servername: String) {
    PluginMessageConnect(servername).sendWithPlayer(this)
}

/**
 * Adds the given ItemStacks to the player's inventory.
 * @return The items that did not fit into the player's inventory.
 */
fun Player.give(vararg itemStacks: ItemStack) = inventory.addItem(*itemStacks)

/**
 * Adds all equipment locks to every equipment slot
 */
fun ArmorStand.fullLock() {
    for (slot in EquipmentSlot.values()) {
        lock(slot)
    }
}

/**
 * Adds all equipment locks to the given slot
 * @param slot the slot which gets locked
 */
fun ArmorStand.lock(slot: EquipmentSlot) {
    for (lock in ArmorStand.LockType.values()) {
        addEquipmentLock(slot, lock)
    }
}