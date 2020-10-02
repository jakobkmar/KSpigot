@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.inventory

import net.axay.kspigot.event.listen
import net.axay.kspigot.main.KSpigot
import org.bukkit.entity.HumanEntity
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

// EXTENSIONS

fun HumanEntity.openGUI(gui: InventoryGUI<*>, page: Int? = null): InventoryView? {

    closeInventory()

    if (page != null)
        gui.loadPage(page)

    return openInventory(gui.bukkitInventory)

}

// GUI HOLDER

class InventoryGUIHolder(kSpigot: KSpigot) : AutoCloseable {

    private val registered = HashSet<InventoryGUI<*>>()

    fun register(inventoryGUI: InventoryGUI<*>) {
        registered += inventoryGUI
    }

    fun unregister(inventoryGUI: InventoryGUI<*>) {
        registered -= inventoryGUI
    }

    init {

        kSpigot.listen<InventoryClickEvent> {

            val inv = registered.find { search -> search.isThisInv(it.inventory) } ?: return@listen
            val invPage = inv.currentPage

            val slot = inv.data.pages[invPage]?.slots?.get(it.slot)
            if (slot != null)
                slot.onClick(InventoryGUIClickEvent(it, inv))
            else
                it.isCancelled = true

        }
    }

    override fun close() {
        registered.forEach { inv -> inv.bukkitInventory.viewers.forEach { it.closeInventory() } }
        registered.clear()
    }

}

// EVENT

class InventoryGUIClickEvent<T : ForInventory>(
        val bukkitEvent: InventoryClickEvent,
        val gui: InventoryGUI<T>
)

/*
 * INVENTORY GUI
 */

private const val DEFAULT_PAGE = 1

class InventoryGUIData<T : ForInventory>(
        val plugin: KSpigot,
        val inventoryType: InventoryType<T>,
        val title: String?,
        val pages: Map<Int, InventoryGUIPage>
)

abstract class InventoryGUI<T : ForInventory>(
        val data: InventoryGUIData<T>
) {

    var currentPage: Int = DEFAULT_PAGE; protected set

    abstract val bukkitInventory: Inventory

    abstract fun loadPage(page: Int, offsetHorizontally: Int = 0, offsetVertically: Int = 0)

    abstract fun isThisInv(inventory: Inventory): Boolean

    abstract operator fun set(slot: InventorySlotCompound<T>, value: ItemStack)

    fun register() = data.plugin.inventoryGUIHolder.register(this)
    fun unregister() = data.plugin.inventoryGUIHolder.unregister(this)

}

// Inventory GUI implementations

class InventoryGUIShared<T : ForInventory>(
        inventoryGUIData: InventoryGUIData<T>
) : InventoryGUI<T>(inventoryGUIData) {

    override val bukkitInventory by lazy {
        val inv = data.inventoryType.createBukkitInv(null, data.title)
        loadPage(DEFAULT_PAGE)
        return@lazy inv
    }

    override fun isThisInv(inventory: Inventory) = inventory == bukkitInventory

    override fun loadPage(page: Int, offsetHorizontally: Int, offsetVertically: Int) {

        fun ifOffset(): Boolean = offsetHorizontally != 0 || offsetVertically != 0

        currentPage = page

        data.pages[page]?.slots?.let { slots ->

            val dimensions = data.inventoryType.dimensions

            if (ifOffset()) {
                dimensions.invSlots.forEach {
                    dimensions.invSlotsWithRealSlots[it.add(offsetHorizontally, offsetVertically)]?.let { slotToClear ->
                        if (dimensions.realSlots.contains(slotToClear))
                            bukkitInventory.clear(slotToClear)
                    }
                }
            } else {
                bukkitInventory.clear()
            }

            slots.forEach {
                val slot = it.value
                if (slot is InventoryGUIElement) {

                    if (ifOffset()) {
                        val invSlot = InventorySlot.fromRealSlot(it.key, dimensions)
                        if (invSlot != null) {
                            val offsetSlot = invSlot.add(offsetHorizontally, offsetVertically).realSlotIn(dimensions)
                            if (offsetSlot != null)
                                bukkitInventory.setItem(offsetSlot, slot.inventoryGUIElementData.itemStack)
                        }
                    } else {
                        bukkitInventory.setItem(it.key, slot.inventoryGUIElementData.itemStack)
                    }

                }

            }

        }

    }

    override operator fun set(slot: InventorySlotCompound<T>, value: ItemStack) {
        slot.realSlotsWithInvType(data.inventoryType).forEach {
            bukkitInventory.setItem(it, value)
        }
    }

}

class InventoryGUIPage(
        val slots: Map<Int, InventoryGUISlot>,
        transitionTo: InventoryGUIPageChangeEffect?,
        transitionFrom: InventoryGUIPageChangeEffect?
) {
    val pageChangerTo = transitionTo?.let { InventoryGUIPageChanger(it) }
    val pageChangerFrom = transitionFrom?.let { InventoryGUIPageChanger(it) }
}