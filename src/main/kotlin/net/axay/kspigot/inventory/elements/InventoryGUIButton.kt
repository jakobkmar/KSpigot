package net.axay.kspigot.inventory.elements

import net.axay.kspigot.inventory.*
import org.bukkit.inventory.ItemStack

open class InventoryGUIButton<T : ForInventory>(
    private val icon: ItemStack,
    val action: (InventoryGUIClickEvent<T>) -> Unit,
) : InventoryGUIElement<T>() {

    override fun getItemStack(gui: InventoryGUI<*>, slot: Int) = icon

    override fun onClickElement(clickEvent: InventoryGUIClickEvent<T>) {
        clickEvent.bukkitEvent.isCancelled = true
        action(clickEvent)
    }

}