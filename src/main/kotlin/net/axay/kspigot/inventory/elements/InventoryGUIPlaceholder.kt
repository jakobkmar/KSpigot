package net.axay.kspigot.inventory.elements

import net.axay.kspigot.inventory.ForInventory
import net.axay.kspigot.inventory.InventoryGUIClickEvent
import net.axay.kspigot.inventory.InventoryGUIElement
import net.axay.kspigot.inventory.InventoryGUIElementData

class InventoryGUIPlaceholder<T : ForInventory>(
        inventoryGUIElementData: InventoryGUIElementData
) : InventoryGUIElement<T>(inventoryGUIElementData) {

    override fun onClickElement(clickEvent: InventoryGUIClickEvent<T>) {
        clickEvent.bukkitEvent.isCancelled = true
    }

}