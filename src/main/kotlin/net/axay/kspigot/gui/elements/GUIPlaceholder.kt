package net.axay.kspigot.gui.elements

import net.axay.kspigot.gui.ForInventory
import net.axay.kspigot.gui.GUIClickEvent
import net.axay.kspigot.gui.GUIElement
import org.bukkit.inventory.ItemStack

class GUIPlaceholder<T : ForInventory>(
    private val icon: ItemStack
) : GUIElement<T>() {

    override fun getItemStack(slot: Int) = icon

    override fun onClickElement(clickEvent: GUIClickEvent<T>) {
        clickEvent.bukkitEvent.isCancelled = true
    }

}