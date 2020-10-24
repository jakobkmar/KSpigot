package net.axay.kspigot.gui

import org.bukkit.event.inventory.InventoryClickEvent

class GUIClickEvent<T : ForInventory>(
    val bukkitEvent: InventoryClickEvent,
    val gui: GUI<T>
)