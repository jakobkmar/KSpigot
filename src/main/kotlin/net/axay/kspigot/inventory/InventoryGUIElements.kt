package net.axay.kspigot.inventory

import org.bukkit.inventory.ItemStack

abstract class InventoryGUISlot<T : ForInventory> {
    abstract fun onClick(clickEvent: InventoryGUIClickEvent<T>)
}

// ELEMENT

abstract class InventoryGUIElement<T : ForInventory> : InventoryGUISlot<T>() {

    abstract fun getItemStack(slot: Int): ItemStack

    final override fun onClick(clickEvent: InventoryGUIClickEvent<T>) {
        clickEvent.gui.data.generalOnClick?.invoke(clickEvent)
        onClickElement(clickEvent)
    }

    protected abstract fun onClickElement(clickEvent: InventoryGUIClickEvent<T>)

    internal open fun startUsing(gui: InventoryGUI<*>) { }
    internal open fun stopUsing(gui: InventoryGUI<*>) { }

}