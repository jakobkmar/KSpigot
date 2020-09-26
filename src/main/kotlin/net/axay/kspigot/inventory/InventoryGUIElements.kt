package net.axay.kspigot.inventory

import org.bukkit.inventory.ItemStack

interface InventoryGUISlot {
    fun onClick(clickEvent: InventoryGUIClickEvent)
}

// ELEMENT

class InventoryGUIElementData(
        val itemStack: ItemStack
)

abstract class InventoryGUIElement(
        val inventoryGUIElementData: InventoryGUIElementData
) : InventoryGUISlot

// Element implementations

open class InventoryGUIButton(
        inventoryGUIElementData: InventoryGUIElementData,
        val action: (InventoryGUIClickEvent) -> Unit,
) : InventoryGUIElement(inventoryGUIElementData) {

    override fun onClick(clickEvent: InventoryGUIClickEvent) {
        clickEvent.bukkitEvent.isCancelled = true
        action(clickEvent)
    }

}

class InventoryGUIPlaceholder(
        inventoryGUIElementData: InventoryGUIElementData
) : InventoryGUIElement(inventoryGUIElementData) {

    override fun onClick(clickEvent: InventoryGUIClickEvent) {
        clickEvent.bukkitEvent.isCancelled = true
    }

}

class InventoryGUIButtonPageChange(
        inventoryGUIElementData: InventoryGUIElementData,
        calculator: InventoryGUIPageChangeCalculator,
        onChange: ((InventoryGUIClickEvent) -> Unit)?
)
    : InventoryGUIButton(inventoryGUIElementData, {
        it.gui.currentPage?.let { currentPageInt ->

            val newPageInt = calculator.calculateNewPage(currentPageInt, it.gui.data.pages.keys)
            if (newPageInt != null) {

                onChange?.invoke(it)

                val pageChanger
                        = (it.gui.data.pages[newPageInt]?.pageChangerTo ?: it.gui.data.pages[currentPageInt]?.pageChangerFrom)
                        ?: InventoryGUIPageChanger(InventoryGUIPageChangeEffect.INSTANT)

                pageChanger.changePage(it.gui, currentPageInt, newPageInt)

            }

        }
    })

// FREE SLOT

class InventoryGUIFreeSlot : InventoryGUISlot {
    override fun onClick(clickEvent: InventoryGUIClickEvent) { /* do nothing */ }
}