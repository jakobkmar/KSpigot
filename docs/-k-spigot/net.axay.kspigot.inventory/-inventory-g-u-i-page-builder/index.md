---
title: InventoryGUIPageBuilder -
---
//[KSpigot](../../index.md)/[net.axay.kspigot.inventory](../index.md)/[InventoryGUIPageBuilder](index.md)



# InventoryGUIPageBuilder  
 [jvm] class [InventoryGUIPageBuilder](index.md)<[T](index.md) : [ForInventory](../-for-inventory/index.md)>(**type**: [InventoryType](../-inventory-type/index.md)<[T](index.md)>, **page**: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [button](button.md)| [jvm]  <br>Brief description  <br><br><br>A button is an item protected from any player actions. If clicked, the specified onClick function is invoked.<br><br>  <br>Content  <br>fun [button](button.md)(slots: [InventorySlotCompound](../-inventory-slot-compound/index.md)<[T](index.md)>, itemStack: ItemStack, onClick: ([InventoryGUIClickEvent](../-inventory-g-u-i-click-event/index.md)<[T](index.md)>) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| [changeGUI](change-g-u-i.md)| [jvm]  <br>Brief description  <br><br><br>By pressing this button, the player switches to another InventoryGUI. The transition effect is applied.<br><br>  <br>Content  <br>fun [changeGUI](change-g-u-i.md)(slots: [InventorySlotCompound](../-inventory-slot-compound/index.md)<[T](index.md)>, itemStack: ItemStack, newGUI: () -> [InventoryGUI](../-inventory-g-u-i/index.md)<*>, newPage: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)?, onChange: ([InventoryGUIClickEvent](../-inventory-g-u-i-click-event/index.md)<[T](index.md)>) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?)  <br><br><br>
| [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)| [jvm]  <br>Content  <br>open operator override fun [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [freeSlot](free-slot.md)| [jvm]  <br>Brief description  <br><br><br>A free slot does not block any player actions. The player can put items in this slot or take items out of it.<br><br>  <br>Content  <br>fun [freeSlot](free-slot.md)(slots: [InventorySlotCompound](../-inventory-slot-compound/index.md)<[T](index.md)>)  <br><br><br>
| [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [nextPage](next-page.md)| [jvm]  <br>Brief description  <br><br><br>This button always tries to find the next page if clicked, and if a next page exists it is loaded.<br><br>  <br>Content  <br>fun [nextPage](next-page.md)(slots: [InventorySlotCompound](../-inventory-slot-compound/index.md)<[T](index.md)>, itemStack: ItemStack, onChange: ([InventoryGUIClickEvent](../-inventory-g-u-i-click-event/index.md)<[T](index.md)>) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?)  <br><br><br>
| [pageChanger](page-changer.md)| [jvm]  <br>Brief description  <br><br><br>This is a button which loads the specified toPage if clicked.<br><br>  <br>Content  <br>fun [pageChanger](page-changer.md)(slots: [InventorySlotCompound](../-inventory-slot-compound/index.md)<[T](index.md)>, itemStack: ItemStack, toPage: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), onChange: ([InventoryGUIClickEvent](../-inventory-g-u-i-click-event/index.md)<[T](index.md)>) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?)  <br><br><br>
| [placeholder](placeholder.md)| [jvm]  <br>Brief description  <br><br><br>An item protected from any player actions. This is not a button.<br><br>  <br>Content  <br>fun [placeholder](placeholder.md)(slots: [InventorySlotCompound](../-inventory-slot-compound/index.md)<[T](index.md)>, itemStack: ItemStack)  <br><br><br>
| [previousPage](previous-page.md)| [jvm]  <br>Brief description  <br><br><br>This button always tries to find the previous page if clicked, and if a previous page exists it is loaded.<br><br>  <br>Content  <br>fun [previousPage](previous-page.md)(slots: [InventorySlotCompound](../-inventory-slot-compound/index.md)<[T](index.md)>, itemStack: ItemStack, onChange: ([InventoryGUIClickEvent](../-inventory-g-u-i-click-event/index.md)<[T](index.md)>) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?)  <br><br><br>
| [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [page](index.md#net.axay.kspigot.inventory/InventoryGUIPageBuilder/page/#/PointingToDeclaration/)|  [jvm] val [page](index.md#net.axay.kspigot.inventory/InventoryGUIPageBuilder/page/#/PointingToDeclaration/): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)   <br>
| [transitionFrom](index.md#net.axay.kspigot.inventory/InventoryGUIPageBuilder/transitionFrom/#/PointingToDeclaration/)|  [jvm] var [transitionFrom](index.md#net.axay.kspigot.inventory/InventoryGUIPageBuilder/transitionFrom/#/PointingToDeclaration/): [PageChangeEffect](../-page-change-effect/index.md)?   <br>
| [transitionTo](index.md#net.axay.kspigot.inventory/InventoryGUIPageBuilder/transitionTo/#/PointingToDeclaration/)|  [jvm] var [transitionTo](index.md#net.axay.kspigot.inventory/InventoryGUIPageBuilder/transitionTo/#/PointingToDeclaration/): [PageChangeEffect](../-page-change-effect/index.md)?   <br>
| [type](index.md#net.axay.kspigot.inventory/InventoryGUIPageBuilder/type/#/PointingToDeclaration/)|  [jvm] val [type](index.md#net.axay.kspigot.inventory/InventoryGUIPageBuilder/type/#/PointingToDeclaration/): [InventoryType](../-inventory-type/index.md)<[T](index.md)>   <br>

