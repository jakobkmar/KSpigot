---
title: net.axay.kspigot.main -
---
//[KSpigot](../index.md)/[net.axay.kspigot.main](index.md)



# Package net.axay.kspigot.main  


## Types  
  
|  Name|  Summary| 
|---|---|
| [KSpigot](-k-spigot/index.md)| [jvm]  <br>Brief description  <br><br><br><br><br>This is the main instance of kSpigot.<br><br><br><br>This class replaces (and inherits from) the JavaPlugin class. Your main plugin class should inherit from this abstract class.<br><br><br><br>**Instead** of overriding onLoad(), onEnable() and onDisable()**override**:<br><br><ul><li>load() (called first)</li><li>startup()  (called second)</li><li>shutdown() (called in the "end")</li></ul><br><br>  <br>Content  <br>abstract class [KSpigot](-k-spigot/index.md) : JavaPlugin  <br><br><br>
| [ValueHolder](-value-holder/index.md)| [jvm]  <br>Content  <br>object [ValueHolder](-value-holder/index.md)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [KSpigotMainInstance](index.md#net.axay.kspigot.main//KSpigotMainInstance/#/PointingToDeclaration/)|  [jvm] lateinit var [KSpigotMainInstance](index.md#net.axay.kspigot.main//KSpigotMainInstance/#/PointingToDeclaration/): [KSpigot](-k-spigot/index.md)   <br>

