package net.axay.kspigot.gui.elements

import net.axay.kspigot.gui.ForInventory
import net.axay.kspigot.runnables.task
import org.bukkit.inventory.ItemStack

class GUISpaceCompoundScrollButton<T : ForInventory>(

    icon: ItemStack,

    private val compound: AbstractGUISpaceCompound<T, *>,
    private val scrollDistance: Int,
    private val scrollTimes: Int,
    private val reverse: Boolean = false

) : GUIButton<T>(icon, {

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

}) {

    constructor(
        icon: ItemStack,
        compound: GUIRectSpaceCompound<T, *>,
        scrollTimes: Int = 1,
        reverse: Boolean = false
    ) : this(icon, compound, compound.compoundWidth, scrollTimes, reverse)

}