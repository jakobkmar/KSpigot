package net.axay.kspigot.extensions.bukkit

import org.bukkit.inventory.Inventory

fun Inventory.closeForViewers() = viewers.forEach { it.closeInventory() }