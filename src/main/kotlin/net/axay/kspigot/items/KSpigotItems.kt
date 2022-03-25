@file:Suppress("Unused")

package net.axay.kspigot.items

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.Component.translatable
import net.kyori.adventure.text.TranslatableComponent
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

/**
 * Creates a new [ItemStack] and opens a builder for it.
 */
inline fun itemStack(material: Material, builder: ItemStack.() -> Unit) = ItemStack(material).apply(builder)

/**
 * Opens a builder with the current meta.
 * @param T the specific type of the meta
 */
inline fun <reified T : ItemMeta> ItemStack.meta(builder: T.() -> Unit) {
    val curMeta = itemMeta as? T
    itemMeta = if (curMeta != null) {
        curMeta.apply(builder)
        curMeta
    } else {
        itemMeta(type, builder)
    }
}

/** @see meta */
@JvmName("simpleMeta")
inline fun ItemStack.meta(builder: ItemMeta.() -> Unit) = meta<ItemMeta>(builder)

/**
 * Resets the meta and opens a builder to create the new one.
 * @param T the specific type of the meta
 */
inline fun <reified T : ItemMeta> ItemStack.setMeta(builder: T.() -> Unit) {
    itemMeta = itemMeta(type, builder)
}

/** @see setMeta */
@JvmName("simpleSetMeta")
inline fun ItemStack.setMeta(builder: ItemMeta.() -> Unit) = setMeta<ItemMeta>(builder)

/**
 * Creates new a [ItemMeta] instance of the given material and opens a builder for it.
 * @param T the specific type of the meta
 */
inline fun <reified T : ItemMeta> itemMeta(material: Material, builder: T.() -> Unit): T? {
    val meta = Bukkit.getItemFactory().getItemMeta(material)
    return if (meta is T) meta.apply(builder) else null
}

/** @see itemMeta */
@JvmName("simpleItemMeta")
inline fun itemMeta(material: Material, builder: ItemMeta.() -> Unit) = itemMeta<ItemMeta>(material, builder)

/**
 * Sets the lore (description) of the item.
 */
inline fun ItemMeta.setLore(builder: ItemMetaLoreBuilder.() -> Unit) {
    lore(ItemMetaLoreBuilder().apply(builder).lorelist)
}

/**
 * Adds new lines to the lore (description) of the item.
 */
inline fun ItemMeta.addLore(builder: ItemMetaLoreBuilder.() -> Unit) {
    val newLore = lore() ?: mutableListOf<Component>()
    newLore.addAll(ItemMetaLoreBuilder().apply(builder).lorelist)
    lore(newLore)
}

/**
 * Lore builder which uses an [ArrayList] under the hood.
 * It exists to provide overloaded operator functions.
 */
class ItemMetaLoreBuilder {
    val lorelist = ArrayList<Component>()

    /**
     * Adds a new line to the lore.
     *
     * Note: Render [TranslatableComponent]s before adding them to the lore.
     */
    operator fun Component.unaryPlus() {
        lorelist += this
    }

    /**
     * Adds a new line to the lore.
     */
    operator fun String.unaryPlus() {
        lorelist += text(this)
    }
}

/**
 * Add a new [ItemFlag] to the item flags.
 */
fun ItemMeta.flag(itemFlag: ItemFlag) = addItemFlags(itemFlag)

/**
 * Add several [ItemFlag]s to the item flags.
 */
fun ItemMeta.flags(vararg itemFlag: ItemFlag) = addItemFlags(*itemFlag)

/**
 * Removes a [ItemFlag] from the item flags.
 */
fun ItemMeta.removeFlag(itemFlag: ItemFlag) = removeItemFlags(itemFlag)

/**
 * Removes several [ItemFlag]s from the item flags.
 */
fun ItemMeta.removeFlags(vararg itemFlag: ItemFlag) = removeItemFlags(*itemFlag)

/**
 * Provides safe access to the items' displayName.
 *
 * Note: Render [TranslatableComponent]s before setting them as the displayName.
 */
var ItemMeta.name: Component?
    get() = if (hasDisplayName()) displayName() else null
    set(value) = displayName(value ?: Component.space())

/**
 * Provides safe access to the items' displayName.
 */
@Suppress("DEPRECATION")
@Deprecated("displaynames are saved as Components in Paper", ReplaceWith("name", "net.axay.kspigot.Items.name"))
var ItemMeta.stringName: String?
    get() = if (hasDisplayName()) displayName else null
    set(value) = setDisplayName(if (value == null || value == "") " " else value)

/**
 * Provides safe access to the items' customModelData.
 */
var ItemMeta.customModel: Int?
    get() = if (hasCustomModelData()) customModelData else null
    set(value) = setCustomModelData(value)

/**
 * Provides more consistent access to the items' localizedName.
 */
var ItemMeta.localName: TranslatableComponent
    get() = if (hasDisplayName()) displayName() as TranslatableComponent else translatable("")
    set(value) = displayName(value)
