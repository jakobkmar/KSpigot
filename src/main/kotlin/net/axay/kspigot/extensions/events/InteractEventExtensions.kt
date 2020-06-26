package net.axay.kspigot.extensions.events

import org.bukkit.block.Block
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack

val PlayerInteractEntityEvent.interactItem: ItemStack?
    get() {
        val p: Player = this.player
        return when (this.hand) {
            EquipmentSlot.HAND -> p.inventory.itemInMainHand
            EquipmentSlot.OFF_HAND -> p.inventory.itemInOffHand
            EquipmentSlot.CHEST, EquipmentSlot.FEET, EquipmentSlot.HEAD, EquipmentSlot.LEGS -> null
        }
    }

val PlayerInteractEvent.clickedBlockExceptAir: Block?
    get() {
        val p: Player = this.player
        return when (this.action) {
            Action.RIGHT_CLICK_BLOCK -> this.clickedBlock
            Action.RIGHT_CLICK_AIR -> {
                for (nearbyEntity: Entity in p.getNearbyEntities(5.0, 5.0, 5.0))
                    if (p.hasLineOfSight(nearbyEntity)) return null
                p.getLineOfSight(null, 5).find { block -> !block.type.isAir }
            }
            else -> null
        }
    }
