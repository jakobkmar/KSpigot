package net.axay.kspigot.inventory.elements

import net.axay.kspigot.inventory.*

class InventoryGUIButtonInventoryChange<T : ForInventory>(
    inventoryGUIElementData: InventoryGUIElementData,
    changeToGUICallback: () -> InventoryGUI<*>,
    changeToPageInt: Int?,
    onChange: ((InventoryGUIClickEvent<T>) -> Unit)?
) : InventoryGUIButton<T>(inventoryGUIElementData, {

    val changeToGUI = changeToGUICallback.invoke()

    val effect = (changeToGUI.data.transitionTo ?: it.gui.data.transitionFrom)
        ?: InventoryChangeEffect.INSTANT

    val changeToPage = changeToGUI.getPage(changeToPageInt) ?: changeToGUI.currentPage

    changeToGUI.changeGUI(effect, it.gui.currentPage, changeToPage)

    it.bukkitEvent.whoClicked.openGUI(changeToGUI)

    onChange?.invoke(it)

})