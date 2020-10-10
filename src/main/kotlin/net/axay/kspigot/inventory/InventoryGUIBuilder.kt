@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package net.axay.kspigot.inventory

import net.axay.kspigot.main.KSpigot
import org.bukkit.inventory.ItemStack

fun <T : ForInventory> KSpigot.inventoryGUI(
        type: InventoryType<T>,
        builder: InventoryGUIBuilder<T>.() -> Unit,
) = InventoryGUIBuilder(this, type).apply(builder).build()

class InventoryGUIBuilder<T : ForInventory>(
        private val kSpigot: KSpigot,
        val type: InventoryType<T>
) {

    var title: String = ""

    var transitionTo: InventoryGUIPageChangeEffect? = null
    var transitionFrom: InventoryGUIPageChangeEffect? = null

    private val guiSlots = HashMap<Int, InventoryGUIPage<T>>()

    private var onClickElement: ((InventoryGUIClickEvent<T>) -> Unit)? = null

    /**
     * Opens the builder for a new page and adds
     * the new page to the GUI.
     * @param page The index of the page.
     */
    fun page(page: Int, builder: InventoryGUIPageBuilder<T>.() -> Unit) {
        guiSlots[page] = InventoryGUIPageBuilder(type, page).apply(builder).build()
    }

    fun onClickElement(onClick: (InventoryGUIClickEvent<T>) -> Unit) {
        onClickElement = onClick
    }

    internal fun build() = InventoryGUIShared(
            InventoryGUIData(kSpigot, type, title, guiSlots, transitionTo, transitionFrom, onClickElement)
    ).apply { register() }

}

class InventoryGUIPageBuilder<T : ForInventory>(
        val type: InventoryType<T>,
        val page: Int
) {

    private val guiSlots = HashMap<Int, InventoryGUISlot<T>>()

    var transitionTo: InventoryGUIPageChangeEffect? = null
    var transitionFrom: InventoryGUIPageChangeEffect? = null

    internal fun build() = InventoryGUIPage(page, guiSlots, transitionTo, transitionFrom)

    /**
     * A button is an item protected from any player
     * actions. If clicked, the specified [onClick]
     * function is invoked.
     */
    fun button(slots: InventorySlotCompound<T>, itemStack: ItemStack, onClick: (InventoryGUIClickEvent<T>) -> Unit)
        = defineSlots(slots, InventoryGUIButton(InventoryGUIElementData(itemStack), onClick))

    /**
     * An item protected from any player actions.
     * This is not a button.
     */
    fun placeholder(slots: InventorySlotCompound<T>, itemStack: ItemStack)
        = defineSlots(slots, InventoryGUIPlaceholder(InventoryGUIElementData(itemStack)))

    /**
     * A free slot does not block any player actions.
     * The player can put items in this slot or take
     * items out of it.
     */
    fun freeSlot(slots: InventorySlotCompound<T>)
        = defineSlots(slots, InventoryGUIFreeSlot())

    /**
     * This is a button which loads the specified
     * [toPage] if clicked.
     */
    fun pageChanger(slots: InventorySlotCompound<T>, itemStack: ItemStack, toPage: Int, onChange: ((InventoryGUIClickEvent<T>) -> Unit)? = null)
        = defineSlots(slots, InventoryGUIButtonPageChange(
            InventoryGUIElementData(itemStack),
            InventoryGUIPageChangeCalculator.InventoryGUIConsistentPageCalculator(toPage),
            onChange
        ))

    /**
     * This button always tries to find the previous
     * page if clicked, and if a previous page
     * exists it is loaded.
     */
    fun previousPage(slots: InventorySlotCompound<T>, itemStack: ItemStack, onChange: ((InventoryGUIClickEvent<T>) -> Unit)? = null)
        = defineSlots(slots, InventoryGUIButtonPageChange(
            InventoryGUIElementData(itemStack),
            InventoryGUIPageChangeCalculator.InventoryGUIPreviousPageCalculator,
            onChange
        ))

    /**
     * This button always tries to find the next
     * page if clicked, and if a next page
     * exists it is loaded.
     */
    fun nextPage(slots: InventorySlotCompound<T>, itemStack: ItemStack, onChange: ((InventoryGUIClickEvent<T>) -> Unit)? = null)
        = defineSlots(slots, InventoryGUIButtonPageChange(
            InventoryGUIElementData(itemStack),
            InventoryGUIPageChangeCalculator.InventoryGUINextPageCalculator,
            onChange
        ))

    /**
     * By pressing this button, the player switches to another
     * InventoryGUI. The transition effect is applied.
     */
    fun changeGUI(slots: InventorySlotCompound<T>, itemStack: ItemStack, newGUI: InventoryGUI<*>, newPage: Int? = null, onChange: ((InventoryGUIClickEvent<T>) -> Unit)? = null)
        = defineSlots(slots, InventoryGUIButtonInventoryChange(
            InventoryGUIElementData(itemStack),
            newGUI,
            newPage,
            onChange
        ))

    private fun defineSlots(slots: InventorySlotCompound<T>, element: InventoryGUISlot<T>)
        = slots.withInvType(type).forEach { curSlot ->
            curSlot.realSlotIn(type.dimensions)?.let { guiSlots[it] = element  }
        }

}