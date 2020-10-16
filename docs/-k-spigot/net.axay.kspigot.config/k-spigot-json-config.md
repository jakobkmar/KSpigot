---
title: kSpigotJsonConfig -
---
//[KSpigot](../index.md)/[net.axay.kspigot.config](index.md)/[kSpigotJsonConfig](k-spigot-json-config.md)



# kSpigotJsonConfig  
[jvm]  
Brief description  




Creates a new ConfigDelegate object.



You can use this as a delegate (with **by**) or a normal object. (Delegate allows direct access to the config object, but does not provide save() or reload() methods.)





## Parameters  
  
jvm  
  
|  Name|  Summary| 
|---|---|
| default| <br><br>Optional default config, which will be used if there is no config file and a new one should be created.<br><br>
| file| <br><br>The path to the config.<br><br>
| T| <br><br>The class type of the config.<br><br>
  
  
Content  
inline fun <[T](k-spigot-json-config.md) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)> [kSpigotJsonConfig](k-spigot-json-config.md)(file: [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html), noinline default: () -> [T](k-spigot-json-config.md)?): [ConfigDelegate](-config-delegate/index.md)<[T](k-spigot-json-config.md)>  



