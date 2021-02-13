@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.gui.elements

import net.axay.kspigot.gui.*
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class GUISpaceCompoundElement<T : ForInventory, E> internal constructor(
    private val compound: AbstractGUISpaceCompound<T, E>
) : GUIElement<T>() {

    override fun getItemStack(slot: Int) = compound.getItemStack(slot)

    override fun onClickElement(clickEvent: GUIClickEvent<T>) {
        compound.onClickElement(clickEvent)
    }

    // the following two methods register and unregister the instance
    // for each compound element, but that is ok because it gets
    // added/removed to/from a HashSet

    override fun startUsing(gui: GUIInstance<*>) = compound.registerGUI(gui)

    override fun stopUsing(gui: GUIInstance<*>) = compound.unregisterGUI(gui)

}

class GUIRectSpaceCompound<T : ForInventory, E>(
    invType: GUIType<T>,
    iconGenerator: (E) -> ItemStack,
    onClick: ((GUIClickEvent<T>, E) -> Unit)?,
    internal val compoundWidth: Int
) : AbstractGUISpaceCompound<T, E>(invType, iconGenerator, onClick) {

    override fun handleScrollEndReached(newProgress: Int, internalSlotsSize: Int, contentSize: Int) =
        (internalSlotsSize + newProgress <= contentSize + (compoundWidth - (contentSize % compoundWidth)))

}

class GUISpaceCompound<T : ForInventory, E>(
    invType: GUIType<T>,
    iconGenerator: (E) -> ItemStack,
    onClick: ((GUIClickEvent<T>, E) -> Unit)?
) : AbstractGUISpaceCompound<T, E>(invType, iconGenerator, onClick) {

    override fun handleScrollEndReached(newProgress: Int, internalSlotsSize: Int, contentSize: Int) = false

}

abstract class AbstractGUISpaceCompound<T : ForInventory, E> internal constructor(
    val guiType: GUIType<T>,
    private val iconGenerator: (E) -> ItemStack,
    private val onClick: ((GUIClickEvent<T>, E) -> Unit)?
) {

    private val content = ArrayList<E>()
    private var currentContent: List<E> = emptyList()

    private val internalSlots: MutableList<Int> = ArrayList()

    private var scrollProgress: Int = 0

    private var contentSort: () -> Unit = { }

    private val registeredGUIs = HashSet<GUIInstance<*>>()

    private fun contentAtSlot(slot: Int) = currentContent.getOrNull(internalSlots.indexOf(slot))

    private fun recalculateCurrentContent() {

        if (scrollProgress > content.size)
            throw IllegalStateException("The scrollProgress is greater than the content size.")

        // avoid IndexOutOfBoundsException
        var sliceUntil = internalSlots.size + scrollProgress
        if (sliceUntil > content.lastIndex)
            sliceUntil = content.size

        currentContent = content.slice(scrollProgress until sliceUntil)

    }

    private fun updateOpenGUIs() {
        registeredGUIs.forEach { it.reloadCurrentPage() }
    }

    internal fun scroll(distance: Int): Boolean {
        val value = scrollProgress + distance
        return if (value >= 0) {

            // always scroll if the end of the content is not reached
            val ifScroll = if (internalSlots.size + value <= content.size) true
            // scroll further if the width of the compound is defined and the last line can be filled up
            else handleScrollEndReached(value, internalSlots.size, content.size)

            if (ifScroll) {
                scrollProgress = value
                recalculateCurrentContent()
                updateOpenGUIs()
                true
            } else false

        } else false
    }

    internal abstract fun handleScrollEndReached(newProgress: Int, internalSlotsSize: Int, contentSize: Int): Boolean

    internal fun getItemStack(slot: Int): ItemStack {
        return contentAtSlot(slot)?.let { return@let iconGenerator.invoke(it) }
            ?: ItemStack(Material.AIR)
    }

    internal fun onClickElement(clickEvent: GUIClickEvent<T>) {
        val element = contentAtSlot(clickEvent.bukkitEvent.slot) ?: kotlin.run {
            clickEvent.bukkitEvent.isCancelled = true
            return
        }
        onClick?.invoke(clickEvent, element)
    }

    internal fun addSlots(slots: InventorySlotCompound<T>) {
        slots.realSlotsWithInvType(guiType).forEach {
            if (!internalSlots.contains(it))
                internalSlots.add(it)
        }
        internalSlots.sort()
    }

    internal fun registerGUI(gui: GUIInstance<*>) {
        registeredGUIs += gui
    }

    internal fun unregisterGUI(gui: GUIInstance<*>) {
        registeredGUIs -= gui
    }

    /**
     * Defines the sort behaviour which gets applied to the content
     * automatically.
     */
    fun <R : Comparable<R>> sortContentBy(reverse: Boolean = false, selector: (E) -> R?) {
        contentSort = {
            if (!reverse) content.sortBy(selector) else content.sortByDescending(selector)
        }
        contentSort.invoke()
    }

    /**
     * Adds a new element to the compound.
     */
    fun addContent(element: E) = addContent(listOf(element))

    /**
     * Adds new elements to the compound.
     */
    fun addContent(elements: Iterable<E>) {
        content += elements
        refreshAfterContentChange()
    }

    /**
     * Removes this element from the compound.
     */
    fun removeContent(element: E) = removeContent(listOf(element))

    /**
     * Removes these elements from the compound.
     */
    fun removeContent(elements: Iterable<E>) {
        content -= elements
        refreshAfterContentChange()
    }

    /**
     * Set the content of the compound to this single element.
     */
    fun setContent(element: E) = setContent(listOf(element))

    /**
     * Set the content of the compound to these elements.
     */
    fun setContent(elements: Iterable<E>) {
        content.clear()
        content += elements
        refreshAfterContentChange()
    }

    private fun refreshAfterContentChange() {
        contentSort.invoke()
        recalculateCurrentContent()
        updateOpenGUIs()
    }

}

/**
 * A simple compound element, covering the most common
 * compound use cases.
 *
 * @see GUIPageBuilder.createSimpleCompound
 * @see GUIPageBuilder.createSimpleRectCompound
 */
open class GUICompoundElement<T : ForInventory>(
    internal val icon: ItemStack,
    internal val onClick: ((GUIClickEvent<T>) -> Unit)? = null
)