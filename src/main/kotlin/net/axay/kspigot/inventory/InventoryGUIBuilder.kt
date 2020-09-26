@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package net.axay.kspigot.inventory

import net.axay.kspigot.main.KSpigot
import org.bukkit.inventory.ItemStack

inline fun <T : ForInventory> KSpigot.inventoryGUI(
        type: InventoryGUIType<T>,
        builder: InventoryGUIBuilder<T>.() -> Unit,
) = InventoryGUIBuilder(this, type).apply(builder).build()

class InventoryGUIBuilder<in T : ForInventory>(
        private val kSpigot: KSpigot,
        val type: InventoryGUIType<in T>
) {

    var title: String = ""

    private val guiSlots = HashMap<Int, InventoryGUIPage>()

    /**
     *
     */
    fun page(page: Int, builder: InventoryGUIPageBuilder<T>.() -> Unit) {
        guiSlots[page] = InventoryGUIPageBuilder(type, page).apply(builder).build()
    }

    fun build() = InventoryGUIShared(InventoryGUIData(kSpigot, type, title, guiSlots)).apply { register() }

}

class InventoryGUIPageBuilder<in T : ForInventory>(
        val type: InventoryGUIType<in T>,
        val page: Int
) {

    private val guiSlots = HashMap<Int, InventoryGUISlot>()

    var transitionTo: InventoryGUIPageChangeEffect? = null
    var transitionFrom: InventoryGUIPageChangeEffect? = null

    /**
     * A button is an item protected from any player
     * actions. If clicked, the specified [onClick]
     * function is invoked.
     */
    fun button(slots: InventorySlotCompound<T>, itemStack: ItemStack, onClick: (InventoryGUIClickEvent) -> Unit)
        = slots(slots, InventoryGUIButton(InventoryGUIElementData(itemStack), onClick))

    /**
     * An item protected from any player actions.
     * This is not a button.
     */
    fun placeholder(slots: InventorySlotCompound<T>, itemStack: ItemStack)
        = slots(slots, InventoryGUIPlaceholder(InventoryGUIElementData(itemStack)))

    /**
     * A free slot does not block any player actions.
     * The player can put items in this slot or take
     * items out of it.
     */
    fun freeSlot(slots: InventorySlotCompound<T>)
        = slots(slots, InventoryGUIFreeSlot())

    /**
     * This is a button which loads the specified
     * [toPage] if clicked.
     */
    fun pageChanger(slots: InventorySlotCompound<T>, itemStack: ItemStack, toPage: Int, onChange: ((InventoryGUIClickEvent) -> Unit)? = null)
        = slots(slots, InventoryGUIButtonPageChange(
            InventoryGUIElementData(itemStack),
            InventoryGUIPageChangeCalculator.InventoryGUIConsistentPageCalculator(toPage),
            onChange
        ))

    /**
     * This button always tries to find the previous
     * page if clicked, and if a previous page
     * exists it is loaded.
     */
    fun previousPage(slots: InventorySlotCompound<T>, itemStack: ItemStack, onChange: ((InventoryGUIClickEvent) -> Unit)? = null)
        = slots(slots, InventoryGUIButtonPageChange(
            InventoryGUIElementData(itemStack),
            InventoryGUIPageChangeCalculator.InventoryGUIPreviousPageCalculator,
            onChange
        ))

    /**
     * This button always tries to find the next
     * page if clicked, and if a next page
     * exists it is loaded.
     */
    fun nextPage(slots: InventorySlotCompound<T>, itemStack: ItemStack, onChange: ((InventoryGUIClickEvent) -> Unit)? = null)
        = slots(slots, InventoryGUIButtonPageChange(
            InventoryGUIElementData(itemStack),
            InventoryGUIPageChangeCalculator.InventoryGUINextPageCalculator,
            onChange
        ))

    private fun slots(slots: InventorySlotCompound<T>, element: InventoryGUISlot)
        = slots.withGUIType(type).forEach { curSlot ->
            curSlot.realSlotIn(type.dimensions)?.let { guiSlots[it] = element  }
        }

    fun build() = InventoryGUIPage(guiSlots, transitionTo, transitionFrom)

}