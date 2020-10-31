package net.axay.kspigot.extensions.bukkit

import org.bukkit.event.inventory.InventoryAction
import org.bukkit.inventory.Inventory

fun Inventory.closeForViewers() = viewers.forEach { it.closeInventory() }

val InventoryAction.isSimple
    get() = when (this) {
        InventoryAction.PLACE_ALL, InventoryAction.PLACE_ONE,
        InventoryAction.PICKUP_ALL, InventoryAction.PICKUP_HALF, InventoryAction.PICKUP_ONE
        -> true
        else -> false
    }