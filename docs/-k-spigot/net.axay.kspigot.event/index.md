---
title: net.axay.kspigot.event -
---
//[KSpigot](../index.md)/[net.axay.kspigot.event](index.md)



# Package net.axay.kspigot.event  


## Types  
  
|  Name|  Summary| 
|---|---|
| [SingleListener](-single-listener/index.md)| [jvm]  <br>Brief description  <br><br><br>This class represents a Listener with only one event to listen to.<br><br>  <br>Content  <br>interface [SingleListener](-single-listener/index.md)<[T](-single-listener/index.md) : Event> : Listener  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [listen](listen.md)| [jvm]  <br>Content  <br>inline fun <[T](listen.md) : Event> [listen](listen.md)(priority: EventPriority, ignoreCancelled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), crossinline onEvent: ([T](listen.md)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [SingleListener](-single-listener/index.md)<[T](listen.md)>  <br><br><br>
| [register](register.md)| [jvm]  <br>Brief description  <br><br><br>Shortcut for registering this listener on the given plugin.<br><br>  <br>Content  <br>fun Listener.[register](register.md)()  <br><br><br>[jvm]  <br>Brief description  <br><br><br>Registers the [SingleListener](-single-listener/index.md) with its event listener.<br><br>  <br>Content  <br>inline fun <[T](register.md) : Event> [SingleListener](-single-listener/index.md)<[T](register.md)>.[register](register.md)(priority: EventPriority, ignoreCancelled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))  <br><br><br>[jvm]  <br>Brief description  <br><br><br>Registers the event with a custom event executor.<br><br>  <br>Content  <br>inline fun <[T](register.md) : Event> Listener.[register](register.md)(priority: EventPriority, ignoreCancelled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), noinline executor: (Listener, Event) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))  <br><br><br>
| [unregister](unregister.md)| [jvm]  <br>Brief description  <br><br><br>Shortcut for unregistering all events in this listener.<br><br>  <br>Content  <br>fun Listener.[unregister](unregister.md)()  <br><br><br>

