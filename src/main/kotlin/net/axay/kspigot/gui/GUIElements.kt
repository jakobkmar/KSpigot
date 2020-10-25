package net.axay.kspigot.gui

import org.bukkit.inventory.ItemStack

abstract class GUISlot<T : ForInventory> {
    abstract fun onClick(clickEvent: GUIClickEvent<T>)
}

// ELEMENT

abstract class GUIElement<T : ForInventory> : GUISlot<T>() {

    abstract fun getItemStack(slot: Int): ItemStack

    final override fun onClick(clickEvent: GUIClickEvent<T>) {
        clickEvent.guiInstance.gui.data.generalOnClick?.invoke(clickEvent)
        onClickElement(clickEvent)
    }

    protected abstract fun onClickElement(clickEvent: GUIClickEvent<T>)

    internal open fun startUsing(gui: GUIInstance<*>) {}
    internal open fun stopUsing(gui: GUIInstance<*>) {}

}