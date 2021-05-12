package net.axay.kspigot.extensions.bukkit

import org.bukkit.event.inventory.InventoryAction
import org.bukkit.inventory.Inventory

/**
 * Closes the inventory for all viewers.
 */
fun Inventory.closeForViewers() = HashSet(viewers).forEach { it.closeInventory() }

/**
 * @return True, if the action was a simple inventory click.
 * (a mouse click, where items were picked up or placed)
 */
val InventoryAction.isSimple
    get() = when (this) {
        InventoryAction.PLACE_ALL, InventoryAction.PLACE_ONE,
        InventoryAction.PICKUP_ALL, InventoryAction.PICKUP_HALF, InventoryAction.PICKUP_ONE,
        -> true
        else -> false
    }
