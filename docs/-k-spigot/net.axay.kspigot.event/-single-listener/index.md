---
title: SingleListener -
---
//[KSpigot](../../index.md)/[net.axay.kspigot.event](../index.md)/[SingleListener](index.md)



# SingleListener  
 [jvm] 

This class represents a Listener with only one event to listen to.

interface [SingleListener](index.md)<[T](index.md) : Event> : Listener   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)| [jvm]  <br>Content  <br>open operator override fun [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [onEvent](on-event.md)| [jvm]  <br>Content  <br>abstract fun [onEvent](on-event.md)(event: [T](index.md))  <br><br><br>
| [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Extensions  
  
|  Name|  Summary| 
|---|---|
| [register](../register.md)| [jvm]  <br>Brief description  <br><br><br>Registers the [SingleListener](index.md) with its event listener.<br><br>  <br>Content  <br>inline fun <[T](../register.md) : Event> [SingleListener](index.md)<[T](../register.md)>.[register](../register.md)(priority: EventPriority, ignoreCancelled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))  <br><br><br>

