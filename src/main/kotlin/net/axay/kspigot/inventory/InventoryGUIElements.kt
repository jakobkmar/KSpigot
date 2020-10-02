package net.axay.kspigot.inventory

import org.bukkit.inventory.ItemStack

interface InventoryGUISlot<T : ForInventory> {
    fun onClick(clickEvent: InventoryGUIClickEvent<T>)
}

// ELEMENT

class InventoryGUIElementData(
        val itemStack: ItemStack
)

abstract class InventoryGUIElement<T : ForInventory>(
        val inventoryGUIElementData: InventoryGUIElementData
) : InventoryGUISlot<T>

// Element implementations

open class InventoryGUIButton<T : ForInventory>(
        inventoryGUIElementData: InventoryGUIElementData,
        val action: (InventoryGUIClickEvent<T>) -> Unit,
) : InventoryGUIElement<T>(inventoryGUIElementData) {

    override fun onClick(clickEvent: InventoryGUIClickEvent<T>) {
        clickEvent.bukkitEvent.isCancelled = true
        action(clickEvent)
    }

}

class InventoryGUIPlaceholder<T : ForInventory>(
        inventoryGUIElementData: InventoryGUIElementData
) : InventoryGUIElement<T>(inventoryGUIElementData) {

    override fun onClick(clickEvent: InventoryGUIClickEvent<T>) {
        clickEvent.bukkitEvent.isCancelled = true
    }

}

class InventoryGUIButtonPageChange<T : ForInventory>(
        inventoryGUIElementData: InventoryGUIElementData,
        calculator: InventoryGUIPageChangeCalculator,
        onChange: ((InventoryGUIClickEvent<T>) -> Unit)?
)
    : InventoryGUIButton<T>(inventoryGUIElementData, {

        val currentPage = it.gui.currentPage

        val newPageInt = calculator.calculateNewPage(currentPage, it.gui.data.pages.keys)
        if (newPageInt != null) {

            onChange?.invoke(it)

            val pageChanger
                    = (it.gui.data.pages[newPageInt]?.pageChangerTo ?: it.gui.data.pages[currentPage]?.pageChangerFrom)
                    ?: InventoryGUIPageChanger(InventoryGUIPageChangeEffect.INSTANT)

            pageChanger.changePage(it.gui, currentPage, newPageInt)

        }

    })

// FREE SLOT

class InventoryGUIFreeSlot<T : ForInventory> : InventoryGUISlot<T> {
    override fun onClick(clickEvent: InventoryGUIClickEvent<T>) { /* do nothing */ }
}