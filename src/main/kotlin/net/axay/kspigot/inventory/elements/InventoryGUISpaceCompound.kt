@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package net.axay.kspigot.inventory.elements

import net.axay.kspigot.inventory.*
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class InventoryGUISpaceCompoundElement<T : ForInventory, E>(
    private val compound: InventoryGUISpaceCompound<T, E>
) : InventoryGUIElement<T>() {

    override fun getItemStack(slot: Int) = compound.getItemStack(slot)

    override fun onClickElement(clickEvent: InventoryGUIClickEvent<T>) {
        compound.onClickElement(clickEvent)
    }

}

class InventoryGUISpaceCompound<T : ForInventory, E> internal constructor(
    private val invType: InventoryType<T>,
    private val iconGenerator: (E) -> ItemStack,
    private val onClick: (InventoryGUIClickEvent<T>, E) -> Unit
) {

    private val content = ArrayList<E>()
    private val internalSlots = ArrayList<Int>()

    private var contentSort: () -> Unit = { }

    // TODO add "scrolling" functionality

    // TODO take current scroll progress into account
    private fun contentAtSlot(slot: Int) = content.getOrNull(internalSlots.indexOf(slot))

    internal fun getItemStack(slot: Int): ItemStack {
        return contentAtSlot(slot)?.let { return@let iconGenerator.invoke(it) }
            ?: ItemStack(Material.AIR)
    }

    internal fun onClickElement(clickEvent: InventoryGUIClickEvent<T>) {
        val element = contentAtSlot(clickEvent.bukkitEvent.slot) ?: return
        onClick.invoke(clickEvent, element)
    }

    internal fun addSlots(slots: InventorySlotCompound<T>) {
        internalSlots += slots.realSlotsWithInvType(invType)
        internalSlots.sort()
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
        // TODO reload GUIs using this compound at this time
    }

}