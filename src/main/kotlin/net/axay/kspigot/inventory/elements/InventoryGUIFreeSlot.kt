package net.axay.kspigot.inventory.elements

import net.axay.kspigot.inventory.ForInventory
import net.axay.kspigot.inventory.InventoryGUIClickEvent
import net.axay.kspigot.inventory.InventoryGUISlot

class InventoryGUIFreeSlot<T : ForInventory> : InventoryGUISlot<T>() {
    override fun onClick(clickEvent: InventoryGUIClickEvent<T>) { /* do nothing */ }
}