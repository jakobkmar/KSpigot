package net.axay.kspigot.inventory.elements

import net.axay.kspigot.inventory.*
import org.bukkit.inventory.ItemStack

class InventoryGUIPlaceholder<T : ForInventory>(
    private val icon: ItemStack
) : InventoryGUIElement<T>() {

    override fun getItemStack(slot: Int) = icon

    override fun onClickElement(clickEvent: InventoryGUIClickEvent<T>) {
        clickEvent.bukkitEvent.isCancelled = true
    }

}