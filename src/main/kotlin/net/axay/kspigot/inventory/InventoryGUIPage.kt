package net.axay.kspigot.inventory

class InventoryGUIPage<T : ForInventory>(
    val number: Int,
    internal val slots: Map<Int, InventoryGUISlot<T>>,
    val transitionTo: PageChangeEffect?,
    val transitionFrom: PageChangeEffect?
)