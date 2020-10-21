@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package net.axay.kspigot.inventory

import net.axay.kspigot.inventory.elements.*
import org.bukkit.inventory.ItemStack
import kotlin.math.absoluteValue

fun <T : ForInventory> kSpigotGUI(
    type: InventoryType<T>,
    shared: Boolean = true,
    builder: InventoryGUIBuilder<T>.() -> Unit,
) = InventoryGUIBuilder(type, shared).apply(builder).build()

class InventoryGUIBuilder<T : ForInventory>(
    val type: InventoryType<T>,
    val shared: Boolean
) {

    var title: String = ""

    var transitionTo: InventoryChangeEffect? = null
    var transitionFrom: InventoryChangeEffect? = null

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

    internal fun build(): InventoryGUI<T> {
        val guiData = InventoryGUIData(type, title, guiSlots, transitionTo, transitionFrom, onClickElement)
        val gui =
            if (shared) InventoryGUIShared(guiData) else TODO("Currently, there is no non-shared GUI implementation available.")
        return gui.apply { register() }
    }

}

class InventoryGUIPageBuilder<T : ForInventory>(
    private val type: InventoryType<T>,
    val page: Int
) {

    private val guiSlots = HashMap<Int, InventoryGUISlot<T>>()

    var transitionTo: PageChangeEffect? = null
    var transitionFrom: PageChangeEffect? = null

    internal fun build() = InventoryGUIPage(page, guiSlots, transitionTo, transitionFrom)

    private fun defineSlots(slots: InventorySlotCompound<T>, element: InventoryGUISlot<T>) =
        slots.withInvType(type).forEach { curSlot ->
            curSlot.realSlotIn(type.dimensions)?.let { guiSlots[it] = element }
        }

    /**
     * A button is an item protected from any player
     * actions. If clicked, the specified [onClick]
     * function is invoked.
     */
    fun button(slots: InventorySlotCompound<T>, itemStack: ItemStack, onClick: (InventoryGUIClickEvent<T>) -> Unit) =
        defineSlots(slots, InventoryGUIButton(itemStack, onClick))

    /**
     * An item protected from any player actions.
     * This is not a button.
     */
    fun placeholder(slots: InventorySlotCompound<T>, itemStack: ItemStack) =
        defineSlots(slots, InventoryGUIPlaceholder(itemStack))

    /**
     * A free slot does not block any player actions.
     * The player can put items in this slot or take
     * items out of it.
     */
    fun freeSlot(slots: InventorySlotCompound<T>) = defineSlots(slots, InventoryGUIFreeSlot())

    /**
     * This is a button which loads the specified
     * [toPage] if clicked.
     */
    fun pageChanger(
        slots: InventorySlotCompound<T>,
        icon: ItemStack,
        toPage: Int,
        onChange: ((InventoryGUIClickEvent<T>) -> Unit)? = null
    ) = defineSlots(
        slots, InventoryGUIButtonPageChange(
            icon,
            InventoryGUIPageChangeCalculator.InventoryGUIConsistentPageCalculator(toPage),
            onChange
        )
    )

    /**
     * This button always tries to find the previous
     * page if clicked, and if a previous page
     * exists it is loaded.
     */
    fun previousPage(
        slots: InventorySlotCompound<T>,
        icon: ItemStack,
        onChange: ((InventoryGUIClickEvent<T>) -> Unit)? = null
    ) = defineSlots(
        slots, InventoryGUIButtonPageChange(
            icon,
            InventoryGUIPageChangeCalculator.InventoryGUIPreviousPageCalculator,
            onChange
        )
    )

    /**
     * This button always tries to find the next
     * page if clicked, and if a next page
     * exists it is loaded.
     */
    fun nextPage(
        slots: InventorySlotCompound<T>,
        icon: ItemStack,
        onChange: ((InventoryGUIClickEvent<T>) -> Unit)? = null
    ) = defineSlots(
        slots, InventoryGUIButtonPageChange(
            icon,
            InventoryGUIPageChangeCalculator.InventoryGUINextPageCalculator,
            onChange
        )
    )

    /**
     * By pressing this button, the player switches to another
     * InventoryGUI. The transition effect is applied.
     */
    fun changeGUI(
        slots: InventorySlotCompound<T>,
        icon: ItemStack,
        newGUI: () -> InventoryGUI<*>,
        newPage: Int? = null,
        onChange: ((InventoryGUIClickEvent<T>) -> Unit)? = null
    ) = defineSlots(
        slots, InventoryGUIButtonInventoryChange(
            icon,
            newGUI,
            newPage,
            onChange
        )
    )

    /**
     * Creates a new compound, holding data which can be displayed
     * in any compound space.
     */
    fun <E> createCompound(
        iconGenerator: (E) -> ItemStack,
        onClick: (clickEvent: InventoryGUIClickEvent<T>, element: E) -> Unit
    ) = InventoryGUISpaceCompound(type, iconGenerator, onClick)

    /**
     * Defines an area where the content of the given compound
     * is displayed.
     */
    fun <E> compoundSpace(
        slots: InventorySlotCompound<T>,
        compound: InventoryGUISpaceCompound<T, E>
    ) {
        compound.addSlots(slots)
        defineSlots(
            slots,
            InventoryGUISpaceCompoundElement(compound)
        )
    }

    /**
     * By pressing this button,
     * the user scrolls forward in the compound.
     */
    fun compoundScrollForwards(
        slots: InventorySlotCompound<T>,
        icon: ItemStack,
        compound: InventoryGUISpaceCompound<T, *>,
        scrollDistance: Int = compound.invType.dimensions.height
    ) = defineSlots(slots, InventoryGUISpaceCompoundScrollButton(icon, compound, scrollDistance.absoluteValue))

    /**
     * By pressing this button,
     * the user scrolls backwards in the compound.
     */
    fun compoundScrollBackwards(
        slots: InventorySlotCompound<T>,
        icon: ItemStack,
        compound: InventoryGUISpaceCompound<T, *>,
        scrollDistance: Int = compound.invType.dimensions.height
    ) = defineSlots(slots, InventoryGUISpaceCompoundScrollButton(icon, compound, -scrollDistance.absoluteValue))

}