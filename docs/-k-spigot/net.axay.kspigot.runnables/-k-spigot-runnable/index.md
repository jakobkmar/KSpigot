---
title: KSpigotRunnable -
---
//[KSpigot](../../index.md)/[net.axay.kspigot.runnables](../index.md)/[KSpigotRunnable](index.md)



# KSpigotRunnable  
 [jvm] abstract class [KSpigotRunnable](index.md)(**counterUp**: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, **counterDownToOne**: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, **counterDownToZero**: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?) : BukkitRunnable   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [cancel](index.md#org.bukkit.scheduler/BukkitRunnable/cancel/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [cancel](index.md#org.bukkit.scheduler/BukkitRunnable/cancel/#/PointingToDeclaration/)()  <br><br><br>
| [checkNotYetScheduled](index.md#org.bukkit.scheduler/BukkitRunnable/checkNotYetScheduled/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [checkNotYetScheduled](index.md#org.bukkit.scheduler/BukkitRunnable/checkNotYetScheduled/#/PointingToDeclaration/)()  <br><br><br>
| [checkScheduled](index.md#org.bukkit.scheduler/BukkitRunnable/checkScheduled/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [checkScheduled](index.md#org.bukkit.scheduler/BukkitRunnable/checkScheduled/#/PointingToDeclaration/)()  <br><br><br>
| [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)| [jvm]  <br>Content  <br>open operator override fun [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [getTaskId](index.md#org.bukkit.scheduler/BukkitRunnable/getTaskId/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [getTaskId](index.md#org.bukkit.scheduler/BukkitRunnable/getTaskId/#/PointingToDeclaration/)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [isCancelled](index.md#org.bukkit.scheduler/BukkitRunnable/isCancelled/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [isCancelled](index.md#org.bukkit.scheduler/BukkitRunnable/isCancelled/#/PointingToDeclaration/)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [run](index.md#java.lang/Runnable/run/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>abstract override fun [run](index.md#java.lang/Runnable/run/#/PointingToDeclaration/)()  <br><br><br>
| [runTask](index.md#org.bukkit.scheduler/BukkitRunnable/runTask/#org.bukkit.plugin.Plugin/PointingToDeclaration/)| [jvm]  <br>Content  <br>@NotNull()  <br>  <br>open override fun [runTask](index.md#org.bukkit.scheduler/BukkitRunnable/runTask/#org.bukkit.plugin.Plugin/PointingToDeclaration/)(@NotNull()p0: Plugin): BukkitTask  <br><br><br>
| [runTaskAsynchronously](index.md#org.bukkit.scheduler/BukkitRunnable/runTaskAsynchronously/#org.bukkit.plugin.Plugin/PointingToDeclaration/)| [jvm]  <br>Content  <br>@NotNull()  <br>  <br>open override fun [runTaskAsynchronously](index.md#org.bukkit.scheduler/BukkitRunnable/runTaskAsynchronously/#org.bukkit.plugin.Plugin/PointingToDeclaration/)(@NotNull()p0: Plugin): BukkitTask  <br><br><br>
| [runTaskLater](index.md#org.bukkit.scheduler/BukkitRunnable/runTaskLater/#org.bukkit.plugin.Plugin#kotlin.Long/PointingToDeclaration/)| [jvm]  <br>Content  <br>@NotNull()  <br>  <br>open override fun [runTaskLater](index.md#org.bukkit.scheduler/BukkitRunnable/runTaskLater/#org.bukkit.plugin.Plugin#kotlin.Long/PointingToDeclaration/)(@NotNull()p0: Plugin, p1: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)): BukkitTask  <br><br><br>
| [runTaskLaterAsynchronously](index.md#org.bukkit.scheduler/BukkitRunnable/runTaskLaterAsynchronously/#org.bukkit.plugin.Plugin#kotlin.Long/PointingToDeclaration/)| [jvm]  <br>Content  <br>@NotNull()  <br>  <br>open override fun [runTaskLaterAsynchronously](index.md#org.bukkit.scheduler/BukkitRunnable/runTaskLaterAsynchronously/#org.bukkit.plugin.Plugin#kotlin.Long/PointingToDeclaration/)(@NotNull()p0: Plugin, p1: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)): BukkitTask  <br><br><br>
| [runTaskTimer](index.md#org.bukkit.scheduler/BukkitRunnable/runTaskTimer/#org.bukkit.plugin.Plugin#kotlin.Long#kotlin.Long/PointingToDeclaration/)| [jvm]  <br>Content  <br>@NotNull()  <br>  <br>open override fun [runTaskTimer](index.md#org.bukkit.scheduler/BukkitRunnable/runTaskTimer/#org.bukkit.plugin.Plugin#kotlin.Long#kotlin.Long/PointingToDeclaration/)(@NotNull()p0: Plugin, p1: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), p2: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)): BukkitTask  <br><br><br>
| [runTaskTimerAsynchronously](index.md#org.bukkit.scheduler/BukkitRunnable/runTaskTimerAsynchronously/#org.bukkit.plugin.Plugin#kotlin.Long#kotlin.Long/PointingToDeclaration/)| [jvm]  <br>Content  <br>@NotNull()  <br>  <br>open override fun [runTaskTimerAsynchronously](index.md#org.bukkit.scheduler/BukkitRunnable/runTaskTimerAsynchronously/#org.bukkit.plugin.Plugin#kotlin.Long#kotlin.Long/PointingToDeclaration/)(@NotNull()p0: Plugin, p1: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), p2: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)): BukkitTask  <br><br><br>
| [setupTask](index.md#org.bukkit.scheduler/BukkitRunnable/setupTask/#org.bukkit.scheduler.BukkitTask/PointingToDeclaration/)| [jvm]  <br>Content  <br>@NotNull()  <br>  <br>open override fun [setupTask](index.md#org.bukkit.scheduler/BukkitRunnable/setupTask/#org.bukkit.scheduler.BukkitTask/PointingToDeclaration/)(@NotNull()p0: BukkitTask): BukkitTask  <br><br><br>
| [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [counterDownToOne](index.md#net.axay.kspigot.runnables/KSpigotRunnable/counterDownToOne/#/PointingToDeclaration/)|  [jvm] var [counterDownToOne](index.md#net.axay.kspigot.runnables/KSpigotRunnable/counterDownToOne/#/PointingToDeclaration/): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?   <br>
| [counterDownToZero](index.md#net.axay.kspigot.runnables/KSpigotRunnable/counterDownToZero/#/PointingToDeclaration/)|  [jvm] var [counterDownToZero](index.md#net.axay.kspigot.runnables/KSpigotRunnable/counterDownToZero/#/PointingToDeclaration/): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?   <br>
| [counterUp](index.md#net.axay.kspigot.runnables/KSpigotRunnable/counterUp/#/PointingToDeclaration/)|  [jvm] var [counterUp](index.md#net.axay.kspigot.runnables/KSpigotRunnable/counterUp/#/PointingToDeclaration/): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?   <br>
| [task](index.md#net.axay.kspigot.runnables/KSpigotRunnable/task/#/PointingToDeclaration/)|  [jvm] override val [task](index.md#net.axay.kspigot.runnables/KSpigotRunnable/task/#/PointingToDeclaration/): BukkitTask   <br>

