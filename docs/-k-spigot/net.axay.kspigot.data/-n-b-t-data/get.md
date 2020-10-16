---
title: get -
---
//[KSpigot](../../index.md)/[net.axay.kspigot.data](../index.md)/[NBTData](index.md)/[get](get.md)



# get  
[jvm]  
Brief description  


This method gets the value at the given key. The returned dataType must be specified. The returned value is null, if it was not possible to find any value at the specified location, or if the type is not the one which was specified.

  
Content  
operator fun <[T](get.md)> [get](get.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), dataType: [NBTDataType](../-n-b-t-data-type/index.md)<[T](get.md)>): [T](get.md)?  



