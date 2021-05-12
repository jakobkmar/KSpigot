package net.axay.kspigot.utils

import net.axay.kspigot.extensions.pluginKey
import net.axay.kspigot.items.meta
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataHolder
import org.bukkit.persistence.PersistentDataType

private fun markerKey(key: String) = pluginKey("kspigot_marker_$key")

/**
 * Marks this object with the given [key].
 * This is persistent.
 *
 * This function makes sure that there are no
 * conflicts with other plugins, therefore even simple
 * keys are safe.
 */
fun PersistentDataHolder.mark(key: String) {
    persistentDataContainer[markerKey(key), PersistentDataType.BYTE] = 1.toByte()
}

/**
 * Removes the given [key] from this objects'
 * markings.
 */
fun PersistentDataHolder.unmark(key: String) {
    persistentDataContainer.remove(markerKey(key))
}

/**
 * @return True, if the given [key] is among
 * this objects' markings.
 */
fun PersistentDataHolder.hasMark(key: String) = persistentDataContainer.has(markerKey(key), PersistentDataType.BYTE)
// quick access for ItemStacks
/** @see PersistentDataHolder.mark */
fun ItemStack.mark(key: String) = meta { mark(key) }

/** @see PersistentDataHolder.unmark */
fun ItemStack.unmark(key: String) = meta { unmark(key) }

/** @see PersistentDataHolder.hasMark */
fun ItemStack.hasMark(key: String): Boolean {
    var result: Boolean = false
    meta { result = hasMark(key) }
    return result
}