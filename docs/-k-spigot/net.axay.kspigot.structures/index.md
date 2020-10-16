---
title: net.axay.kspigot.structures -
---
//[KSpigot](../index.md)/[net.axay.kspigot.structures](index.md)



# Package net.axay.kspigot.structures  


## Types  
  
|  Name|  Summary| 
|---|---|
| [Circle](-circle/index.md)| [jvm]  <br>Content  <br>abstract class [Circle](-circle/index.md)(**radius**: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html))  <br><br><br>
| [EntityCircle](-entity-circle/index.md)| [jvm]  <br>Content  <br>class [EntityCircle](-entity-circle/index.md)(**radius**: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), **entityType**: EntityType) : [Circle](-circle/index.md)  <br><br><br>
| [MaterialCircle](-material-circle/index.md)| [jvm]  <br>Content  <br>class [MaterialCircle](-material-circle/index.md)(**radius**: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), **material**: Material) : [Circle](-circle/index.md)  <br><br><br>
| [ParticleCircle](-particle-circle/index.md)| [jvm]  <br>Content  <br>class [ParticleCircle](-particle-circle/index.md)(**radius**: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), **particle**: [KSpigotParticle](../net.axay.kspigot.particles/-k-spigot-particle/index.md)) : [Circle](-circle/index.md)  <br><br><br>
| [SingleStructureData](-single-structure-data/index.md)| [jvm]  <br>Content  <br>class [SingleStructureData](-single-structure-data/index.md)(**location**: [SimpleLocation3D](../net.axay.kspigot.extensions.geometry/-simple-location3-d/index.md), **structureData**: [StructureData](-structure-data/index.md))  <br><br><br>
| [Structure](-structure/index.md)| [jvm]  <br>Content  <br>data class [Structure](-structure/index.md)(**structureData**: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)<[SingleStructureData](-single-structure-data/index.md)>)  <br><br><br>
| [StructureData](-structure-data/index.md)| [jvm]  <br>Content  <br>interface [StructureData](-structure-data/index.md)  <br><br><br>
| [StructureDataBlock](-structure-data-block/index.md)| [jvm]  <br>Content  <br>data class [StructureDataBlock](-structure-data-block/index.md)(**material**: Material, **blockData**: BlockData) : [StructureData](-structure-data/index.md)  <br><br><br>
| [StructureDataEntity](-structure-data-entity/index.md)| [jvm]  <br>Content  <br>data class [StructureDataEntity](-structure-data-entity/index.md)(**entityType**: EntityType, **nbtData**: [NBTData](../net.axay.kspigot.data/-n-b-t-data/index.md)) : [StructureData](-structure-data/index.md)  <br><br><br>
| [StructureDataMaterial](-structure-data-material/index.md)| [jvm]  <br>Content  <br>data class [StructureDataMaterial](-structure-data-material/index.md)(**material**: Material) : [StructureData](-structure-data/index.md)  <br><br><br>
| [StructureDataParticle](-structure-data-particle/index.md)| [jvm]  <br>Content  <br>data class [StructureDataParticle](-structure-data-particle/index.md)(**particle**: [KSpigotParticle](../net.axay.kspigot.particles/-k-spigot-particle/index.md)) : [StructureData](-structure-data/index.md)  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [buildAt](build-at.md)| [jvm]  <br>Content  <br>fun [Structure](-structure/index.md).[buildAt](build-at.md)(loc: Location)  <br><br><br>
| [loadStructure](load-structure.md)| [jvm]  <br>Content  <br>fun [LocationArea](../net.axay.kspigot.extensions.geometry/-location-area/index.md).[loadStructure](load-structure.md)(includeBlocks: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), includeEntities: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [Structure](-structure/index.md)  <br><br><br>
| [rotate](rotate.md)| [jvm]  <br>Content  <br>inline fun [Structure](-structure/index.md).[rotate](rotate.md)(angle: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), vectorRotation: (Vector, [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)) -> Vector): [Structure](-structure/index.md)  <br><br><br>
| [rotateAroundX](rotate-around-x.md)| [jvm]  <br>Content  <br>fun [Structure](-structure/index.md).[rotateAroundX](rotate-around-x.md)(angle: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): [Structure](-structure/index.md)  <br><br><br>
| [rotateAroundY](rotate-around-y.md)| [jvm]  <br>Content  <br>fun [Structure](-structure/index.md).[rotateAroundY](rotate-around-y.md)(angle: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): [Structure](-structure/index.md)  <br><br><br>
| [rotateAroundZ](rotate-around-z.md)| [jvm]  <br>Content  <br>fun [Structure](-structure/index.md).[rotateAroundZ](rotate-around-z.md)(angle: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): [Structure](-structure/index.md)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [entities](index.md#net.axay.kspigot.structures//entities/net.axay.kspigot.extensions.geometry.LocationArea#/PointingToDeclaration/)|  [jvm] <br><br><br><br>val [LocationArea](../net.axay.kspigot.extensions.geometry/-location-area/index.md).[entities](index.md#net.axay.kspigot.structures//entities/net.axay.kspigot.extensions.geometry.LocationArea#/PointingToDeclaration/): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)<Entity>   <br>
| [fillBlocks](index.md#net.axay.kspigot.structures//fillBlocks/net.axay.kspigot.extensions.geometry.LocationArea#/PointingToDeclaration/)|  [jvm] <br><br><br><br>val [LocationArea](../net.axay.kspigot.extensions.geometry/-location-area/index.md).[fillBlocks](index.md#net.axay.kspigot.structures//fillBlocks/net.axay.kspigot.extensions.geometry.LocationArea#/PointingToDeclaration/): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)<Block>   <br>

