package net.axay.kspigot.inventory

import net.axay.kspigot.event.listen
import org.bukkit.event.inventory.InventoryClickEvent

object InventoryGUIHolder : AutoCloseable {

    private val registered = HashSet<InventoryGUI<ForInventory>>()

    fun register(inventoryGUI: InventoryGUI<ForInventory>) {
        registered.add(inventoryGUI)
    }

    fun unregister(inventoryGUI: InventoryGUI<ForInventory>) {
        registered.remove(inventoryGUI)
    }

    init {

        listen<InventoryClickEvent> {

            val clickedInv = it.clickedInventory ?: return@listen

            val inv = registered.find { search -> search.isThisInv(clickedInv) } ?: return@listen

            if (inv.isInMove) {
                it.isCancelled = true
                return@listen
            }

            val invPage = inv.currentPageInt

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