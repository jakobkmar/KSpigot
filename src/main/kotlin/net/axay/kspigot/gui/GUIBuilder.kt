@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package net.axay.kspigot.gui

import net.axay.kspigot.gui.elements.*
import org.bukkit.inventory.ItemStack
import kotlin.math.absoluteValue

fun <T : ForInventory> kSpigotGUI(
    type: GUIType<T>,
    guiCreator: GUICreator<T> = IndividualGUICreator(),
    builder: GUIBuilder<T>.() -> Unit,
) = GUIBuilder(type, guiCreator).apply(builder).build()

class GUIBuilder<T : ForInventory>(
    val type: GUIType<T>,
    private val guiCreator: GUICreator<T>,
) {
    private val guiPages = HashMap<Int, GUIPage<T>>()

    /**
     * The title of this GUI.
     * This title will be visible for every page of
     * this GUI.
     */
    var title: String = ""

    /**
     * The transition applied, if another GUI redirects to
     * this GUI.
     */
    var transitionTo: InventoryChangeEffect? = null

    /**
     * The transition applied, if this GUI redirects to
     * another GUI and the other GUI has no transitionTo
     * value defined.
     */
    var transitionFrom: InventoryChangeEffect? = null

    /**
     * The default page will be loaded first for every
     * GUI instance.
     */
    var defaultPage = 1

    private var onClickElement: ((GUIClickEvent<T>) -> Unit)? = null

    private var onClose: ((GUICloseEvent<T>) -> Unit)? = null

    /**
     * Opens the builder for a new page and adds
     * the new page to the GUI.
     * @param page The index of the page.
     */
    fun page(page: Int, builder: GUIPageBuilder<T>.() -> Unit) {
        guiPages[page] = GUIPageBuilder(type, page).apply(builder).build()
    }

    /**
     * A callback executed when the user clicks on
     * any GUI elements on any page in this GUI.
     */
    fun onClickElement(onClick: (GUIClickEvent<T>) -> Unit) {
        onClickElement = onClick
    }

    /**
     * A callback executed when the user closes
     * the inventory.
     */
    fun onClose(onClose: (GUICloseEvent<T>) -> Unit) {
        this.onClose = onClose
    }

    internal fun build() = guiCreator.createInstance(
        GUIData(type, title, guiPages, defaultPage, transitionTo, transitionFrom, onClickElement, onClose)
    )
}

class GUIPageBuilder<T : ForInventory>(
    private val type: GUIType<T>,
    val page: Int,
) {
    private val guiSlots = HashMap<Int, GUISlot<T>>()

    var transitionTo: PageChangeEffect? = null
    var transitionFrom: PageChangeEffect? = null

    internal fun build() = GUIPage(page, guiSlots, transitionTo, transitionFrom)

    private fun defineSlots(slots: InventorySlotCompound<T>, element: GUISlot<T>) =
        slots.withInvType(type).forEach { curSlot ->
            curSlot.realSlotIn(type.dimensions)?.let { guiSlots[it] = element }
        }

    /**
     * A button is an item protected from any player
     * actions. If clicked, the specified [onClick]
     * function is invoked.
     */
    fun button(slots: InventorySlotCompound<T>, itemStack: ItemStack, onClick: (GUIClickEvent<T>) -> Unit) =
        defineSlots(slots, GUIButton(itemStack, onClick))

    /**
     * An item protected from any player actions.
     * This is not a button.
     */
    fun placeholder(slots: InventorySlotCompound<T>, itemStack: ItemStack) =
        defineSlots(slots, GUIPlaceholder(itemStack))

    /**
     * A free slot does not block any player actions.
     * The player can put items in this slot or take
     * items out of it.
     */
    fun freeSlot(slots: InventorySlotCompound<T>) = defineSlots(slots, GUIFreeSlot())

    /**
     * This is a button which loads the specified
     * [toPage] if clicked.
     */
    @Deprecated("Use the new function instead.", ReplaceWith("pageChanger(slots, icon, toPage, null, onChange)"))
    fun pageChanger(
        slots: InventorySlotCompound<T>,
        icon: ItemStack,
        toPage: Int,
        onChange: ((GUIClickEvent<T>) -> Unit)? = null,
    ) = pageChanger(slots, icon, toPage, null, onChange)

    /**
     * This is a button which loads the specified
     * [toPage] if clicked.
     */
    fun pageChanger(
        slots: InventorySlotCompound<T>,
        icon: ItemStack,
        toPage: Int,
        shouldChange: ((GUIClickEvent<T>) -> Boolean)? = null,
        onChange: ((GUIClickEvent<T>) -> Unit)? = null,
    ) = defineSlots(
        slots, GUIButtonPageChange(
            icon,
            GUIPageChangeCalculator.GUIConsistentPageCalculator(toPage),
            shouldChange,
            onChange
        )
    )

    /**
     * This button always tries to find the previous
     * page if clicked, and if a previous page
     * exists it is loaded.
     */
    @Deprecated("Use the new function instead.", ReplaceWith("previousPage(slots, icon, null, onChange)"))
    fun previousPage(
        slots: InventorySlotCompound<T>,
        icon: ItemStack,
        onChange: ((GUIClickEvent<T>) -> Unit)? = null,
    ) = previousPage(slots, icon, null, onChange)

    /**
     * This button always tries to find the previous
     * page if clicked, and if a previous page
     * exists it is loaded.
     */
    fun previousPage(
        slots: InventorySlotCompound<T>,
        icon: ItemStack,
        shouldChange: ((GUIClickEvent<T>) -> Boolean)? = null,
        onChange: ((GUIClickEvent<T>) -> Unit)? = null,
    ) = defineSlots(
        slots, GUIButtonPageChange(
            icon,
            GUIPageChangeCalculator.GUIPreviousPageCalculator,
            shouldChange,
            onChange
        )
    )

    /**
     * This button always tries to find the next
     * page if clicked, and if a next page
     * exists it is loaded.
     */
    @Deprecated("Use the new function instead.", ReplaceWith("nextPage(slots, icon, null, onChange)"))
    fun nextPage(
        slots: InventorySlotCompound<T>,
        icon: ItemStack,
        onChange: ((GUIClickEvent<T>) -> Unit)? = null,
    ) = nextPage(slots, icon, null, onChange)

    /**
     * This button always tries to find the next
     * page if clicked, and if a next page
     * exists it is loaded.
     */
    fun nextPage(
        slots: InventorySlotCompound<T>,
        icon: ItemStack,
        shouldChange: ((GUIClickEvent<T>) -> Boolean)? = null,
        onChange: ((GUIClickEvent<T>) -> Unit)? = null,
    ) = defineSlots(
        slots, GUIButtonPageChange(
            icon,
            GUIPageChangeCalculator.GUINextPageCalculator,
            shouldChange,
            onChange
        )
    )

    /**
     * By pressing this button, the player switches to another
     * GUI. The transition effect is applied.
     */
    fun changeGUI(
        slots: InventorySlotCompound<T>,
        icon: ItemStack,
        newGUI: () -> GUI<*>,
        newPage: Int? = null,
        onChange: ((GUIClickEvent<T>) -> Unit)? = null,
    ) = defineSlots(
        slots, GUIButtonInventoryChange(
            icon,
            newGUI,
            newPage,
            onChange
        )
    )

    /**
     * Creates a new compound, holding simple compound elements.
     */
    fun createSimpleCompound() = createCompound<GUICompoundElement<T>>(
        iconGenerator = { it.icon },
        onClick = { clickEvent, element -> element.onClick?.invoke(clickEvent) }
    )

    /**
     * Creates a new compound, holding data which can be displayed
     * in any compound space.
     */
    fun <E> createCompound(
        iconGenerator: (E) -> ItemStack,
        onClick: ((clickEvent: GUIClickEvent<T>, element: E) -> Unit)? = null,
    ) = GUISpaceCompound(type, iconGenerator, onClick)

    /**
     * Defines an area where the content of the given compound
     * is displayed.
     */
    fun <E> compoundSpace(
        slots: InventorySlotCompound<T>,
        compound: GUISpaceCompound<T, E>,
    ) {
        compound.addSlots(slots)
        defineSlots(
            slots,
            GUISpaceCompoundElement(compound)
        )
    }

    /**
     * Creates a new compound, holding simple compound elements.
     * This compound is strictly a rectangle.
     * The space is automatically defined.
     *
     * This method sets the element type to
     * [GUICompoundElement]. The iconGenerator and onClick callback
     * are automatically defined.
     */
    fun createSimpleRectCompound(
        fromSlot: SingleInventorySlot<out T>,
        toSlot: SingleInventorySlot<out T>,
    ) = createRectCompound<GUICompoundElement<T>>(
        fromSlot, toSlot,
        iconGenerator = { it.icon },
        onClick = { clickEvent, element -> element.onClick?.invoke(clickEvent) }
    )

    /**
     * Creates a new compound, holding custom element data.
     * This compound is strictly a rectangle.
     * The space is automatically defined.
     */
    fun <E> createRectCompound(
        fromSlot: SingleInventorySlot<out T>,
        toSlot: SingleInventorySlot<out T>,
        iconGenerator: (E) -> ItemStack,
        onClick: ((clickEvent: GUIClickEvent<T>, element: E) -> Unit)? = null,
    ): GUIRectSpaceCompound<T, E> {
        val rectSlotCompound = fromSlot rectTo toSlot
        return GUIRectSpaceCompound(
            type,
            iconGenerator,
            onClick,
            (rectSlotCompound.endInclusive.slotInRow - rectSlotCompound.start.slotInRow) + 1
        ).apply {
            addSlots(rectSlotCompound)
            defineSlots(
                rectSlotCompound,
                GUISpaceCompoundElement(this)
            )
        }
    }

    /**
     * By pressing this button,
     * the user scrolls forwards or backwards in the compound.
     */
    fun compoundScroll(
        slots: InventorySlotCompound<T>,
        icon: ItemStack,
        compound: GUISpaceCompound<T, *>,
        scrollDistance: Int = 1,
        scrollTimes: Int = 1,
        reverse: Boolean = false,
    ) = defineSlots(
        slots,
        GUISpaceCompoundScrollButton(
            icon,
            compound,
            scrollDistance = scrollDistance.absoluteValue,
            scrollTimes = scrollTimes,
            reverse
        )
    )

    /**
     * By pressing this button,
     * the user scrolls forwards or backwards in the compound.
     */
    fun compoundScroll(
        slots: InventorySlotCompound<T>,
        icon: ItemStack,
        compound: GUIRectSpaceCompound<T, *>,
        scrollTimes: Int = 1,
        scrollLines: Int = 1,
        reverse: Boolean = false,
    ) = defineSlots(
        slots,
        GUISpaceCompoundScrollButton(
            icon,
            compound,
            scrollTimes = scrollTimes,
            scrollLines = scrollLines,
            reverse
        )
    )
}
