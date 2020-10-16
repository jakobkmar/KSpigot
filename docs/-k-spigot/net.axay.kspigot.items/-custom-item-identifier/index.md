---
title: CustomItemIdentifier -
---
//[KSpigot](../../index.md)/[net.axay.kspigot.items](../index.md)/[CustomItemIdentifier](index.md)



# CustomItemIdentifier  
 [jvm] 



This class defines a material in combination with a specific custom model data value.



It is useful if you work with custom items defined in resourcepacks, where you do not only need a material to define a specific item type, but the value of the custom model data aswell.



data class [CustomItemIdentifier](index.md)(**customModelData**: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), **placeHolderMaterial**: Material)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [CustomItemIdentifier](-custom-item-identifier.md)|  [jvm] fun [CustomItemIdentifier](-custom-item-identifier.md)(itemStack: ItemStack)   <br>
| [CustomItemIdentifier](-custom-item-identifier.md)|  [jvm] fun [CustomItemIdentifier](-custom-item-identifier.md)(customModelData: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), placeHolderMaterial: Material)   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [component1](component1.md)| [jvm]  <br>Content  <br>operator fun [component1](component1.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [component2](component2.md)| [jvm]  <br>Content  <br>operator fun [component2](component2.md)(): Material  <br><br><br>
| [copy](copy.md)| [jvm]  <br>Content  <br>fun [copy](copy.md)(customModelData: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), placeHolderMaterial: Material): [CustomItemIdentifier](index.md)  <br><br><br>
| [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)| [jvm]  <br>Content  <br>open operator override fun [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [customModelData](index.md#net.axay.kspigot.items/CustomItemIdentifier/customModelData/#/PointingToDeclaration/)|  [jvm] val [customModelData](index.md#net.axay.kspigot.items/CustomItemIdentifier/customModelData/#/PointingToDeclaration/): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)   <br>
| [itemStack](index.md#net.axay.kspigot.items/CustomItemIdentifier/itemStack/#/PointingToDeclaration/)|  [jvm] val [itemStack](index.md#net.axay.kspigot.items/CustomItemIdentifier/itemStack/#/PointingToDeclaration/): ItemStack?   <br>
| [placeHolderMaterial](index.md#net.axay.kspigot.items/CustomItemIdentifier/placeHolderMaterial/#/PointingToDeclaration/)|  [jvm] val [placeHolderMaterial](index.md#net.axay.kspigot.items/CustomItemIdentifier/placeHolderMaterial/#/PointingToDeclaration/): Material   <br>

