package net.axay.kspigot.inventory

import org.bukkit.event.inventory.InventoryClickEvent

class InventoryGUIClickEvent<T : ForInventory>(
    val bukkitEvent: InventoryClickEvent,
    val gui: InventoryGUI<T>
)