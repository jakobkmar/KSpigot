---
title: InventoryGUI -
---
//[KSpigot](../../index.md)/[net.axay.kspigot.inventory](../index.md)/[InventoryGUI](index.md)



# InventoryGUI  
 [jvm] abstract class [InventoryGUI](index.md)<[T](index.md) : [ForInventory](../-for-inventory/index.md)>(**data**: [InventoryGUIData](../-inventory-g-u-i-data/index.md)<[T](index.md)>)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)| [jvm]  <br>Content  <br>open operator override fun [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [getPage](get-page.md)| [jvm]  <br>Brief description  <br><br><br>Searches for a page associated to the given page index.<br><br>  <br>Content  <br>fun [getPage](get-page.md)(page: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)?): [InventoryGUIPage](../-inventory-g-u-i-page/index.md)<[T](index.md)>?  <br><br><br>
| [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [isThisInv](is-this-inv.md)| [jvm]  <br>Content  <br>abstract fun [isThisInv](is-this-inv.md)(inventory: Inventory): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [loadPage](load-page.md)| [jvm]  <br>Brief description  <br><br><br>Loads the specified page in order to display it in the GUI.<br><br>  <br>Content  <br>fun [loadPage](load-page.md)(page: [InventoryGUIPage](../-inventory-g-u-i-page/index.md)<[T](index.md)>)  <br><br><br>
| [register](register.md)| [jvm]  <br>Brief description  <br><br><br>Registers this InventoryGUI. (KSpigot will listen for actions in the inventory.)<br><br>  <br>Content  <br>fun [register](register.md)()  <br><br><br>
| [set](set.md)| [jvm]  <br>Brief description  <br><br><br>Temporarily sets the given item at the given slots.<br><br>  <br>Content  <br>abstract operator fun [set](set.md)(slot: [InventorySlotCompound](../-inventory-slot-compound/index.md)<[T](index.md)>, value: ItemStack)  <br><br><br>
| [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [unregister](unregister.md)| [jvm]  <br>Brief description  <br><br><br>Stops KSpigot from listening to actions in this InventoryGUI anymore.<br><br>  <br>Content  <br>fun [unregister](unregister.md)()  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [currentPage](index.md#net.axay.kspigot.inventory/InventoryGUI/currentPage/#/PointingToDeclaration/)|  [jvm] val [currentPage](index.md#net.axay.kspigot.inventory/InventoryGUI/currentPage/#/PointingToDeclaration/): [InventoryGUIPage](../-inventory-g-u-i-page/index.md)<[T](index.md)>   <br>
| [currentPageInt](index.md#net.axay.kspigot.inventory/InventoryGUI/currentPageInt/#/PointingToDeclaration/)|  [jvm] var [currentPageInt](index.md#net.axay.kspigot.inventory/InventoryGUI/currentPageInt/#/PointingToDeclaration/): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)   <br>
| [data](index.md#net.axay.kspigot.inventory/InventoryGUI/data/#/PointingToDeclaration/)|  [jvm] val [data](index.md#net.axay.kspigot.inventory/InventoryGUI/data/#/PointingToDeclaration/): [InventoryGUIData](../-inventory-g-u-i-data/index.md)<[T](index.md)>   <br>


## Inheritors  
  
|  Name| 
|---|
| [InventoryGUIShared](../-inventory-g-u-i-shared/index.md)

