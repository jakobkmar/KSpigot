package net.axay.kspigot.gui

import org.bukkit.entity.Player
import org.bukkit.inventory.InventoryView

fun Player.openGUI(
    gui: GUI<*>,
    page: Int? = null,
    resetCursor: Boolean = false
): InventoryView? {
    if (resetCursor) closeInventory()
    return openGUIInstance(gui.getInstance(this), page)
}

internal fun Player.openGUIInstance(guiInstance: GUIInstance<*>, page: Int? = null): InventoryView? {

    if (page != null)
        guiInstance.loadPageUnsafe(page)

    return openInventory(guiInstance.bukkitInventory)

}