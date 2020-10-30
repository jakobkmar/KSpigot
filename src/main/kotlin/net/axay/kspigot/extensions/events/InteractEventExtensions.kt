package net.axay.kspigot.extensions.events

import net.axay.kspigot.annotations.UnsafeImplementation
import org.bukkit.block.Block
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack

/**
 * Returns the item used in the interaction
 * with the use of the [EquipmentSlot] returned
 * by the value [PlayerInteractEntityEvent.hand].
 */
val PlayerInteractEntityEvent.interactItem: ItemStack?
    get() {
        val p: Player = this.player
        return when (this.hand) {
            EquipmentSlot.HAND -> p.inventory.itemInMainHand
            EquipmentSlot.OFF_HAND -> p.inventory.itemInOffHand
            EquipmentSlot.CHEST, EquipmentSlot.FEET, EquipmentSlot.HEAD, EquipmentSlot.LEGS -> null
        }
    }

/**
 * @return True, if the action was a left mouse button click.
 */
val Action.isLeftClick get() = this == Action.LEFT_CLICK_BLOCK || this == Action.LEFT_CLICK_AIR

/**
 * @return True, if the action was a right mouse button click.
 */
val Action.isRightClick get() = this == Action.RIGHT_CLICK_BLOCK || this == Action.RIGHT_CLICK_AIR

@UnsafeImplementation
val PlayerInteractEvent.clickedBlockExceptAir: Block?
    get() {
        return clickedBlock ?: kotlin.run {
            return@run if (this.action == Action.RIGHT_CLICK_AIR) {

                val p: Player = this.player

                // check for sight blocking entities
                for (nearbyEntity: Entity in p.getNearbyEntities(5.0, 5.0, 5.0))
                    if (p.hasLineOfSight(nearbyEntity)) return@run null

                // get first block in line of sight which is not air
                p.getLineOfSight(null, 5).find { block -> !block.type.isAir }

            } else null
        }
    }
