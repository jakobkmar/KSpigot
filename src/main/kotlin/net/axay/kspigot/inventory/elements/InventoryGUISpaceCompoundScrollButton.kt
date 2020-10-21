package net.axay.kspigot.inventory.elements

import net.axay.kspigot.inventory.ForInventory
import net.axay.kspigot.inventory.InventoryGUIClickEvent
import net.axay.kspigot.inventory.InventoryGUIElement
import net.axay.kspigot.runnables.task
import org.bukkit.inventory.ItemStack

class InventoryGUISpaceCompoundScrollButton<T : ForInventory>(
    private val icon: ItemStack,
    private val compound: InventoryGUISpaceCompound<T, *>,
    private val scrollDistance: Int = compound.invType.dimensions.height,
    private val reverse: Boolean = false
) : InventoryGUIElement<T>() {

    override fun getItemStack(slot: Int) = icon

    override fun onClickElement(clickEvent: InventoryGUIClickEvent<T>) {
        task(
            period = 1,
            howOften = scrollDistance.toLong()
        ) {
            val ifScrolled = if (reverse) compound.scroll(-1) else compound.scroll(1)
            if (!ifScrolled) it.cancel()
        }
    }

}