@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.inventory.elements

import net.axay.kspigot.inventory.*
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class InventoryGUISpaceCompoundElement<T : ForInventory, E> internal constructor(
    private val compound: AbstractInventoryGUISpaceCompound<T, E>
) : InventoryGUIElement<T>() {

    override fun getItemStack(slot: Int) = compound.getItemStack(slot)

    override fun onClickElement(clickEvent: InventoryGUIClickEvent<T>) {
        compound.onClickElement(clickEvent)
    }

    override fun startUsing(gui: InventoryGUI<*>) = compound.registerGUI(gui)

    override fun stopUsing(gui: InventoryGUI<*>) = compound.unregisterGUI(gui)

}

class InventoryGUIRectSpaceCompound<T : ForInventory, E>(
    invType: InventoryType<T>,
    iconGenerator: (E) -> ItemStack,
    onClick: (InventoryGUIClickEvent<T>, E) -> Unit,
    internal val compoundWidth: Int
) : AbstractInventoryGUISpaceCompound<T, E>(invType, iconGenerator, onClick) {

    override fun handleScrollEndReached(newProgress: Int, internalSlotsSize: Int, contentSize: Int) =
        (internalSlotsSize + newProgress <= contentSize + (compoundWidth - (contentSize % compoundWidth)))

}

class InventoryGUISpaceCompound<T : ForInventory, E>(
    invType: InventoryType<T>,
    iconGenerator: (E) -> ItemStack,
    onClick: (InventoryGUIClickEvent<T>, E) -> Unit
) : AbstractInventoryGUISpaceCompound<T, E>(invType, iconGenerator, onClick) {

    override fun handleScrollEndReached(newProgress: Int, internalSlotsSize: Int, contentSize: Int) = false

}

abstract class AbstractInventoryGUISpaceCompound<T : ForInventory, E> internal constructor(
    val invType: InventoryType<T>,
    private val iconGenerator: (E) -> ItemStack,
    private val onClick: (InventoryGUIClickEvent<T>, E) -> Unit
) {

    private val content = ArrayList<E>()
    private var currentContent: List<E> = emptyList()

    private val internalSlots: MutableList<Int> = ArrayList()

    private var scrollProgress: Int = 0

    private var contentSort: () -> Unit = { }

    private val registeredGUIs = HashSet<InventoryGUI<*>>()

    private fun contentAtSlot(slot: Int) = currentContent.getOrNull(internalSlots.indexOf(slot))

    private fun recalculateCurrentContent() {

        if (scrollProgress >= content.size)
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

    internal fun onClickElement(clickEvent: InventoryGUIClickEvent<T>) {
        val element = contentAtSlot(clickEvent.bukkitEvent.slot) ?: kotlin.run {
            clickEvent.bukkitEvent.isCancelled = true
            return
        }
        onClick.invoke(clickEvent, element)
    }

    internal fun addSlots(slots: InventorySlotCompound<T>) {
        slots.realSlotsWithInvType(invType).forEach {
            if (!internalSlots.contains(it))
                internalSlots.add(it)
        }
        internalSlots.sort()
    }

    internal fun registerGUI(gui: InventoryGUI<*>) {
        registeredGUIs += gui
    }

    internal fun unregisterGUI(gui: InventoryGUI<*>) {
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
    fun addContent(element: E) {
        addContent(listOf(element))
    }

    /**
     * Adds new elements to the compound.
     */
    fun addContent(elements: Collection<E>) {

        content += elements
        contentSort.invoke()

        recalculateCurrentContent()

        updateOpenGUIs()

    }

}