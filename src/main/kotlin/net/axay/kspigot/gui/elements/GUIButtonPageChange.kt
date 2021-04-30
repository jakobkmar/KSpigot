package net.axay.kspigot.gui.elements

import net.axay.kspigot.gui.*
import org.bukkit.inventory.ItemStack

class GUIButtonPageChange<T : ForInventory>(
    icon: ItemStack,
    calculator: GUIPageChangeCalculator,
    shouldChange: ((GUIClickEvent<T>) -> Boolean)?,
    onChange: ((GUIClickEvent<T>) -> Unit)?
) : GUIButton<T>(icon, {

    val currentPage = it.guiInstance.currentPage
    val newPage = it.guiInstance.getPage(
        calculator.calculateNewPage(
            it.guiInstance.currentPageInt,
            it.guiInstance.gui.data.pages.keys
        )
    )
    if (newPage != null) {

        val changePage = shouldChange?.invoke(it) ?: true

        if (changePage) {
            val effect = (newPage.transitionTo ?: currentPage.transitionFrom)
                ?: PageChangeEffect.INSTANT

            it.guiInstance.changePage(effect, currentPage, newPage)
            onChange?.invoke(it)
        }
    }

})
