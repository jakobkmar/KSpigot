package net.axay.kspigot.gui

import net.axay.kspigot.event.listen
import net.axay.kspigot.extensions.bukkit.closeForViewers
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryAction
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory

object GUIHolder : AutoCloseable {

    private val registered = HashMap<Inventory, GUIInstance<ForInventory>>()

    fun register(guiInstance: GUIInstance<ForInventory>) {
        registered[guiInstance.bukkitInventory] = guiInstance
    }

    fun unregister(guiInstance: GUIInstance<ForInventory>) {
        registered -= guiInstance.bukkitInventory
    }

    init {

        listen<InventoryClickEvent> {

            val clickedInv = it.clickedInventory ?: return@listen

            val inv = registered[clickedInv] ?: return@listen

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
        registered.keys.forEach { it.closeForViewers() }
        registered.clear()
    }

}

private val InventoryAction.isGUIClick
    get() = this == InventoryAction.PICKUP_ALL || this == InventoryAction.PICKUP_HALF