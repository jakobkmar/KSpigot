package net.axay.kspigot.inventory.elements

import net.axay.kspigot.inventory.*
import org.bukkit.inventory.ItemStack

class InventoryGUIButtonInventoryChange<T : ForInventory>(
    icon: ItemStack,
    changeToGUICallback: () -> InventoryGUI<*>,
    changeToPageInt: Int?,
    onChange: ((InventoryGUIClickEvent<T>) -> Unit)?
) : InventoryGUIButton<T>(icon, {

    val changeToGUI = changeToGUICallback.invoke()

    val effect = (changeToGUI.data.transitionTo ?: it.gui.data.transitionFrom)
        ?: InventoryChangeEffect.INSTANT

    val changeToPage = changeToGUI.getPage(changeToPageInt) ?: changeToGUI.currentPage

    changeToGUI.changeGUI(effect, it.gui.currentPage, changeToPage)

    it.bukkitEvent.whoClicked.openGUI(changeToGUI)

    onChange?.invoke(it)

})