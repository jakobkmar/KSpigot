---
title: register -
---
//[KSpigot](../index.md)/[net.axay.kspigot.event](index.md)/[register](register.md)



# register  
[jvm]  
Brief description  


Shortcut for registering this listener on the given plugin.

  
Content  
fun Listener.[register](register.md)()  


[jvm]  
Brief description  


Registers the event with a custom event executor.



## Parameters  
  
jvm  
  
|  Name|  Summary| 
|---|---|
| executor| <br><br>handles incoming events<br><br>
| ignoreCancelled| <br><br>if manual cancellation should be ignored<br><br>
| priority| <br><br>the priority when multiple listeners handle this event<br><br>
| T| <br><br>the type of event<br><br>
  
  
Content  
inline fun <[T](register.md) : Event> Listener.[register](register.md)(priority: EventPriority, ignoreCancelled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), noinline executor: (Listener, Event) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  


[jvm]  
Brief description  


Registers the [SingleListener](-single-listener/index.md) with its event listener.



## Parameters  
  
jvm  
  
|  Name|  Summary| 
|---|---|
| ignoreCancelled| <br><br>if manual cancellation should be ignored<br><br>
| priority| <br><br>the priority when multiple listeners handle this event<br><br>
  
  
Content  
inline fun <[T](register.md) : Event> [SingleListener](-single-listener/index.md)<[T](register.md)>.[register](register.md)(priority: EventPriority, ignoreCancelled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))  



