---
title: task -
---
//[KSpigot](../index.md)/[net.axay.kspigot.runnables](index.md)/[task](task.md)



# task  
[jvm]  
Brief description  


Starts a new BukkitRunnable.



## Parameters  
  
jvm  
  
|  Name|  Summary| 
|---|---|
| delay| <br><br>the delay (in ticks) until the first execution of the task<br><br>
| endCallback| <br><br>code that should always be executed when the runnable ends<br><br>
| howOften| <br><br>how many times the task should be executed - null for infinite execution<br><br>
| period| <br><br>at which interval (in ticks) the task should be repeated<br><br>
| runnable| <br><br>the runnable which should be executed each repetition<br><br>
| safe| <br><br>if the endCallback of the runnable should always be executed, even if the server shuts down or the runnable ends prematurely<br><br>
| sync| <br><br>if the runnable should run sync (true) or async (false)<br><br>
  
  
Content  
fun [task](task.md)(sync: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), delay: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), period: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, howOften: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, safe: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), endCallback: () -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?, runnable: ([KSpigotRunnable](-k-spigot-runnable/index.md)) -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?)  



