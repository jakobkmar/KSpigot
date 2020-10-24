package net.axay.kspigot.gui.elements

import net.axay.kspigot.gui.*
import org.bukkit.inventory.ItemStack

class GUIButtonInventoryChange<T : ForInventory>(
    icon: ItemStack,
    changeToGUICallback: () -> GUI<*>,
    changeToPageInt: Int?,
    onChange: ((GUIClickEvent<T>) -> Unit)?
) : GUIButton<T>(icon, {

    val changeToGUI = changeToGUICallback.invoke()

    val effect = (changeToGUI.data.transitionTo ?: it.gui.data.transitionFrom)
        ?: InventoryChangeEffect.INSTANT

    val changeToPage = changeToGUI.getPage(changeToPageInt) ?: changeToGUI.currentPage

    changeToGUI.changeGUI(effect, it.gui.currentPage, changeToPage)

    it.bukkitEvent.whoClicked.openGUI(changeToGUI)

    onChange?.invoke(it)

})