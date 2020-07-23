package net.axay.kspigot.items

import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

object KSpigotItems {

    inline fun buildItem(material: Material, builder: KSpigotItemBuilder.() -> Unit): ItemStack {
        return KSpigotItemBuilder(material).apply(builder).build()
    }

}

class KSpigotItemBuilder(material: Material) {

    private val itemStack = ItemStack(material, 1)

    var amount: Int? = null

    fun addEnchantment(enchantment: Enchantment, level: Int = 1) {
        itemStack.addEnchantment(enchantment, level)
    }

    fun itemMeta(builder: ItemMetaBuilder.() -> Unit) {
        ItemMetaBuilder(itemStack).apply(builder).applyToItemStack()
    }

    fun build(): ItemStack {

        amount?.let { itemStack.amount = it }

        return itemStack

    }

}

class ItemMetaBuilder(val itemStack: ItemStack) {

    private val itemMeta = itemStack.itemMeta

    var lore = ArrayList<String>()
    var flags = ArrayList<ItemFlag>()

    var displayName: String? = null
    var customModelData: Int? = null
    var unbreakable: Boolean? = null
    var localizedName: String? = null

    inline fun addLore(builder: ItemMetaLoreBuilder.() -> Unit) {
        lore.addAll(ItemMetaLoreBuilder().apply(builder).lorelist)
    }

    fun addAttributeModifier(attribute: Attribute, attributeModifier: AttributeModifier) {
        itemMeta?.addAttributeModifier(attribute, attributeModifier)
    }

    infix fun flag(itemFlag: ItemFlag) {
        flags.add(itemFlag)
    }

    fun applyToItemStack() {
        itemMeta?.let { meta ->

            meta.lore = lore
            meta.addItemFlags(*flags.toTypedArray())

            displayName?.let { meta.setDisplayName(it) }
            meta.setCustomModelData(customModelData)
            unbreakable?.let { meta.isUnbreakable = it }
            localizedName?.let { meta.setLocalizedName(it) }

            itemStack.itemMeta = meta

        }
    }

}

class ItemMetaLoreBuilder {

    val lorelist = ArrayList<String>()

    operator fun String.unaryPlus() {
        lorelist += this
    }

}