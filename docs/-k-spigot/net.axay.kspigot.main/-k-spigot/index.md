---
title: KSpigot -
---
//[KSpigot](../../index.md)/[net.axay.kspigot.main](../index.md)/[KSpigot](index.md)



# KSpigot  
 [jvm] 



This is the main instance of kSpigot.



This class replaces (and inherits from) the JavaPlugin class. Your main plugin class should inherit from this abstract class.



**Instead** of overriding onLoad(), onEnable() and onDisable()**override**:

<ul><li>load() (called first)</li><li>startup()  (called second)</li><li>shutdown() (called in the "end")</li></ul>

abstract class [KSpigot](index.md) : JavaPlugin   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [KSpigot](-k-spigot.md)|  [jvm] fun [KSpigot](-k-spigot.md)()   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](index.md#org.bukkit.plugin/PluginBase/equals/#kotlin.Any?/PointingToDeclaration/)| [jvm]  <br>Content  <br>operator override fun [equals](index.md#org.bukkit.plugin/PluginBase/equals/#kotlin.Any?/PointingToDeclaration/)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [getClassLoader](index.md#org.bukkit.plugin.java/JavaPlugin/getClassLoader/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>@NotNull()  <br>  <br>override fun [getClassLoader](index.md#org.bukkit.plugin.java/JavaPlugin/getClassLoader/#/PointingToDeclaration/)(): [ClassLoader](https://docs.oracle.com/javase/8/docs/api/java/lang/ClassLoader.html)  <br><br><br>
| [getCommand](index.md#org.bukkit.plugin.java/JavaPlugin/getCommand/#kotlin.String/PointingToDeclaration/)| [jvm]  <br>Content  <br>@Nullable()  <br>  <br>open override fun [getCommand](index.md#org.bukkit.plugin.java/JavaPlugin/getCommand/#kotlin.String/PointingToDeclaration/)(@NotNull()p0: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): PluginCommand?  <br><br><br>
| [getConfig](index.md#org.bukkit.plugin.java/JavaPlugin/getConfig/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>@NotNull()  <br>  <br>open override fun [getConfig](index.md#org.bukkit.plugin.java/JavaPlugin/getConfig/#/PointingToDeclaration/)(): FileConfiguration  <br><br><br>
| [getDataFolder](index.md#org.bukkit.plugin.java/JavaPlugin/getDataFolder/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>@NotNull()  <br>  <br>override fun [getDataFolder](index.md#org.bukkit.plugin.java/JavaPlugin/getDataFolder/#/PointingToDeclaration/)(): [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)  <br><br><br>
| [getDefaultWorldGenerator](index.md#org.bukkit.plugin.java/JavaPlugin/getDefaultWorldGenerator/#kotlin.String#kotlin.String?/PointingToDeclaration/)| [jvm]  <br>Content  <br>@Nullable()  <br>  <br>open override fun [getDefaultWorldGenerator](index.md#org.bukkit.plugin.java/JavaPlugin/getDefaultWorldGenerator/#kotlin.String#kotlin.String?/PointingToDeclaration/)(@NotNull()p0: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Nullable()p1: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?): ChunkGenerator?  <br><br><br>
| [getDescription](index.md#org.bukkit.plugin.java/JavaPlugin/getDescription/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>@NotNull()  <br>  <br>override fun [getDescription](index.md#org.bukkit.plugin.java/JavaPlugin/getDescription/#/PointingToDeclaration/)(): PluginDescriptionFile  <br><br><br>
| [getFile](index.md#org.bukkit.plugin.java/JavaPlugin/getFile/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>@NotNull()  <br>  <br>open override fun [getFile](index.md#org.bukkit.plugin.java/JavaPlugin/getFile/#/PointingToDeclaration/)(): [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)  <br><br><br>
| [getLogger](index.md#org.bukkit.plugin.java/JavaPlugin/getLogger/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>@NotNull()  <br>  <br>open override fun [getLogger](index.md#org.bukkit.plugin.java/JavaPlugin/getLogger/#/PointingToDeclaration/)(): [Logger](https://docs.oracle.com/javase/8/docs/api/java/util/logging/Logger.html)  <br><br><br>
| [getName](index.md#org.bukkit.plugin/PluginBase/getName/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>@NotNull()  <br>  <br>override fun [getName](index.md#org.bukkit.plugin/PluginBase/getName/#/PointingToDeclaration/)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [getPluginLoader](index.md#org.bukkit.plugin.java/JavaPlugin/getPluginLoader/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>@NotNull()  <br>  <br>override fun [getPluginLoader](index.md#org.bukkit.plugin.java/JavaPlugin/getPluginLoader/#/PointingToDeclaration/)(): PluginLoader  <br><br><br>
| [getResource](index.md#org.bukkit.plugin.java/JavaPlugin/getResource/#kotlin.String/PointingToDeclaration/)| [jvm]  <br>Content  <br>@Nullable()  <br>  <br>open override fun [getResource](index.md#org.bukkit.plugin.java/JavaPlugin/getResource/#kotlin.String/PointingToDeclaration/)(@NotNull()p0: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [InputStream](https://docs.oracle.com/javase/8/docs/api/java/io/InputStream.html)?  <br><br><br>
| [getServer](index.md#org.bukkit.plugin.java/JavaPlugin/getServer/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>@NotNull()  <br>  <br>override fun [getServer](index.md#org.bukkit.plugin.java/JavaPlugin/getServer/#/PointingToDeclaration/)(): Server  <br><br><br>
| [getTextResource](index.md#org.bukkit.plugin.java/JavaPlugin/getTextResource/#kotlin.String/PointingToDeclaration/)| [jvm]  <br>Content  <br>@Nullable()  <br>  <br>override fun [getTextResource](index.md#org.bukkit.plugin.java/JavaPlugin/getTextResource/#kotlin.String/PointingToDeclaration/)(@NotNull()p0: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Reader](https://docs.oracle.com/javase/8/docs/api/java/io/Reader.html)?  <br><br><br>
| [hashCode](index.md#org.bukkit.plugin/PluginBase/hashCode/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>override fun [hashCode](index.md#org.bukkit.plugin/PluginBase/hashCode/#/PointingToDeclaration/)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [init](index.md#org.bukkit.plugin.java/JavaPlugin/init/#org.bukkit.plugin.PluginLoader#org.bukkit.Server#org.bukkit.plugin.PluginDescriptionFile#java.io.File#java.io.File#java.lang.ClassLoader/PointingToDeclaration/)| [jvm]  <br>Content  <br>override fun [init](index.md#org.bukkit.plugin.java/JavaPlugin/init/#org.bukkit.plugin.PluginLoader#org.bukkit.Server#org.bukkit.plugin.PluginDescriptionFile#java.io.File#java.io.File#java.lang.ClassLoader/PointingToDeclaration/)(@NotNull()p0: PluginLoader, @NotNull()p1: Server, @NotNull()p2: PluginDescriptionFile, @NotNull()p3: [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html), @NotNull()p4: [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html), @NotNull()p5: [ClassLoader](https://docs.oracle.com/javase/8/docs/api/java/lang/ClassLoader.html))  <br><br><br>
| [isEnabled](index.md#org.bukkit.plugin.java/JavaPlugin/isEnabled/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>override fun [isEnabled](index.md#org.bukkit.plugin.java/JavaPlugin/isEnabled/#/PointingToDeclaration/)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [isNaggable](index.md#org.bukkit.plugin.java/JavaPlugin/isNaggable/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>override fun [isNaggable](index.md#org.bukkit.plugin.java/JavaPlugin/isNaggable/#/PointingToDeclaration/)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [load](load.md)| [jvm]  <br>Brief description  <br><br><br>Called when the plugin was loaded<br><br>  <br>Content  <br>open fun [load](load.md)()  <br><br><br>
| [onCommand](index.md#org.bukkit.plugin.java/JavaPlugin/onCommand/#org.bukkit.command.CommandSender#org.bukkit.command.Command#kotlin.String#kotlin.Array[kotlin.String]/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [onCommand](index.md#org.bukkit.plugin.java/JavaPlugin/onCommand/#org.bukkit.command.CommandSender#org.bukkit.command.Command#kotlin.String#kotlin.Array[kotlin.String]/PointingToDeclaration/)(@NotNull()p0: CommandSender, @NotNull()p1: Command, @NotNull()p2: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @NotNull()p3: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [onDisable](on-disable.md)| [jvm]  <br>Content  <br>override fun [onDisable](on-disable.md)()  <br><br><br>
| [onEnable](on-enable.md)| [jvm]  <br>Content  <br>override fun [onEnable](on-enable.md)()  <br><br><br>
| [onLoad](on-load.md)| [jvm]  <br>Content  <br>override fun [onLoad](on-load.md)()  <br><br><br>
| [onTabComplete](index.md#org.bukkit.plugin.java/JavaPlugin/onTabComplete/#org.bukkit.command.CommandSender#org.bukkit.command.Command#kotlin.String#kotlin.Array[kotlin.String]/PointingToDeclaration/)| [jvm]  <br>Content  <br>@Nullable()  <br>  <br>open override fun [onTabComplete](index.md#org.bukkit.plugin.java/JavaPlugin/onTabComplete/#org.bukkit.command.CommandSender#org.bukkit.command.Command#kotlin.String#kotlin.Array[kotlin.String]/PointingToDeclaration/)(@NotNull()p0: CommandSender, @NotNull()p1: Command, @NotNull()p2: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @NotNull()p3: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>?  <br><br><br>
| [reloadConfig](index.md#org.bukkit.plugin.java/JavaPlugin/reloadConfig/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [reloadConfig](index.md#org.bukkit.plugin.java/JavaPlugin/reloadConfig/#/PointingToDeclaration/)()  <br><br><br>
| [saveConfig](index.md#org.bukkit.plugin.java/JavaPlugin/saveConfig/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [saveConfig](index.md#org.bukkit.plugin.java/JavaPlugin/saveConfig/#/PointingToDeclaration/)()  <br><br><br>
| [saveDefaultConfig](index.md#org.bukkit.plugin.java/JavaPlugin/saveDefaultConfig/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [saveDefaultConfig](index.md#org.bukkit.plugin.java/JavaPlugin/saveDefaultConfig/#/PointingToDeclaration/)()  <br><br><br>
| [saveResource](index.md#org.bukkit.plugin.java/JavaPlugin/saveResource/#kotlin.String#kotlin.Boolean/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [saveResource](index.md#org.bukkit.plugin.java/JavaPlugin/saveResource/#kotlin.String#kotlin.Boolean/PointingToDeclaration/)(@NotNull()p0: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), p1: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))  <br><br><br>
| [setEnabled](index.md#org.bukkit.plugin.java/JavaPlugin/setEnabled/#kotlin.Boolean/PointingToDeclaration/)| [jvm]  <br>Content  <br>override fun [setEnabled](index.md#org.bukkit.plugin.java/JavaPlugin/setEnabled/#kotlin.Boolean/PointingToDeclaration/)(p0: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))  <br><br><br>
| [setNaggable](index.md#org.bukkit.plugin.java/JavaPlugin/setNaggable/#kotlin.Boolean/PointingToDeclaration/)| [jvm]  <br>Content  <br>override fun [setNaggable](index.md#org.bukkit.plugin.java/JavaPlugin/setNaggable/#kotlin.Boolean/PointingToDeclaration/)(p0: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))  <br><br><br>
| [shutdown](shutdown.md)| [jvm]  <br>Brief description  <br><br><br>Called when the plugin gets disabled<br><br>  <br>Content  <br>open fun [shutdown](shutdown.md)()  <br><br><br>
| [startup](startup.md)| [jvm]  <br>Brief description  <br><br><br>Called when the plugin was enabled<br><br>  <br>Content  <br>open fun [startup](startup.md)()  <br><br><br>
| [toString](index.md#org.bukkit.plugin.java/JavaPlugin/toString/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>@NotNull()  <br>  <br>open override fun [toString](index.md#org.bukkit.plugin.java/JavaPlugin/toString/#/PointingToDeclaration/)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [classLoader](index.md#net.axay.kspigot.main/KSpigot/classLoader/#/PointingToDeclaration/)|  [jvm] override val [classLoader](index.md#net.axay.kspigot.main/KSpigot/classLoader/#/PointingToDeclaration/): [ClassLoader](https://docs.oracle.com/javase/8/docs/api/java/lang/ClassLoader.html)   <br>
| [configFile](index.md#net.axay.kspigot.main/KSpigot/configFile/#/PointingToDeclaration/)|  [jvm] override val [configFile](index.md#net.axay.kspigot.main/KSpigot/configFile/#/PointingToDeclaration/): [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)   <br>
| [dataFolder](index.md#net.axay.kspigot.main/KSpigot/dataFolder/#/PointingToDeclaration/)|  [jvm] override val [dataFolder](index.md#net.axay.kspigot.main/KSpigot/dataFolder/#/PointingToDeclaration/): [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)   <br>
| [description](index.md#net.axay.kspigot.main/KSpigot/description/#/PointingToDeclaration/)|  [jvm] override val [description](index.md#net.axay.kspigot.main/KSpigot/description/#/PointingToDeclaration/): PluginDescriptionFile   <br>
| [file](index.md#net.axay.kspigot.main/KSpigot/file/#/PointingToDeclaration/)|  [jvm] override val [file](index.md#net.axay.kspigot.main/KSpigot/file/#/PointingToDeclaration/): [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)   <br>
| [isEnabled](index.md#net.axay.kspigot.main/KSpigot/isEnabled/#/PointingToDeclaration/)|  [jvm] override val [isEnabled](index.md#net.axay.kspigot.main/KSpigot/isEnabled/#/PointingToDeclaration/): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)   <br>
| [loader](index.md#net.axay.kspigot.main/KSpigot/loader/#/PointingToDeclaration/)|  [jvm] override val [loader](index.md#net.axay.kspigot.main/KSpigot/loader/#/PointingToDeclaration/): PluginLoader   <br>
| [logger](index.md#net.axay.kspigot.main/KSpigot/logger/#/PointingToDeclaration/)|  [jvm] override val [logger](index.md#net.axay.kspigot.main/KSpigot/logger/#/PointingToDeclaration/): PluginLogger   <br>
| [naggable](index.md#net.axay.kspigot.main/KSpigot/naggable/#/PointingToDeclaration/)|  [jvm] override val [naggable](index.md#net.axay.kspigot.main/KSpigot/naggable/#/PointingToDeclaration/): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)   <br>
| [newConfig](index.md#net.axay.kspigot.main/KSpigot/newConfig/#/PointingToDeclaration/)|  [jvm] override val [newConfig](index.md#net.axay.kspigot.main/KSpigot/newConfig/#/PointingToDeclaration/): FileConfiguration   <br>
| [server](index.md#net.axay.kspigot.main/KSpigot/server/#/PointingToDeclaration/)|  [jvm] override val [server](index.md#net.axay.kspigot.main/KSpigot/server/#/PointingToDeclaration/): Server   <br>

