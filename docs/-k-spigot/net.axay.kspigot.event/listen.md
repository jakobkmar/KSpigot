---
title: listen -
---
//[KSpigot](../index.md)/[net.axay.kspigot.event](index.md)/[listen](listen.md)



# listen  
[jvm]  
Brief description  


## Parameters  
  
jvm  
  
|  Name|  Summary| 
|---|---|
| ignoreCancelled| <br><br>if manual cancellation should be ignored<br><br>
| onEvent| <br><br>the event callback<br><br>
| priority| <br><br>the priority when multiple listeners handle this event<br><br>
| T| <br><br>the type of event to listen to<br><br>
  
  
Content  
inline fun <[T](listen.md) : Event> [listen](listen.md)(priority: EventPriority, ignoreCancelled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), crossinline onEvent: ([T](listen.md)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [SingleListener](-single-listener/index.md)<[T](listen.md)>  



