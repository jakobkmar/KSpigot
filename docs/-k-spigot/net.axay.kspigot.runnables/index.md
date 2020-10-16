---
title: net.axay.kspigot.runnables -
---
//[KSpigot](../index.md)/[net.axay.kspigot.runnables](index.md)



# Package net.axay.kspigot.runnables  


## Types  
  
|  Name|  Summary| 
|---|---|
| [ChainedRunnablePart](-chained-runnable-part/index.md)| [jvm]  <br>Content  <br>abstract class [ChainedRunnablePart](-chained-runnable-part/index.md)<[T](-chained-runnable-part/index.md), [R](-chained-runnable-part/index.md)>(**sync**: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))  <br><br><br>
| [ChainedRunnablePartFirst](-chained-runnable-part-first/index.md)| [jvm]  <br>Content  <br>class [ChainedRunnablePartFirst](-chained-runnable-part-first/index.md)<[R](-chained-runnable-part-first/index.md)>(**runnable**: () -> [R](-chained-runnable-part-first/index.md), **sync**: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) : [ChainedRunnablePart](-chained-runnable-part/index.md)<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), [R](-chained-runnable-part-first/index.md)>   <br><br><br>
| [ChainedRunnablePartThen](-chained-runnable-part-then/index.md)| [jvm]  <br>Content  <br>class [ChainedRunnablePartThen](-chained-runnable-part-then/index.md)<[T](-chained-runnable-part-then/index.md), [R](-chained-runnable-part-then/index.md)>(**runnable**: ([T](-chained-runnable-part-then/index.md)) -> [R](-chained-runnable-part-then/index.md), **sync**: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), **previous**: [ChainedRunnablePart](-chained-runnable-part/index.md)<*, [T](-chained-runnable-part-then/index.md)>) : [ChainedRunnablePart](-chained-runnable-part/index.md)<[T](-chained-runnable-part-then/index.md), [R](-chained-runnable-part-then/index.md)>   <br><br><br>
| [KSpigotRunnable](-k-spigot-runnable/index.md)| [jvm]  <br>Content  <br>abstract class [KSpigotRunnable](-k-spigot-runnable/index.md)(**counterUp**: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, **counterDownToOne**: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, **counterDownToZero**: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?) : BukkitRunnable  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [async](async.md)| [jvm]  <br>Brief description  <br><br><br>Starts an asynchronous task.<br><br>  <br>Content  <br>fun [async](async.md)(runnable: () -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): BukkitTask  <br><br><br>
| [firstAsync](first-async.md)| [jvm]  <br>Content  <br>fun <[R](first-async.md)> [firstAsync](first-async.md)(runnable: () -> [R](first-async.md)): [ChainedRunnablePartFirst](-chained-runnable-part-first/index.md)<[R](first-async.md)>  <br><br><br>
| [firstDo](first-do.md)| [jvm]  <br>Content  <br>fun <[R](first-do.md)> [firstDo](first-do.md)(sync: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), runnable: () -> [R](first-do.md)): [ChainedRunnablePartFirst](-chained-runnable-part-first/index.md)<[R](first-do.md)>  <br><br><br>
| [firstSync](first-sync.md)| [jvm]  <br>Content  <br>fun <[R](first-sync.md)> [firstSync](first-sync.md)(runnable: () -> [R](first-sync.md)): [ChainedRunnablePartFirst](-chained-runnable-part-first/index.md)<[R](first-sync.md)>  <br><br><br>
| [sync](sync.md)| [jvm]  <br>Brief description  <br><br><br>Starts a synchronous task.<br><br>  <br>Content  <br>fun [sync](sync.md)(runnable: () -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): BukkitTask  <br><br><br>
| [task](task.md)| [jvm]  <br>Brief description  <br><br><br>Starts a new BukkitRunnable.<br><br>  <br>Content  <br>fun [task](task.md)(sync: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), delay: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), period: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, howOften: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, safe: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), endCallback: () -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?, runnable: ([KSpigotRunnable](-k-spigot-runnable/index.md)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?)  <br><br><br>
| [thenAsync](then-async.md)| [jvm]  <br>Content  <br>fun <[T](then-async.md), [R](then-async.md), [U](then-async.md)> [ChainedRunnablePart](-chained-runnable-part/index.md)<[T](then-async.md), [R](then-async.md)>.[thenAsync](then-async.md)(runnable: ([R](then-async.md)) -> [U](then-async.md)): [ChainedRunnablePartThen](-chained-runnable-part-then/index.md)<[R](then-async.md), [U](then-async.md)>  <br><br><br>
| [thenDo](then-do.md)| [jvm]  <br>Content  <br>fun <[T](then-do.md), [R](then-do.md), [U](then-do.md)> [ChainedRunnablePart](-chained-runnable-part/index.md)<[T](then-do.md), [R](then-do.md)>.[thenDo](then-do.md)(sync: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), runnable: ([R](then-do.md)) -> [U](then-do.md)): [ChainedRunnablePartThen](-chained-runnable-part-then/index.md)<[R](then-do.md), [U](then-do.md)>  <br><br><br>
| [thenSync](then-sync.md)| [jvm]  <br>Content  <br>fun <[T](then-sync.md), [R](then-sync.md), [U](then-sync.md)> [ChainedRunnablePart](-chained-runnable-part/index.md)<[T](then-sync.md), [R](then-sync.md)>.[thenSync](then-sync.md)(runnable: ([R](then-sync.md)) -> [U](then-sync.md)): [ChainedRunnablePartThen](-chained-runnable-part-then/index.md)<[R](then-sync.md), [U](then-sync.md)>  <br><br><br>

