package net.axay.kspigot.inventory.elements

import net.axay.kspigot.inventory.ForInventory
import net.axay.kspigot.runnables.task
import org.bukkit.inventory.ItemStack

class InventoryGUISpaceCompoundScrollButton<T : ForInventory>(
    icon: ItemStack,
    private val compound: AbstractInventoryGUISpaceCompound<T, *>,
    private val scrollDistance: Int,
    private val scrollTimes: Int,
    private val reverse: Boolean = false
) : InventoryGUIButton<T>(icon, {
    if (scrollTimes > 1) {
        task(
            period = 1,
            howOften = scrollTimes.toLong()
        ) {
            val ifScrolled = if (reverse) compound.scroll(-scrollDistance) else compound.scroll(scrollDistance)
            if (!ifScrolled) it.cancel()
        }
    } else if (scrollTimes == 1)
        if (reverse) compound.scroll(-scrollDistance) else compound.scroll(scrollDistance)
})