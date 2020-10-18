package net.axay.kspigot.inventory.elements

import net.axay.kspigot.inventory.*

class InventoryGUIButtonPageChange<T : ForInventory>(
    inventoryGUIElementData: InventoryGUIElementData,
    calculator: InventoryGUIPageChangeCalculator,
    onChange: ((InventoryGUIClickEvent<T>) -> Unit)?
) : InventoryGUIButton<T>(inventoryGUIElementData, {

    val currentPage = it.gui.currentPage
    val newPage = it.gui.getPage(calculator.calculateNewPage(it.gui.currentPageInt, it.gui.data.pages.keys))
    if (newPage != null) {

        val effect = (newPage.transitionTo ?: currentPage.transitionFrom)
            ?: PageChangeEffect.INSTANT

        it.gui.changePage(effect, currentPage, newPage)

        onChange?.invoke(it)

    }

})