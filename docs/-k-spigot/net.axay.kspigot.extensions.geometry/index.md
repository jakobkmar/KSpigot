---
title: net.axay.kspigot.extensions.geometry -
---
//[KSpigot](../index.md)/[net.axay.kspigot.extensions.geometry](index.md)



# Package net.axay.kspigot.extensions.geometry  


## Types  
  
|  Name|  Summary| 
|---|---|
| [LocationArea](-location-area/index.md)| [jvm]  <br>Content  <br>class [LocationArea](-location-area/index.md)(**loc1**: Location, **loc2**: Location)  <br><br><br>
| [SimpleChunkLocation](-simple-chunk-location/index.md)| [jvm]  <br>Content  <br>data class [SimpleChunkLocation](-simple-chunk-location/index.md)(**x**: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), **z**: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))  <br><br><br>
| [SimpleLocation2D](-simple-location2-d/index.md)| [jvm]  <br>Content  <br>data class [SimpleLocation2D](-simple-location2-d/index.md)(**x**: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), **y**: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html))  <br><br><br>
| [SimpleLocation3D](-simple-location3-d/index.md)| [jvm]  <br>Content  <br>data class [SimpleLocation3D](-simple-location3-d/index.md)(**x**: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), **y**: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), **z**: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html))  <br><br><br>
| [SimpleLocationPair](-simple-location-pair/index.md)| [jvm]  <br>Content  <br>class [SimpleLocationPair](-simple-location-pair/index.md)(**loc1**: Location, **loc2**: Location)  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [add](add.md)| [jvm]  <br>Content  <br>fun Location.[add](add.md)(x: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), y: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), z: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): Location  <br><br><br>
| [increase](increase.md)| [jvm]  <br>Content  <br>infix fun Location.[increase](increase.md)(distance: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): Location  <br>infix fun Location.[increase](increase.md)(loc: [SimpleLocation3D](-simple-location3-d/index.md)): Location  <br>infix fun Location.[increase](increase.md)(loc: Location): Location  <br>infix fun Location.[increase](increase.md)(vec: Vector): Location  <br>infix fun Vector.[increase](increase.md)(vec: Vector): Vector  <br><br><br>
| [increaseX](increase-x.md)| [jvm]  <br>Content  <br>infix fun Location.[increaseX](increase-x.md)(distance: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): Location  <br><br><br>
| [increaseXY](increase-x-y.md)| [jvm]  <br>Content  <br>infix fun Location.[increaseXY](increase-x-y.md)(distance: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): Location  <br><br><br>
| [increaseXZ](increase-x-z.md)| [jvm]  <br>Content  <br>infix fun Location.[increaseXZ](increase-x-z.md)(distance: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): Location  <br><br><br>
| [increaseY](increase-y.md)| [jvm]  <br>Content  <br>infix fun Location.[increaseY](increase-y.md)(distance: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): Location  <br><br><br>
| [increaseYZ](increase-y-z.md)| [jvm]  <br>Content  <br>infix fun Location.[increaseYZ](increase-y-z.md)(distance: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): Location  <br><br><br>
| [increaseZ](increase-z.md)| [jvm]  <br>Content  <br>infix fun Location.[increaseZ](increase-z.md)(distance: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): Location  <br><br><br>
| [minus](minus.md)| [jvm]  <br>Content  <br>operator fun Location.[minus](minus.md)(loc: [SimpleLocation3D](-simple-location3-d/index.md)): Location  <br>operator fun Location.[minus](minus.md)(loc: Location): Location  <br>operator fun Location.[minus](minus.md)(vec: Vector): Location  <br>operator fun Vector.[minus](minus.md)(vec: Vector): Vector  <br><br><br>
| [minusAssign](minus-assign.md)| [jvm]  <br>Content  <br>operator fun Location.[minusAssign](minus-assign.md)(loc: [SimpleLocation3D](-simple-location3-d/index.md))  <br>operator fun Location.[minusAssign](minus-assign.md)(loc: Location)  <br>operator fun Location.[minusAssign](minus-assign.md)(vec: Vector)  <br>operator fun Vector.[minusAssign](minus-assign.md)(vec: Vector)  <br><br><br>
| [multiply](multiply.md)| [jvm]  <br>Content  <br>infix fun Vector.[multiply](multiply.md)(num: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): Vector  <br>infix fun Vector.[multiply](multiply.md)(vec: Vector): Vector  <br><br><br>
| [plus](plus.md)| [jvm]  <br>Content  <br>operator fun Location.[plus](plus.md)(loc: [SimpleLocation3D](-simple-location3-d/index.md)): Location  <br>operator fun Location.[plus](plus.md)(loc: Location): Location  <br>operator fun Location.[plus](plus.md)(vec: Vector): Location  <br>operator fun Vector.[plus](plus.md)(vec: Vector): Vector  <br><br><br>
| [plusAssign](plus-assign.md)| [jvm]  <br>Content  <br>operator fun Location.[plusAssign](plus-assign.md)(loc: [SimpleLocation3D](-simple-location3-d/index.md))  <br>operator fun Location.[plusAssign](plus-assign.md)(loc: Location)  <br>operator fun Location.[plusAssign](plus-assign.md)(vec: Vector)  <br>operator fun Vector.[plusAssign](plus-assign.md)(vec: Vector)  <br><br><br>
| [reduce](reduce.md)| [jvm]  <br>Content  <br>infix fun Location.[reduce](reduce.md)(distance: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): Location  <br>infix fun Location.[reduce](reduce.md)(loc: [SimpleLocation3D](-simple-location3-d/index.md)): Location  <br>infix fun Location.[reduce](reduce.md)(loc: Location): Location  <br>infix fun Location.[reduce](reduce.md)(vec: Vector): Location  <br>infix fun Vector.[reduce](reduce.md)(vec: Vector): Vector  <br><br><br>
| [reduceX](reduce-x.md)| [jvm]  <br>Content  <br>infix fun Location.[reduceX](reduce-x.md)(distance: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): Location  <br><br><br>
| [reduceXY](reduce-x-y.md)| [jvm]  <br>Content  <br>infix fun Location.[reduceXY](reduce-x-y.md)(distance: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): Location  <br><br><br>
| [reduceXZ](reduce-x-z.md)| [jvm]  <br>Content  <br>infix fun Location.[reduceXZ](reduce-x-z.md)(distance: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): Location  <br><br><br>
| [reduceY](reduce-y.md)| [jvm]  <br>Content  <br>infix fun Location.[reduceY](reduce-y.md)(distance: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): Location  <br><br><br>
| [reduceYZ](reduce-y-z.md)| [jvm]  <br>Content  <br>infix fun Location.[reduceYZ](reduce-y-z.md)(distance: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): Location  <br><br><br>
| [reduceZ](reduce-z.md)| [jvm]  <br>Content  <br>infix fun Location.[reduceZ](reduce-z.md)(distance: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): Location  <br><br><br>
| [relationTo](relation-to.md)| [jvm]  <br>Content  <br>infix fun Location.[relationTo](relation-to.md)(loc: Location): [SimpleLocation3D](-simple-location3-d/index.md)  <br><br><br>
| [substract](substract.md)| [jvm]  <br>Content  <br>fun Location.[substract](substract.md)(x: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), y: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), z: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): Location  <br><br><br>
| [times](times.md)| [jvm]  <br>Content  <br>operator fun Vector.[times](times.md)(num: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): Vector  <br>operator fun Vector.[times](times.md)(vec: Vector): Vector  <br><br><br>
| [timesAssign](times-assign.md)| [jvm]  <br>Content  <br>operator fun Vector.[timesAssign](times-assign.md)(num: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html))  <br>operator fun Vector.[timesAssign](times-assign.md)(vec: Vector)  <br><br><br>
| [toSimple](to-simple.md)| [jvm]  <br>Content  <br>fun Chunk.[toSimple](to-simple.md)(): [SimpleChunkLocation](-simple-chunk-location/index.md)  <br>fun Location.[toSimple](to-simple.md)(): [SimpleLocation3D](-simple-location3-d/index.md)  <br><br><br>
| [toSimpleLoc](to-simple-loc.md)| [jvm]  <br>Content  <br>fun Vector.[toSimpleLoc](to-simple-loc.md)(): [SimpleLocation3D](-simple-location3-d/index.md)  <br><br><br>
| [toVector](to-vector.md)| [jvm]  <br>Content  <br>fun [SimpleLocation3D](-simple-location3-d/index.md).[toVector](to-vector.md)(): Vector  <br><br><br>
| [vec](vec.md)| [jvm]  <br>Content  <br>fun [vec](vec.md)(x: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), y: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), z: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): Vector  <br><br><br>
| [vecX](vec-x.md)| [jvm]  <br>Content  <br>fun [vecX](vec-x.md)(x: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): Vector  <br><br><br>
| [vecXY](vec-x-y.md)| [jvm]  <br>Content  <br>fun [vecXY](vec-x-y.md)(x: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), y: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): Vector  <br><br><br>
| [vecXZ](vec-x-z.md)| [jvm]  <br>Content  <br>fun [vecXZ](vec-x-z.md)(x: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), z: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): Vector  <br><br><br>
| [vecY](vec-y.md)| [jvm]  <br>Content  <br>fun [vecY](vec-y.md)(y: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): Vector  <br><br><br>
| [vecYZ](vec-y-z.md)| [jvm]  <br>Content  <br>fun [vecYZ](vec-y-z.md)(y: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), z: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): Vector  <br><br><br>
| [vecZ](vec-z.md)| [jvm]  <br>Content  <br>fun [vecZ](vec-z.md)(z: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)): Vector  <br><br><br>
| [withWorld](with-world.md)| [jvm]  <br>Content  <br>fun [SimpleChunkLocation](-simple-chunk-location/index.md).[withWorld](with-world.md)(world: World): Chunk  <br>fun [SimpleLocation3D](-simple-location3-d/index.md).[withWorld](with-world.md)(world: World): Location  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [blockLoc](index.md#net.axay.kspigot.extensions.geometry//blockLoc/org.bukkit.Location#/PointingToDeclaration/)|  [jvm] val Location.[blockLoc](index.md#net.axay.kspigot.extensions.geometry//blockLoc/org.bukkit.Location#/PointingToDeclaration/): Location   <br>
| [isFinite](index.md#net.axay.kspigot.extensions.geometry//isFinite/org.bukkit.util.Vector#/PointingToDeclaration/)|  [jvm] val Vector.[isFinite](index.md#net.axay.kspigot.extensions.geometry//isFinite/org.bukkit.util.Vector#/PointingToDeclaration/): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)   <br>
| [worldOrException](index.md#net.axay.kspigot.extensions.geometry//worldOrException/org.bukkit.Location#/PointingToDeclaration/)|  [jvm] val Location.[worldOrException](index.md#net.axay.kspigot.extensions.geometry//worldOrException/org.bukkit.Location#/PointingToDeclaration/): World   <br>

