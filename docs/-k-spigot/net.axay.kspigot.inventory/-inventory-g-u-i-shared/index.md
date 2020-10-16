---
title: InventoryGUIShared -
---
//[KSpigot](../../index.md)/[net.axay.kspigot.inventory](../index.md)/[InventoryGUIShared](index.md)



# InventoryGUIShared  
 [jvm] class [InventoryGUIShared](index.md)<[T](index.md) : [ForInventory](../-for-inventory/index.md)>(**inventoryGUIData**: [InventoryGUIData](../-inventory-g-u-i-data/index.md)<[T](index.md)>) : [InventoryGUI](../-inventory-g-u-i/index.md)<[T](index.md)>    


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)| [jvm]  <br>Content  <br>open operator override fun [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [getPage](../-inventory-g-u-i/get-page.md)| [jvm]  <br>Brief description  <br><br><br>Searches for a page associated to the given page index.<br><br>  <br>Content  <br>override fun [getPage](../-inventory-g-u-i/get-page.md)(page: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)?): [InventoryGUIPage](../-inventory-g-u-i-page/index.md)<[T](index.md)>?  <br><br><br>
| [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [isThisInv](is-this-inv.md)| [jvm]  <br>Content  <br>open override fun [isThisInv](is-this-inv.md)(inventory: Inventory): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [loadPage](../-inventory-g-u-i/load-page.md)| [jvm]  <br>Brief description  <br><br><br>Loads the specified page in order to display it in the GUI.<br><br>  <br>Content  <br>override fun [loadPage](../-inventory-g-u-i/load-page.md)(page: [InventoryGUIPage](../-inventory-g-u-i-page/index.md)<[T](index.md)>)  <br><br><br>
| [register](../-inventory-g-u-i/register.md)| [jvm]  <br>Brief description  <br><br><br>Registers this InventoryGUI. (KSpigot will listen for actions in the inventory.)<br><br>  <br>Content  <br>override fun [register](../-inventory-g-u-i/register.md)()  <br><br><br>
| [set](set.md)| [jvm]  <br>Brief description  <br><br><br>Temporarily sets the given item at the given slots.<br><br>  <br>Content  <br>open operator override fun [set](set.md)(slot: [InventorySlotCompound](../-inventory-slot-compound/index.md)<[T](index.md)>, value: ItemStack)  <br><br><br>
| [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [unregister](../-inventory-g-u-i/unregister.md)| [jvm]  <br>Brief description  <br><br><br>Stops KSpigot from listening to actions in this InventoryGUI anymore.<br><br>  <br>Content  <br>override fun [unregister](../-inventory-g-u-i/unregister.md)()  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [currentPage](index.md#net.axay.kspigot.inventory/InventoryGUIShared/currentPage/#/PointingToDeclaration/)|  [jvm] override val [currentPage](index.md#net.axay.kspigot.inventory/InventoryGUIShared/currentPage/#/PointingToDeclaration/): [InventoryGUIPage](../-inventory-g-u-i-page/index.md)<[T](index.md)>   <br>
| [currentPageInt](index.md#net.axay.kspigot.inventory/InventoryGUIShared/currentPageInt/#/PointingToDeclaration/)|  [jvm] override var [currentPageInt](index.md#net.axay.kspigot.inventory/InventoryGUIShared/currentPageInt/#/PointingToDeclaration/): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)   <br>
| [data](index.md#net.axay.kspigot.inventory/InventoryGUIShared/data/#/PointingToDeclaration/)|  [jvm] override val [data](index.md#net.axay.kspigot.inventory/InventoryGUIShared/data/#/PointingToDeclaration/): [InventoryGUIData](../-inventory-g-u-i-data/index.md)<[T](index.md)>   <br>

