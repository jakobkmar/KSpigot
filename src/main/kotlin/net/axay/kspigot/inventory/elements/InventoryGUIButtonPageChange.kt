package net.axay.kspigot.inventory.elements

import net.axay.kspigot.inventory.*
import org.bukkit.inventory.ItemStack

class InventoryGUIButtonPageChange<T : ForInventory>(
    icon: ItemStack,
    calculator: InventoryGUIPageChangeCalculator,
    onChange: ((InventoryGUIClickEvent<T>) -> Unit)?
) : InventoryGUIButton<T>(icon, {

    val currentPage = it.gui.currentPage
    val newPage = it.gui.getPage(calculator.calculateNewPage(it.gui.currentPageInt, it.gui.data.pages.keys))
    if (newPage != null) {

        val effect = (newPage.transitionTo ?: currentPage.transitionFrom)
            ?: PageChangeEffect.INSTANT

        it.gui.changePage(effect, currentPage, newPage)

        onChange?.invoke(it)

    }

})