@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.inventory

import net.axay.kspigot.event.listen
import net.axay.kspigot.event.register
import net.axay.kspigot.main.KSpigot
import org.bukkit.entity.HumanEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryInteractEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryView

// EXTENSIONS

fun HumanEntity.openGUI(gui: InventoryGUI, page: Int = 1): InventoryView? {
    closeInventory()
    gui.loadPage(page)
    return openInventory(gui.bukkitInventory)
}

// GUI HOLDER

class InventoryGUIHolder(kSpigot: KSpigot) : AutoCloseable {

    private val registered = HashSet<InventoryGUI>()

    fun register(inventoryGUI: InventoryGUI) {
        registered += inventoryGUI
    }

    fun unregister(inventoryGUI: InventoryGUI) {
        registered -= inventoryGUI
    }

    init {

        object : Listener {
            @EventHandler
            private fun onInteract(event: InventoryInteractEvent) {
                println("interact geht hier")
            }
        }.register(kSpigot)

        kSpigot.listen<InventoryClickEvent> {

            val inv = registered.find { search -> search.isThisInv(it.inventory) } ?: return@listen
            val invPage = inv.currentPage ?: return@listen

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

class InventoryGUIClickEvent(
        val bukkitEvent: InventoryClickEvent,
        val gui: InventoryGUI,
)

/*
 * INVENTORY GUI
 */

class InventoryGUIData(
        val plugin: KSpigot,
        val inventoryGUIType: InventoryGUIType<*>,
        val title: String?,
        val pages: Map<Int, InventoryGUIPage>
)

abstract class InventoryGUI(
        val data: InventoryGUIData
) {

    var currentPage: Int? = null; protected set

    abstract val bukkitInventory: Inventory

    abstract fun loadPage(page: Int, offsetHorizontally: Int = 0, offsetVertically: Int = 0)

    abstract fun isThisInv(inventory: Inventory): Boolean

    fun register() = data.plugin.inventoryGUIHolder.register(this)
    fun unregister() = data.plugin.inventoryGUIHolder.unregister(this)

}

// Inventory GUI implementations

class InventoryGUIShared(
        inventoryGUIData: InventoryGUIData
) : InventoryGUI(inventoryGUIData) {

    override val bukkitInventory by lazy { data.inventoryGUIType.createBukkitInv(null, data.title) }

    override fun isThisInv(inventory: Inventory) = inventory == bukkitInventory

    override fun loadPage(page: Int, offsetHorizontally: Int, offsetVertically: Int) {

        fun ifOffset(): Boolean = offsetHorizontally != 0 || offsetVertically != 0

        currentPage = page

        data.pages[page]?.slots?.let { slots ->

            val dimensions = data.inventoryGUIType.dimensions

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

}

class InventoryGUIPage(
        val slots: Map<Int, InventoryGUISlot>,
        transitionTo: InventoryGUIPageChangeEffect?,
        transitionFrom: InventoryGUIPageChangeEffect?
) {
    val pageChangerTo = transitionTo?.let { InventoryGUIPageChanger(it) }
    val pageChangerFrom = transitionFrom?.let { InventoryGUIPageChanger(it) }
}