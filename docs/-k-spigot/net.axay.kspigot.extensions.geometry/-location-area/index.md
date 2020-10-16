---
title: LocationArea -
---
//[KSpigot](../../index.md)/[net.axay.kspigot.extensions.geometry](../index.md)/[LocationArea](index.md)



# LocationArea  
 [jvm] class [LocationArea](index.md)(**loc1**: Location, **loc2**: Location)   


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)| [jvm]  <br>Content  <br>open operator override fun [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [isInArea](is-in-area.md)| [jvm]  <br>Content  <br>fun [isInArea](is-in-area.md)(loc: Location, check3d: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), tolerance: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [loc1](index.md#net.axay.kspigot.extensions.geometry/LocationArea/loc1/#/PointingToDeclaration/)|  [jvm] var [loc1](index.md#net.axay.kspigot.extensions.geometry/LocationArea/loc1/#/PointingToDeclaration/): Location   <br>
| [loc2](index.md#net.axay.kspigot.extensions.geometry/LocationArea/loc2/#/PointingToDeclaration/)|  [jvm] var [loc2](index.md#net.axay.kspigot.extensions.geometry/LocationArea/loc2/#/PointingToDeclaration/): Location   <br>
| [maxLoc](index.md#net.axay.kspigot.extensions.geometry/LocationArea/maxLoc/#/PointingToDeclaration/)|  [jvm] val [maxLoc](index.md#net.axay.kspigot.extensions.geometry/LocationArea/maxLoc/#/PointingToDeclaration/): Location   <br>
| [minLoc](index.md#net.axay.kspigot.extensions.geometry/LocationArea/minLoc/#/PointingToDeclaration/)|  [jvm] val [minLoc](index.md#net.axay.kspigot.extensions.geometry/LocationArea/minLoc/#/PointingToDeclaration/): Location   <br>
| [simpleLocationPair](index.md#net.axay.kspigot.extensions.geometry/LocationArea/simpleLocationPair/#/PointingToDeclaration/)|  [jvm] var [simpleLocationPair](index.md#net.axay.kspigot.extensions.geometry/LocationArea/simpleLocationPair/#/PointingToDeclaration/): [SimpleLocationPair](../-simple-location-pair/index.md)   <br>
| [touchedChunks](index.md#net.axay.kspigot.extensions.geometry/LocationArea/touchedChunks/#/PointingToDeclaration/)|  [jvm] val [touchedChunks](index.md#net.axay.kspigot.extensions.geometry/LocationArea/touchedChunks/#/PointingToDeclaration/): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)<Chunk>   <br>
| [world](index.md#net.axay.kspigot.extensions.geometry/LocationArea/world/#/PointingToDeclaration/)|  [jvm] val [world](index.md#net.axay.kspigot.extensions.geometry/LocationArea/world/#/PointingToDeclaration/): World   <br>


## Extensions  
  
|  Name|  Summary| 
|---|---|
| [entities](../../net.axay.kspigot.structures/index.md#net.axay.kspigot.structures//entities/net.axay.kspigot.extensions.geometry.LocationArea#/PointingToDeclaration/)| [jvm]  <br>Content  <br>val [LocationArea](index.md).[entities](../../net.axay.kspigot.structures/index.md#net.axay.kspigot.structures//entities/net.axay.kspigot.extensions.geometry.LocationArea#/PointingToDeclaration/): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)<Entity>  <br><br><br>
| [fillBlocks](../../net.axay.kspigot.structures/index.md#net.axay.kspigot.structures//fillBlocks/net.axay.kspigot.extensions.geometry.LocationArea#/PointingToDeclaration/)| [jvm]  <br>Content  <br>val [LocationArea](index.md).[fillBlocks](../../net.axay.kspigot.structures/index.md#net.axay.kspigot.structures//fillBlocks/net.axay.kspigot.extensions.geometry.LocationArea#/PointingToDeclaration/): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)<Block>  <br><br><br>
| [loadStructure](../../net.axay.kspigot.structures/load-structure.md)| [jvm]  <br>Content  <br>fun [LocationArea](index.md).[loadStructure](../../net.axay.kspigot.structures/load-structure.md)(includeBlocks: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), includeEntities: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [Structure](../../net.axay.kspigot.structures/-structure/index.md)  <br><br><br>

