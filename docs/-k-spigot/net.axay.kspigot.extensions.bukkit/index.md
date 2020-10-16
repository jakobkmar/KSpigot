---
title: net.axay.kspigot.extensions.bukkit -
---
//[KSpigot](../index.md)/[net.axay.kspigot.extensions.bukkit](index.md)



# Package net.axay.kspigot.extensions.bukkit  


## Functions  
  
|  Name|  Summary| 
|---|---|
| [appear](appear.md)| [jvm]  <br>Brief description  <br><br><br>Shows the player for all [onlinePlayers](../net.axay.kspigot.extensions/index.md#net.axay.kspigot.extensions//onlinePlayers/#/PointingToDeclaration/).<br><br>  <br>Content  <br>fun Player.[appear](appear.md)()  <br><br><br>
| [disappear](disappear.md)| [jvm]  <br>Brief description  <br><br><br>Hides the player for all [onlinePlayers](../net.axay.kspigot.extensions/index.md#net.axay.kspigot.extensions//onlinePlayers/#/PointingToDeclaration/).<br><br>  <br>Content  <br>fun Player.[disappear](disappear.md)()  <br><br><br>
| [error](error.md)| [jvm]  <br>Content  <br>fun CommandSender.[error](error.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), plugin: Plugin?)  <br><br><br>
| [feed](feed.md)| [jvm]  <br>Brief description  <br><br><br>Sets the players' foodLevel to the max possible value.<br><br>  <br>Content  <br>fun Player.[feed](feed.md)()  <br><br><br>
| [feedSaturate](feed-saturate.md)| [jvm]  <br>Brief description  <br><br><br>Feeds and saturates the player.<br><br>  <br>Content  <br>fun Player.[feedSaturate](feed-saturate.md)()  <br><br><br>
| [getHandItem](get-hand-item.md)| [jvm]  <br>Brief description  <br><br><br>Returns the itemInHand of the given EquipmentSlot if it is an hand slot.<br><br>  <br>Content  <br>fun Player.[getHandItem](get-hand-item.md)(hand: EquipmentSlot?): ItemStack?  <br><br><br>
| [heal](heal.md)| [jvm]  <br>Brief description  <br><br><br>Sets the entities' health to the max possible value.<br><br>  <br>Content  <br>fun LivingEntity.[heal](heal.md)()  <br><br><br>
| [info](info.md)| [jvm]  <br>Content  <br>fun CommandSender.[info](info.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), plugin: Plugin?)  <br><br><br>
| [kick](kick.md)| [jvm]  <br>Brief description  <br><br><br>Kicks the player from the server.<br><br>  <br>Content  <br>fun Player.[kick](kick.md)(reason: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?)  <br><br><br>
| [kill](kill.md)| [jvm]  <br>Brief description  <br><br><br>Kills the damageable.<br><br>  <br>Content  <br>fun Damageable.[kill](kill.md)()  <br><br><br>
| [print](print.md)| [jvm]  <br>Content  <br>fun CommandSender.[print](print.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), plugin: Plugin?)  <br><br><br>
| [printColoredPrefix](print-colored-prefix.md)| [jvm]  <br>Brief description  <br><br><br>Sends the given message and adds the given prefix with the given color to it.<br><br>  <br>Content  <br>fun CommandSender.[printColoredPrefix](print-colored-prefix.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), textColor: ChatColor, prefix: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), prefixColor: ChatColor)  <br><br><br>
| [saturate](saturate.md)| [jvm]  <br>Brief description  <br><br><br>Sets the players' saturation to the current max possible value.<br><br>  <br>Content  <br>fun Player.[saturate](saturate.md)()  <br><br><br>
| [spawnCleanEntity](spawn-clean-entity.md)| [jvm]  <br>Brief description  <br><br><br>Spawns an entity without any variations in color, type etc...<br><br>  <br>Content  <br>fun Location.[spawnCleanEntity](spawn-clean-entity.md)(entityType: EntityType): Entity?  <br><br><br>
| [success](success.md)| [jvm]  <br>Content  <br>fun CommandSender.[success](success.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), plugin: Plugin?)  <br><br><br>
| [title](title.md)| [jvm]  <br>Content  <br>fun Player.[title](title.md)(mainText: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, subText: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, fadeIn: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), stay: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), fadeOut: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))  <br><br><br>
| [warn](warn.md)| [jvm]  <br>Content  <br>fun CommandSender.[warn](warn.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), plugin: Plugin?)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [isDamageable](index.md#net.axay.kspigot.extensions.bukkit//isDamageable/org.bukkit.GameMode#/PointingToDeclaration/)|  [jvm] <br><br><br><br>val GameMode.[isDamageable](index.md#net.axay.kspigot.extensions.bukkit//isDamageable/org.bukkit.GameMode#/PointingToDeclaration/): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)   <br>
| [isFeetInWater](index.md#net.axay.kspigot.extensions.bukkit//isFeetInWater/org.bukkit.entity.Entity#/PointingToDeclaration/)|  [jvm] <br><br>Checks if the entities' feet are in water.<br><br>val Entity.[isFeetInWater](index.md#net.axay.kspigot.extensions.bukkit//isFeetInWater/org.bukkit.entity.Entity#/PointingToDeclaration/): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)   <br>
| [isGroundSolid](index.md#net.axay.kspigot.extensions.bukkit//isGroundSolid/org.bukkit.entity.Entity#/PointingToDeclaration/)|  [jvm] <br><br>Checks if the entity stands on solid ground.<br><br>val Entity.[isGroundSolid](index.md#net.axay.kspigot.extensions.bukkit//isGroundSolid/org.bukkit.entity.Entity#/PointingToDeclaration/): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)   <br>
| [isHeadInWater](index.md#net.axay.kspigot.extensions.bukkit//isHeadInWater/org.bukkit.entity.LivingEntity#/PointingToDeclaration/)|  [jvm] <br><br>Checks if the entities' head is in water.<br><br>val LivingEntity.[isHeadInWater](index.md#net.axay.kspigot.extensions.bukkit//isHeadInWater/org.bukkit.entity.LivingEntity#/PointingToDeclaration/): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)   <br>
| [isInWater](index.md#net.axay.kspigot.extensions.bukkit//isInWater/org.bukkit.entity.LivingEntity#/PointingToDeclaration/)|  [jvm] <br><br>Checks if the entity is completely in water.<br><br>val LivingEntity.[isInWater](index.md#net.axay.kspigot.extensions.bukkit//isInWater/org.bukkit.entity.LivingEntity#/PointingToDeclaration/): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)   <br>

