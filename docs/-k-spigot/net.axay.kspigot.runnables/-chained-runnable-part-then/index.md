---
title: ChainedRunnablePartThen -
---
//[KSpigot](../../index.md)/[net.axay.kspigot.runnables](../index.md)/[ChainedRunnablePartThen](index.md)



# ChainedRunnablePartThen  
 [jvm] class [ChainedRunnablePartThen](index.md)<[T](index.md), [R](index.md)>(**runnable**: ([T](index.md)) -> [R](index.md), **sync**: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), **previous**: [ChainedRunnablePart](../-chained-runnable-part/index.md)<*, [T](index.md)>) : [ChainedRunnablePart](../-chained-runnable-part/index.md)<[T](index.md), [R](index.md)>    


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)| [jvm]  <br>Content  <br>open operator override fun [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [execute](execute.md)| [jvm]  <br>Content  <br>open override fun [execute](execute.md)()  <br><br><br>
| [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [next](index.md#net.axay.kspigot.runnables/ChainedRunnablePartThen/next/#/PointingToDeclaration/)|  [jvm] override var [next](index.md#net.axay.kspigot.runnables/ChainedRunnablePartThen/next/#/PointingToDeclaration/): [ChainedRunnablePart](../-chained-runnable-part/index.md)<[R](index.md), *>?   <br>
| [previous](index.md#net.axay.kspigot.runnables/ChainedRunnablePartThen/previous/#/PointingToDeclaration/)|  [jvm] val [previous](index.md#net.axay.kspigot.runnables/ChainedRunnablePartThen/previous/#/PointingToDeclaration/): [ChainedRunnablePart](../-chained-runnable-part/index.md)<*, [T](index.md)>   <br>
| [runnable](index.md#net.axay.kspigot.runnables/ChainedRunnablePartThen/runnable/#/PointingToDeclaration/)|  [jvm] val [runnable](index.md#net.axay.kspigot.runnables/ChainedRunnablePartThen/runnable/#/PointingToDeclaration/): ([T](index.md)) -> [R](index.md)   <br>
| [sync](index.md#net.axay.kspigot.runnables/ChainedRunnablePartThen/sync/#/PointingToDeclaration/)|  [jvm] override val [sync](index.md#net.axay.kspigot.runnables/ChainedRunnablePartThen/sync/#/PointingToDeclaration/): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)   <br>

