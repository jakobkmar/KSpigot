package net.axay.kspigot.inventory

import org.bukkit.entity.HumanEntity
import org.bukkit.inventory.InventoryView

fun HumanEntity.openGUI(gui: InventoryGUI<*>, page: Int? = null): InventoryView? {

    closeInventory()

    if (page != null)
        gui.loadPageUnsafe(page)

    return openInventory(gui.bukkitInventory)

}