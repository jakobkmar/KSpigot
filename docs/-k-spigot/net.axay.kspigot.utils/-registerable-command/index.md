---
title: RegisterableCommand -
---
//[KSpigot](../../index.md)/[net.axay.kspigot.utils](../index.md)/[RegisterableCommand](index.md)



# RegisterableCommand  
 [jvm] interface [RegisterableCommand](index.md) : CommandExecutor   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)| [jvm]  <br>Content  <br>open operator override fun [equals](index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [hashCode](index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [onCommand](index.md#org.bukkit.command/CommandExecutor/onCommand/#org.bukkit.command.CommandSender#org.bukkit.command.Command#kotlin.String#kotlin.Array[kotlin.String]/PointingToDeclaration/)| [jvm]  <br>Content  <br>abstract override fun [onCommand](index.md#org.bukkit.command/CommandExecutor/onCommand/#org.bukkit.command.CommandSender#org.bukkit.command.Command#kotlin.String#kotlin.Array[kotlin.String]/PointingToDeclaration/)(@NotNull()p0: CommandSender, @NotNull()p1: Command, @NotNull()p2: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @NotNull()p3: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [registerAs](register-as.md)| [jvm]  <br>Brief description  <br><br><br>Registers this executor for the given command and for the given instance of kSpigot.<br><br>  <br>Content  <br>open fun [registerAs](register-as.md)(commandName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [toString](index.md#kotlin/Any/toString/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [toString](index.md#kotlin/Any/toString/#/PointingToDeclaration/)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>

