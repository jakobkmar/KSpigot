---
title: NBTData -
---
//[KSpigot](../../index.md)/[net.axay.kspigot.data](../index.md)/[NBTData](index.md)



# NBTData  
 [jvm] class [NBTData](index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [NBTData](-n-b-t-data.md)|  [jvm] fun [NBTData](-n-b-t-data.md)(nbtTagCompound: NBTTagCompound?)   <br>
| [NBTData](-n-b-t-data.md)|  [jvm] fun [NBTData](-n-b-t-data.md)()   <br>
| [NBTData](-n-b-t-data.md)|  [jvm] fun [NBTData](-n-b-t-data.md)(nbtString: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))   <br>


## Types  
  
|  Name|  Summary| 
|---|---|
| [Companion](-companion/index.md)| [jvm]  <br>Content  <br>object [Companion](-companion/index.md)  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)| [jvm]  <br>Content  <br>open operator override fun [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [get](get.md)| [jvm]  <br>Brief description  <br><br><br>This method gets the value at the given key. The returned dataType must be specified. The returned value is null, if it was not possible to find any value at the specified location, or if the type is not the one which was specified.<br><br>  <br>Content  <br>operator fun <[T](get.md)> [get](get.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), dataType: [NBTDataType](../-n-b-t-data-type/index.md)<[T](get.md)>): [T](get.md)?  <br><br><br>
| [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [minusAssign](minus-assign.md)| [jvm]  <br>Content  <br>operator fun [minusAssign](minus-assign.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [remove](remove.md)| [jvm]  <br>Brief description  <br><br><br>This method removes the given key from the NBTTagCompound. Its value will be lost.<br><br>  <br>Content  <br>fun [remove](remove.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))  <br><br><br>
| [serialize](serialize.md)| [jvm]  <br>Content  <br>fun [serialize](serialize.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [set](set.md)| [jvm]  <br>Brief description  <br><br><br>This method sets some value at the position of the given key. The dataType of the given value must be specified.<br><br>  <br>Content  <br>operator fun <[T](set.md)> [set](set.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), dataType: [NBTDataType](../-n-b-t-data-type/index.md)<[T](set.md)>, value: [T](set.md))  <br><br><br>
| [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [nbtTagCompound](index.md#net.axay.kspigot.data/NBTData/nbtTagCompound/#/PointingToDeclaration/)|  [jvm] val [nbtTagCompound](index.md#net.axay.kspigot.data/NBTData/nbtTagCompound/#/PointingToDeclaration/): NBTTagCompound   <br>

