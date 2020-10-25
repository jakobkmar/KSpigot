package net.axay.kspigot.gui

import net.axay.kspigot.event.listen
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryAction
import org.bukkit.event.inventory.InventoryClickEvent

object GUIHolder : AutoCloseable {

    private val registered = HashSet<GUIInstance<ForInventory>>()

    fun register(guiInstance: GUIInstance<ForInventory>) {
        registered.add(guiInstance)
    }

    fun unregister(guiInstance: GUIInstance<ForInventory>) {
        registered.remove(guiInstance)
    }

    init {

        listen<InventoryClickEvent> {

            val clickedInv = it.clickedInventory ?: return@listen

            val inv = registered.find { search -> search.isThisInv(clickedInv) } ?: return@listen

            val player = it.whoClicked as? Player ?: kotlin.run {
                it.isCancelled = true
                return@listen
            }

            if (inv.isInMove) {
                it.isCancelled = true
                return@listen
            }

            if (it.action.isGUIClick)
                inv.currentPage.slots[it.slot]?.onClick(GUIClickEvent(it, inv, player)) ?: kotlin.run {
                    it.isCancelled = true
                }
            else
                it.isCancelled = true

        }
    }

    override fun close() {
        registered.forEach { inv -> inv.bukkitInventory.viewers.forEach { it.closeInventory() } }
        registered.clear()
    }

}

private val InventoryAction.isGUIClick
    get() = this == InventoryAction.PICKUP_ALL || this == InventoryAction.PICKUP_HALF