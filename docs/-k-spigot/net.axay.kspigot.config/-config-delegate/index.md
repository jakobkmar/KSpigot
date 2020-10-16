---
title: ConfigDelegate -
---
//[KSpigot](../../index.md)/[net.axay.kspigot.config](../index.md)/[ConfigDelegate](index.md)



# ConfigDelegate  
 [jvm] class [ConfigDelegate](index.md)<[T](index.md) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)>(**configClass**: [KClass](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)<[T](index.md)>, **file**: [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html), **defaultCallback**: () -> [T](index.md)?)   


## See also  
  
jvm  
  
|  Name|  Summary| 
|---|---|
| [kSpigotJsonConfig](../k-spigot-json-config.md)| <br><br><br><br>
  


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [ConfigDelegate](-config-delegate.md)|  [jvm] fun <[T](index.md) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)> [ConfigDelegate](-config-delegate.md)(configClass: [KClass](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)<[T](index.md)>, file: [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html), defaultCallback: () -> [T](index.md)?)   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)| [jvm]  <br>Content  <br>open operator override fun [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [getValue](get-value.md)| [jvm]  <br>Content  <br>operator fun [getValue](get-value.md)(thisRef: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?, property: [KProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.html)<*>): [T](index.md)  <br><br><br>
| [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [reload](reload.md)| [jvm]  <br>Brief description  <br><br><br>Loads the current state of the config on disk to the config object.<br><br>  <br>Content  <br>fun [reload](reload.md)()  <br><br><br>
| [save](save.md)| [jvm]  <br>Brief description  <br><br><br>Saves the config object in its current state to disk.<br><br>  <br>Content  <br>fun [save](save.md)()  <br><br><br>
| [setValue](set-value.md)| [jvm]  <br>Content  <br>operator fun [setValue](set-value.md)(thisRef: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?, property: [KProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.html)<*>, config: [T](index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [data](index.md#net.axay.kspigot.config/ConfigDelegate/data/#/PointingToDeclaration/)|  [jvm] var [data](index.md#net.axay.kspigot.config/ConfigDelegate/data/#/PointingToDeclaration/): [T](index.md)   <br>

