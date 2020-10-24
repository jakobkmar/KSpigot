package net.axay.kspigot.gui

import org.bukkit.entity.HumanEntity
import org.bukkit.inventory.InventoryView

fun HumanEntity.openGUI(gui: GUI<*>, page: Int? = null): InventoryView? {

    closeInventory()

    if (page != null)
        gui.loadPageUnsafe(page)

    return openInventory(gui.bukkitInventory)

}