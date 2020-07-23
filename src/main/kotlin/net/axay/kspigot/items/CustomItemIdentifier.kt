package net.axay.kspigot.items

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

/**
 * This class defines a material in combination
 * with a specific custom model data value.
 *
 * It is useful if you work with custom items
 * defined in resourcepacks, where you do not
 * only need a material to define a specific item
 * type, but the value of the custom model data
 * aswell.
 */
data class CustomItemIdentifier(val customModelData: Int, val placeHolderMaterial: Material) {

    constructor(itemStack: ItemStack) :
        this(
            kotlin.run {
                val itemMeta = itemStack.itemMeta
                if (itemMeta != null && itemMeta.hasCustomModelData()) {
                    return@run itemMeta.customModelData
                }
                return@run 0
            },
            itemStack.type
        )

    val itemStack: ItemStack?
        get() {
            val itemStack = ItemStack(placeHolderMaterial, 1)
            val itemMeta: ItemMeta? = itemStack.itemMeta
            return if (itemMeta != null) {
                itemMeta.setCustomModelData(customModelData)
                itemStack.itemMeta = itemMeta
                itemStack
            } else null
        }

}