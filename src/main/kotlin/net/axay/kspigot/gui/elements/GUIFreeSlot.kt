package net.axay.kspigot.gui.elements

import net.axay.kspigot.gui.ForInventory
import net.axay.kspigot.gui.GUIClickEvent
import net.axay.kspigot.gui.GUISlot

class GUIFreeSlot<T : ForInventory> : GUISlot<T>() {
    override fun onClick(clickEvent: GUIClickEvent<T>) {
        /* do nothing */
    }
}
