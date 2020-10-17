package net.axay.kspigot.inventory.elements

import net.axay.kspigot.inventory.ForInventory
import net.axay.kspigot.inventory.InventoryGUIClickEvent
import net.axay.kspigot.inventory.InventoryGUIElement
import net.axay.kspigot.inventory.InventoryGUIElementData

open class InventoryGUIButton<T : ForInventory>(
        inventoryGUIElementData: InventoryGUIElementData,
        val action: (InventoryGUIClickEvent<T>) -> Unit,
) : InventoryGUIElement<T>(inventoryGUIElementData) {

    override fun onClickElement(clickEvent: InventoryGUIClickEvent<T>) {
        clickEvent.bukkitEvent.isCancelled = true
        action(clickEvent)
    }

}