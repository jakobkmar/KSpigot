package net.axay.kspigot.gui

import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent

class GUIClickEvent<T : ForInventory>(
    val bukkitEvent: InventoryClickEvent,
    val guiInstance: GUIInstance<T>,
    val player: Player,
)
