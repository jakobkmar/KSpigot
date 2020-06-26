package net.axay.kspigot.extensions.events

import org.bukkit.Material
import org.bukkit.event.inventory.PrepareItemCraftEvent
import org.bukkit.inventory.ItemStack

val PrepareItemCraftEvent.isCancelled: Boolean
        get() = this.inventory.result?.type == Material.AIR

fun PrepareItemCraftEvent.cancel() { this.inventory.result = ItemStack(Material.AIR) }