---
title: InventoryGUIPageChangeCalculator -
---
//[KSpigot](../../index.md)/[net.axay.kspigot.inventory](../index.md)/[InventoryGUIPageChangeCalculator](index.md)



# InventoryGUIPageChangeCalculator  
 [jvm] abstract class [InventoryGUIPageChangeCalculator](index.md)   


## Types  
  
|  Name|  Summary| 
|---|---|
| [InventoryGUIConsistentPageCalculator](-inventory-g-u-i-consistent-page-calculator/index.md)| [jvm]  <br>Content  <br>class [InventoryGUIConsistentPageCalculator](-inventory-g-u-i-consistent-page-calculator/index.md)(**toPage**: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [InventoryGUIPageChangeCalculator](index.md)  <br><br><br>
| [InventoryGUINextPageCalculator](-inventory-g-u-i-next-page-calculator/index.md)| [jvm]  <br>Content  <br>object [InventoryGUINextPageCalculator](-inventory-g-u-i-next-page-calculator/index.md) : [InventoryGUIPageChangeCalculator](index.md)  <br><br><br>
| [InventoryGUIPreviousPageCalculator](-inventory-g-u-i-previous-page-calculator/index.md)| [jvm]  <br>Content  <br>object [InventoryGUIPreviousPageCalculator](-inventory-g-u-i-previous-page-calculator/index.md) : [InventoryGUIPageChangeCalculator](index.md)  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [calculateNewPage](calculate-new-page.md)| [jvm]  <br>Content  <br>abstract fun [calculateNewPage](calculate-new-page.md)(currentPage: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), pages: [Collection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)<[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)>): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)?  <br><br><br>
| [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)| [jvm]  <br>Content  <br>open operator override fun [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [InventoryGUIPageChangeCalculator](-inventory-g-u-i-previous-page-calculator/index.md)
| [InventoryGUIPageChangeCalculator](-inventory-g-u-i-next-page-calculator/index.md)
| [InventoryGUIPageChangeCalculator](-inventory-g-u-i-consistent-page-calculator/index.md)

