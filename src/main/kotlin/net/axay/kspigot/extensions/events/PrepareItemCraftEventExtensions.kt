package net.axay.kspigot.extensions.events

import org.bukkit.Material
import org.bukkit.event.inventory.PrepareItemCraftEvent
import org.bukkit.inventory.ItemStack

/**
 * Checks if the event is "cancelled"
 * by returning if the material of
 * the result is equal to [Material.AIR].
 */
val PrepareItemCraftEvent.isCancelled: Boolean
    get() = this.inventory.result?.type == Material.AIR

/**
 * "Cancels" this event by
 * setting the result to [Material.AIR].
 */
fun PrepareItemCraftEvent.cancel() {
    this.inventory.result = ItemStack(Material.AIR)
}
