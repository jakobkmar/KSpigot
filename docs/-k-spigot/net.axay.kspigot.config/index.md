---
title: net.axay.kspigot.config -
---
//[KSpigot](../index.md)/[net.axay.kspigot.config](index.md)



# Package net.axay.kspigot.config  


## Types  
  
|  Name|  Summary| 
|---|---|
| [ConfigDelegate](-config-delegate/index.md)| [jvm]  <br>Content  <br>class [ConfigDelegate](-config-delegate/index.md)<[T](-config-delegate/index.md) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)>(**configClass**: [KClass](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)<[T](-config-delegate/index.md)>, **file**: [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html), **defaultCallback**: () -> [T](-config-delegate/index.md)?)  <br><br><br>
| [PluginFile](-plugin-file/index.md)| [jvm]  <br>Content  <br>class [PluginFile](-plugin-file/index.md)(**path**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **child**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) : [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [kSpigotJsonConfig](k-spigot-json-config.md)| [jvm]  <br>Brief description  <br><br><br><br><br>Creates a new ConfigDelegate object.<br><br><br><br>You can use this as a delegate (with **by**) or a normal object. (Delegate allows direct access to the config object, but does not provide save() or reload() methods.)<br><br><br><br>  <br>Content  <br>inline fun <[T](k-spigot-json-config.md) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)> [kSpigotJsonConfig](k-spigot-json-config.md)(file: [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html), noinline default: () -> [T](k-spigot-json-config.md)?): [ConfigDelegate](-config-delegate/index.md)<[T](k-spigot-json-config.md)>  <br><br><br>

