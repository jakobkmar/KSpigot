package net.axay.kspigot.gui.elements

import net.axay.kspigot.gui.ForInventory
import net.axay.kspigot.gui.GUIClickEvent
import net.axay.kspigot.gui.GUIElement
import org.bukkit.inventory.ItemStack

open class GUIButton<T : ForInventory>(
    private val icon: ItemStack,
    private val action: (GUIClickEvent<T>) -> Unit,
) : GUIElement<T>() {

    final override fun getItemStack(slot: Int) = icon

    override fun onClickElement(clickEvent: GUIClickEvent<T>) {
        clickEvent.bukkitEvent.isCancelled = true
        action(clickEvent)
    }

}