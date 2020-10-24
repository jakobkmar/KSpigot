package net.axay.kspigot.gui.elements

import net.axay.kspigot.gui.*
import org.bukkit.inventory.ItemStack

class GUIButtonPageChange<T : ForInventory>(
    icon: ItemStack,
    calculator: GUIPageChangeCalculator,
    onChange: ((GUIClickEvent<T>) -> Unit)?
) : GUIButton<T>(icon, {

    val currentPage = it.gui.currentPage
    val newPage = it.gui.getPage(calculator.calculateNewPage(it.gui.currentPageInt, it.gui.data.pages.keys))
    if (newPage != null) {

        val effect = (newPage.transitionTo ?: currentPage.transitionFrom)
            ?: PageChangeEffect.INSTANT

        it.gui.changePage(effect, currentPage, newPage)

        onChange?.invoke(it)

    }

})