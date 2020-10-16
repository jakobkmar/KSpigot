---
title: ChainedRunnablePart -
---
//[KSpigot](../../index.md)/[net.axay.kspigot.runnables](../index.md)/[ChainedRunnablePart](index.md)



# ChainedRunnablePart  
 [jvm] abstract class [ChainedRunnablePart](index.md)<[T](index.md), [R](index.md)>(**sync**: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)| [jvm]  <br>Content  <br>open operator override fun [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [execute](execute.md)| [jvm]  <br>Content  <br>abstract fun [execute](execute.md)()  <br><br><br>
| [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [next](index.md#net.axay.kspigot.runnables/ChainedRunnablePart/next/#/PointingToDeclaration/)|  [jvm] var [next](index.md#net.axay.kspigot.runnables/ChainedRunnablePart/next/#/PointingToDeclaration/): [ChainedRunnablePart](index.md)<[R](index.md), *>?   <br>
| [sync](index.md#net.axay.kspigot.runnables/ChainedRunnablePart/sync/#/PointingToDeclaration/)|  [jvm] val [sync](index.md#net.axay.kspigot.runnables/ChainedRunnablePart/sync/#/PointingToDeclaration/): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)   <br>


## Inheritors  
  
|  Name| 
|---|
| [ChainedRunnablePartFirst](../-chained-runnable-part-first/index.md)
| [ChainedRunnablePartThen](../-chained-runnable-part-then/index.md)


## Extensions  
  
|  Name|  Summary| 
|---|---|
| [thenAsync](../then-async.md)| [jvm]  <br>Content  <br>fun <[T](../then-async.md), [R](../then-async.md), [U](../then-async.md)> [ChainedRunnablePart](index.md)<[T](../then-async.md), [R](../then-async.md)>.[thenAsync](../then-async.md)(runnable: ([R](../then-async.md)) -> [U](../then-async.md)): [ChainedRunnablePartThen](../-chained-runnable-part-then/index.md)<[R](../then-async.md), [U](../then-async.md)>  <br><br><br>
| [thenDo](../then-do.md)| [jvm]  <br>Content  <br>fun <[T](../then-do.md), [R](../then-do.md), [U](../then-do.md)> [ChainedRunnablePart](index.md)<[T](../then-do.md), [R](../then-do.md)>.[thenDo](../then-do.md)(sync: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), runnable: ([R](../then-do.md)) -> [U](../then-do.md)): [ChainedRunnablePartThen](../-chained-runnable-part-then/index.md)<[R](../then-do.md), [U](../then-do.md)>  <br><br><br>
| [thenSync](../then-sync.md)| [jvm]  <br>Content  <br>fun <[T](../then-sync.md), [R](../then-sync.md), [U](../then-sync.md)> [ChainedRunnablePart](index.md)<[T](../then-sync.md), [R](../then-sync.md)>.[thenSync](../then-sync.md)(runnable: ([R](../then-sync.md)) -> [U](../then-sync.md)): [ChainedRunnablePartThen](../-chained-runnable-part-then/index.md)<[R](../then-sync.md), [U](../then-sync.md)>  <br><br><br>

