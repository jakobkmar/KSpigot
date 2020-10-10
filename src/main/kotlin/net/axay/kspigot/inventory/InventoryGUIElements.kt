package net.axay.kspigot.inventory

import org.bukkit.inventory.ItemStack

abstract class InventoryGUISlot<T : ForInventory> {
    abstract fun onClick(clickEvent: InventoryGUIClickEvent<T>)
}

// ELEMENT

class InventoryGUIElementData(
        val itemStack: ItemStack
)

abstract class InventoryGUIElement<T : ForInventory>(
        val inventoryGUIElementData: InventoryGUIElementData
) : InventoryGUISlot<T>() {

    final override fun onClick(clickEvent: InventoryGUIClickEvent<T>) {
        clickEvent.gui.data.generalOnClick?.invoke(clickEvent)
        onClickElement(clickEvent)
    }

    protected abstract fun onClickElement(clickEvent: InventoryGUIClickEvent<T>)

}

// Element implementations

open class InventoryGUIButton<T : ForInventory>(
        inventoryGUIElementData: InventoryGUIElementData,
        val action: (InventoryGUIClickEvent<T>) -> Unit,
) : InventoryGUIElement<T>(inventoryGUIElementData) {

    override fun onClickElement(clickEvent: InventoryGUIClickEvent<T>) {
        clickEvent.bukkitEvent.isCancelled = true
        action(clickEvent)
    }

}

class InventoryGUIPlaceholder<T : ForInventory>(
        inventoryGUIElementData: InventoryGUIElementData
) : InventoryGUIElement<T>(inventoryGUIElementData) {

    override fun onClickElement(clickEvent: InventoryGUIClickEvent<T>) {
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
        val newPage = it.gui.getPage(calculator.calculateNewPage(it.gui.currentPageInt, it.gui.data.pages.keys))
        if (newPage != null) {

            val effect = (newPage.transitionTo ?: currentPage.transitionFrom)
                    ?: PageChangeEffect.INSTANT

            it.gui.changePage(effect, currentPage, newPage)

            onChange?.invoke(it)

        }

    })

class InventoryGUIButtonInventoryChange<T : ForInventory>(
        inventoryGUIElementData: InventoryGUIElementData,
        changeToGUICallback: () -> InventoryGUI<*>,
        changeToPageInt: Int?,
        onChange: ((InventoryGUIClickEvent<T>) -> Unit)?
)
    : InventoryGUIButton<T>(inventoryGUIElementData, {

        val changeToGUI = changeToGUICallback.invoke()

        val effect = (changeToGUI.data.transitionTo ?: it.gui.data.transitionFrom)
                ?: InventoryChangeEffect.INSTANT

        val changeToPage = changeToGUI.getPage(changeToPageInt) ?: changeToGUI.currentPage

        changeToGUI.changeGUI(effect, it.gui.currentPage, changeToPage)

        it.bukkitEvent.whoClicked.openGUI(changeToGUI)

        onChange?.invoke(it)

    })


// FREE SLOT

class InventoryGUIFreeSlot<T : ForInventory> : InventoryGUISlot<T>() {
    override fun onClick(clickEvent: InventoryGUIClickEvent<T>) { /* do nothing */ }
}