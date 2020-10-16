---
title: SingleInventorySlot -
---
//[KSpigot](../../index.md)/[net.axay.kspigot.inventory](../index.md)/[SingleInventorySlot](index.md)



# SingleInventorySlot  
 [jvm] open class [SingleInventorySlot](index.md)<[T](index.md) : [ForInventory](../-for-inventory/index.md)> : [InventorySlotCompound](../-inventory-slot-compound/index.md)<[T](index.md)>    


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [SingleInventorySlot](-single-inventory-slot.md)|  [jvm] fun [SingleInventorySlot](-single-inventory-slot.md)(row: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), slotInRow: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)| [jvm]  <br>Content  <br>open operator override fun [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [realSlotsWithInvType](../-inventory-slot-compound/real-slots-with-inv-type.md)| [jvm]  <br>Content  <br>open override fun [realSlotsWithInvType](../-inventory-slot-compound/real-slots-with-inv-type.md)(invType: [InventoryType](../-inventory-type/index.md)<[T](index.md)>): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)>  <br><br><br>
| [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [withInvType](with-inv-type.md)| [jvm]  <br>Content  <br>open override fun [withInvType](with-inv-type.md)(invType: [InventoryType](../-inventory-type/index.md)<[T](index.md)>): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[InventorySlot](../-inventory-slot/index.md)>  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [inventorySlot](index.md#net.axay.kspigot.inventory/SingleInventorySlot/inventorySlot/#/PointingToDeclaration/)|  [jvm] val [inventorySlot](index.md#net.axay.kspigot.inventory/SingleInventorySlot/inventorySlot/#/PointingToDeclaration/): [InventorySlot](../-inventory-slot/index.md)   <br>


## Extensions  
  
|  Name|  Summary| 
|---|---|
| [linTo](../lin-to.md)| [jvm]  <br>Brief description  <br><br><br>This range contains all slots having an index between the indeces of the two given slots.<br><br>  <br>Content  <br>infix fun <[T](../lin-to.md) : [ForInventory](../-for-inventory/index.md)> [SingleInventorySlot](index.md)<out [T](../lin-to.md)>.[linTo](../lin-to.md)(slot: [SingleInventorySlot](index.md)<out [T](../lin-to.md)>): [InventorySlotRange](../-inventory-slot-range/index.md)<[T](../lin-to.md)>  <br><br><br>
| [rectTo](../rect-to.md)| [jvm]  <br>Brief description  <br><br><br>This range contains all slots inside of a thought rectangle with the two given slots as two opposite corners of the rectangle.<br><br>  <br>Content  <br>infix fun <[T](../rect-to.md) : [ForInventory](../-for-inventory/index.md)> [SingleInventorySlot](index.md)<out [T](../rect-to.md)>.[rectTo](../rect-to.md)(slot: [SingleInventorySlot](index.md)<out [T](../rect-to.md)>): [InventorySlotRange](../-inventory-slot-range/index.md)<[T](../rect-to.md)>  <br><br><br>

