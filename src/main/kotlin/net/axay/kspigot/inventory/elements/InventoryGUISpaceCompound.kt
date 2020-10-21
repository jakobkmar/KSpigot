@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package net.axay.kspigot.inventory.elements

import net.axay.kspigot.inventory.*
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class InventoryGUISpaceCompoundElement<T : ForInventory, E> internal constructor(
    private val compound: InventoryGUISpaceCompound<T, E>
) : InventoryGUIElement<T>() {

    override fun getItemStack(slot: Int) = compound.getItemStack(slot)

    override fun onClickElement(clickEvent: InventoryGUIClickEvent<T>) {
        compound.onClickElement(clickEvent)
    }

    override fun startUsing(gui: InventoryGUI<*>) = compound.registerGUI(gui)

    override fun stopUsing(gui: InventoryGUI<*>) = compound.unregisterGUI(gui)

}

class InventoryGUISpaceCompound<T : ForInventory, E>(
    val invType: InventoryType<T>,
    private val iconGenerator: (E) -> ItemStack,
    private val onClick: (InventoryGUIClickEvent<T>, E) -> Unit
) {

    private val content = ArrayList<E>()

    private val realInternalSlots = ArrayList<Int>()

    private val currentInternalSlots: List<Int>
        get() {

            val result = ArrayList(realInternalSlots)

            var more = 1
            while (content.size > result.size) {
                result += realInternalSlots.mapTo(ArrayList()) { it + (more * invType.dimensions.slotAmount) }
                more++
            }

            return result

        }

    internal var scrolledLines: Int = 0; private set

    fun scroll(distance: Int): Boolean {
        val value = scrolledLines + distance
        return if (
            value >= 0 &&
            ((value - 1) * invType.dimensions.width) < content.size
        ) {
            scrolledLines = value
            onChange()
            true
        } else false
    }

    private var contentSort: () -> Unit = { }

    private val registeredGUIs = HashSet<InventoryGUI<*>>()

    private fun translateSlot(slot: Int) = (scrolledLines * invType.dimensions.width) + slot

    private fun contentAtSlot(slot: Int) = content.getOrNull(
        realInternalSlots.indexOf(translateSlot(slot))
    )

    internal fun getItemStack(slot: Int): ItemStack {
        return contentAtSlot(slot)?.let { return@let iconGenerator.invoke(it) }
            ?: ItemStack(Material.AIR)
    }

    internal fun onClickElement(clickEvent: InventoryGUIClickEvent<T>) {
        val element = contentAtSlot(clickEvent.bukkitEvent.slot) ?: return
        onClick.invoke(clickEvent, element)
    }

    internal fun addSlots(slots: InventorySlotCompound<T>) {
        slots.realSlotsWithInvType(invType).forEach {
            if (!realInternalSlots.contains(it))
                realInternalSlots.add(it)
        }
        realInternalSlots.sort()
    }

    internal fun onChange() {
        registeredGUIs.forEach { it.reloadCurrentPage() }
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
        onChange()
    }

}