package net.axay.kspigot.gui

import net.axay.kspigot.event.listen
import net.axay.kspigot.extensions.bukkit.closeForViewers
import org.bukkit.entity.Player
import org.bukkit.event.inventory.*
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
            val gui = registered[clickedInv] ?: return@listen
            val player = it.playerOrCancel ?: return@listen

            if (gui.isInMove) {
                it.isCancelled = true
                return@listen
            }

            if (it.action.isGUIClick)
                gui.currentPage.slots[it.slot]?.onClick(GUIClickEvent(it, gui, player)) ?: kotlin.run {
                    it.isCancelled = true
                }
            else
                it.isCancelled = true
        }

        listen<InventoryDragEvent> {
            val inv = it.inventory
            val gui = registered[inv] ?: return@listen
            val player = it.playerOrCancel ?: return@listen

            var ifCancel = false

            for (slotIndex in it.inventorySlots) {
                val slot = gui.currentPage.slots[slotIndex]
                if (slot == null) {
                    ifCancel = true
                    break
                }
                val clickEvent = InventoryClickEvent(
                    it.view,
                    it.view.getSlotType(slotIndex),
                    slotIndex,
                    ClickType.UNKNOWN,
                    InventoryAction.UNKNOWN
                )

                slot.onClick(GUIClickEvent(clickEvent, gui, player))

                if (clickEvent.isCancelled) {
                    ifCancel = true
                    break
                }
            }

            if (ifCancel)
                it.isCancelled = true
        }
    }

    override fun close() {
        registered.keys.forEach { it.closeForViewers() }
        registered.clear()
    }
}

private val InventoryAction.isGUIClick
    get() = this == InventoryAction.PICKUP_ALL || this == InventoryAction.PICKUP_HALF || this == InventoryAction.PICKUP_SOME || this == InventoryAction.PICKUP_ONE || this == InventoryAction.MOVE_TO_OTHER_INVENTORY

private val InventoryInteractEvent.playerOrCancel: Player?
    get() = (whoClicked as? Player) ?: kotlin.run {
        isCancelled = true
        return null
    }
