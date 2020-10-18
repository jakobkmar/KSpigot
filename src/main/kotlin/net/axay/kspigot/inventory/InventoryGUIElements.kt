package net.axay.kspigot.inventory

import org.bukkit.inventory.ItemStack

abstract class InventoryGUISlot<T : ForInventory> {
    abstract fun onClick(clickEvent: InventoryGUIClickEvent<T>)
}

// ELEMENT

class InventoryGUIElementData(
    val itemStack: ItemStack
)

abstract class InventoryGUIElement<T : ForInventory>(
    val inventoryGUIElementData: InventoryGUIElementData
) : InventoryGUISlot<T>() {

    final override fun onClick(clickEvent: InventoryGUIClickEvent<T>) {
        clickEvent.gui.data.generalOnClick?.invoke(clickEvent)
        onClickElement(clickEvent)
    }

    protected abstract fun onClickElement(clickEvent: InventoryGUIClickEvent<T>)

}