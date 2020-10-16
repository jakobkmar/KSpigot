---
title: InventorySlotCompound -
---
//[KSpigot](../../index.md)/[net.axay.kspigot.inventory](../index.md)/[InventorySlotCompound](index.md)



# InventorySlotCompound  
 [jvm] interface [InventorySlotCompound](index.md)<out [T](index.md) : [ForInventory](../-for-inventory/index.md)>   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)| [jvm]  <br>Content  <br>open operator override fun [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [realSlotsWithInvType](real-slots-with-inv-type.md)| [jvm]  <br>Content  <br>open fun [realSlotsWithInvType](real-slots-with-inv-type.md)(invType: [InventoryType](../-inventory-type/index.md)<[T](index.md)>): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)>  <br><br><br>
| [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [withInvType](with-inv-type.md)| [jvm]  <br>Content  <br>abstract fun [withInvType](with-inv-type.md)(invType: [InventoryType](../-inventory-type/index.md)<[T](index.md)>): [Collection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)<[InventorySlot](../-inventory-slot/index.md)>  <br><br><br>


## Inheritors  
  
|  Name| 
|---|
| [SingleInventorySlot](../-single-inventory-slot/index.md)
| [InventorySlotRange](../-inventory-slot-range/index.md)
| [InventoryRowSlots](../-inventory-row-slots/index.md)
| [InventoryColumnSlots](../-inventory-column-slots/index.md)
| [InventoryBorderSlots](../-inventory-border-slots/index.md)
| [InventoryCornerSlots](../-inventory-corner-slots/index.md)

