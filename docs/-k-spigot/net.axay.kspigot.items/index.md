---
title: net.axay.kspigot.items -
---
//[KSpigot](../index.md)/[net.axay.kspigot.items](index.md)



# Package net.axay.kspigot.items  


## Types  
  
|  Name|  Summary| 
|---|---|
| [CustomItemIdentifier](-custom-item-identifier/index.md)| [jvm]  <br>Brief description  <br><br><br><br><br>This class defines a material in combination with a specific custom model data value.<br><br><br><br>It is useful if you work with custom items defined in resourcepacks, where you do not only need a material to define a specific item type, but the value of the custom model data aswell.<br><br><br><br>  <br>Content  <br>data class [CustomItemIdentifier](-custom-item-identifier/index.md)(**customModelData**: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), **placeHolderMaterial**: Material)  <br><br><br>
| [ItemMetaLoreBuilder](-item-meta-lore-builder/index.md)| [jvm]  <br>Brief description  <br><br><br>Lore builder which uses an [ArrayList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html) under the hood. It exists to provide overloaded operator functions.<br><br>  <br>Content  <br>class [ItemMetaLoreBuilder](-item-meta-lore-builder/index.md)  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [addLore](add-lore.md)| [jvm]  <br>Brief description  <br><br><br>Adds new lines to the lore (description) of the item.<br><br>  <br>Content  <br>inline fun ItemMeta.[addLore](add-lore.md)(builder: [ItemMetaLoreBuilder](-item-meta-lore-builder/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| [flag](flag.md)| [jvm]  <br>Brief description  <br><br><br>Add a new ItemFlag to the item flags.<br><br>  <br>Content  <br>fun ItemMeta.[flag](flag.md)(itemFlag: ItemFlag)  <br><br><br>
| [flags](flags.md)| [jvm]  <br>Brief description  <br><br><br>Add several ItemFlags to the item flags.<br><br>  <br>Content  <br>fun ItemMeta.[flags](flags.md)(vararg itemFlag: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)<out ItemFlag>)  <br><br><br>
| [itemMeta](item-meta.md)| [jvm]  <br>Brief description  <br><br><br>Creates new a ItemMeta instance of the given material and opens a builder for it.<br><br>  <br>Content  <br>inline fun <[T](item-meta.md) : ItemMeta> [itemMeta](item-meta.md)(material: Material, builder: [T](item-meta.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [T](item-meta.md)?  <br><br><br>[jvm]  <br>Content  <br>@[JvmName](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-name/index.html)(name = "simpleItemMeta")  <br>  <br>inline fun [itemMeta](item-meta.md)(material: Material, builder: ItemMeta.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): ItemMeta?  <br><br><br>
| [itemStack](item-stack.md)| [jvm]  <br>Brief description  <br><br><br>Creates a new ItemStack and opens a builder for it.<br><br>  <br>Content  <br>inline fun [itemStack](item-stack.md)(material: Material, builder: ItemStack.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): ItemStack  <br><br><br>
| [meta](meta.md)| [jvm]  <br>Brief description  <br><br><br>Opens a builder with the current meta.<br><br>  <br>Content  <br>inline fun <[T](meta.md) : ItemMeta> ItemStack.[meta](meta.md)(builder: [T](meta.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>[jvm]  <br>Content  <br>@[JvmName](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-name/index.html)(name = "simpleMeta")  <br>  <br>inline fun ItemStack.[meta](meta.md)(builder: ItemMeta.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| [removeFlag](remove-flag.md)| [jvm]  <br>Brief description  <br><br><br>Removes a ItemFlag from the item flags.<br><br>  <br>Content  <br>fun ItemMeta.[removeFlag](remove-flag.md)(itemFlag: ItemFlag)  <br><br><br>
| [removeFlags](remove-flags.md)| [jvm]  <br>Brief description  <br><br><br>Removes several ItemFlags from the item flags.<br><br>  <br>Content  <br>fun ItemMeta.[removeFlags](remove-flags.md)(vararg itemFlag: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)<out ItemFlag>)  <br><br><br>
| [setLore](set-lore.md)| [jvm]  <br>Brief description  <br><br><br>Sets the lore (description) of the item.<br><br>  <br>Content  <br>inline fun ItemMeta.[setLore](set-lore.md)(builder: [ItemMetaLoreBuilder](-item-meta-lore-builder/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| [setMeta](set-meta.md)| [jvm]  <br>Brief description  <br><br><br>Resets the meta and opens a builder to create the new one.<br><br>  <br>Content  <br>inline fun <[T](set-meta.md) : ItemMeta> ItemStack.[setMeta](set-meta.md)(builder: [T](set-meta.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>[jvm]  <br>Content  <br>@[JvmName](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-name/index.html)(name = "simpleSetMeta")  <br>  <br>inline fun ItemStack.[setMeta](set-meta.md)(builder: ItemMeta.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [customModel](index.md#net.axay.kspigot.items//customModel/org.bukkit.inventory.meta.ItemMeta#/PointingToDeclaration/)|  [jvm] <br><br>Provides safe access to the items' customModelData.<br><br>var ItemMeta.[customModel](index.md#net.axay.kspigot.items//customModel/org.bukkit.inventory.meta.ItemMeta#/PointingToDeclaration/): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)?   <br>
| [localName](index.md#net.axay.kspigot.items//localName/org.bukkit.inventory.meta.ItemMeta#/PointingToDeclaration/)|  [jvm] <br><br>Provides more consistent access to the items' localizedName.<br><br>var ItemMeta.[localName](index.md#net.axay.kspigot.items//localName/org.bukkit.inventory.meta.ItemMeta#/PointingToDeclaration/): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)   <br>
| [name](index.md#net.axay.kspigot.items//name/org.bukkit.inventory.meta.ItemMeta#/PointingToDeclaration/)|  [jvm] <br><br>Provides safe access to the items' displayName.<br><br>var ItemMeta.[name](index.md#net.axay.kspigot.items//name/org.bukkit.inventory.meta.ItemMeta#/PointingToDeclaration/): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?   <br>

