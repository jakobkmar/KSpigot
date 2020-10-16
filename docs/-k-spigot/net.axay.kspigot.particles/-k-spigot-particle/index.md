---
title: KSpigotParticle -
---
//[KSpigot](../../index.md)/[net.axay.kspigot.particles](../index.md)/[KSpigotParticle](index.md)



# KSpigotParticle  
 [jvm] data class [KSpigotParticle](index.md)(**particle**: Particle, **amount**: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), **offset**: Vector?, **extra**: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), **data**: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?, **force**: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))   


## Parameters  
  
jvm  
  
|  Name|  Summary| 
|---|---|
| amount| <br><br>The amount of particles.<br><br>
| data| <br><br>Particle specific data, often specifying BlockData or an ItemStack.<br><br>
| extra| <br><br>Particle specific data, often specifying an aspect like speed.<br><br>
| force| <br><br>Determines whether the client should be encouraged to display the particles.<br><br>
| offset| <br><br>The offset at which particles should appear.<br><br>
| particle| <br><br>The type of Particle.<br><br>
  


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [KSpigotParticle](-k-spigot-particle.md)|  [jvm] <br><br>The type of Particle.<br><br>fun [KSpigotParticle](-k-spigot-particle.md)(particle: Particle, amount: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), offset: Vector?, extra: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), data: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?, force: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [component1](component1.md)| [jvm]  <br>Content  <br>operator fun [component1](component1.md)(): Particle  <br><br><br>
| [component2](component2.md)| [jvm]  <br>Content  <br>operator fun [component2](component2.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [component3](component3.md)| [jvm]  <br>Content  <br>operator fun [component3](component3.md)(): Vector?  <br><br><br>
| [component4](component4.md)| [jvm]  <br>Content  <br>operator fun [component4](component4.md)(): [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)  <br><br><br>
| [component5](component5.md)| [jvm]  <br>Content  <br>operator fun [component5](component5.md)(): [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?  <br><br><br>
| [component6](component6.md)| [jvm]  <br>Content  <br>operator fun [component6](component6.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [copy](copy.md)| [jvm]  <br>Content  <br>fun [copy](copy.md)(particle: Particle, amount: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), offset: Vector?, extra: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), data: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?, force: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [KSpigotParticle](index.md)  <br><br><br>
| [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)| [jvm]  <br>Content  <br>open operator override fun [equals](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [hashCode](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [spawnAt](spawn-at.md)| [jvm]  <br>Brief description  <br><br><br>Spawns the particle at the location. It will be visible for everyone near it.<br><br>  <br>Content  <br>fun [spawnAt](spawn-at.md)(loc: Location)  <br><br><br>
| [spawnFor](spawn-for.md)| [jvm]  <br>Brief description  <br><br><br>Spawns the particle at the location of the player. It will be only visible for the player.<br><br>  <br>Content  <br>fun [spawnFor](spawn-for.md)(player: Player)  <br><br><br>
| [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)| [jvm]  <br>Content  <br>open override fun [toString](../../net.axay.kspigot.utils/-registerable-command/index.md#kotlin/Any/toString/#/PointingToDeclaration/)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [amount](index.md#net.axay.kspigot.particles/KSpigotParticle/amount/#/PointingToDeclaration/)|  [jvm] <br><br>The amount of particles.<br><br>var [amount](index.md#net.axay.kspigot.particles/KSpigotParticle/amount/#/PointingToDeclaration/): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)   <br>
| [data](index.md#net.axay.kspigot.particles/KSpigotParticle/data/#/PointingToDeclaration/)|  [jvm] <br><br>Particle specific data, often specifying BlockData or an ItemStack.<br><br>var [data](index.md#net.axay.kspigot.particles/KSpigotParticle/data/#/PointingToDeclaration/): [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?   <br>
| [extra](index.md#net.axay.kspigot.particles/KSpigotParticle/extra/#/PointingToDeclaration/)|  [jvm] <br><br>Particle specific data, often specifying an aspect like speed.<br><br>var [extra](index.md#net.axay.kspigot.particles/KSpigotParticle/extra/#/PointingToDeclaration/): [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html)   <br>
| [force](index.md#net.axay.kspigot.particles/KSpigotParticle/force/#/PointingToDeclaration/)|  [jvm] <br><br>Determines whether the client should be encouraged to display the particles.<br><br>var [force](index.md#net.axay.kspigot.particles/KSpigotParticle/force/#/PointingToDeclaration/): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)   <br>
| [offset](index.md#net.axay.kspigot.particles/KSpigotParticle/offset/#/PointingToDeclaration/)|  [jvm] <br><br>The offset at which particles should appear.<br><br>var [offset](index.md#net.axay.kspigot.particles/KSpigotParticle/offset/#/PointingToDeclaration/): Vector?   <br>
| [particle](index.md#net.axay.kspigot.particles/KSpigotParticle/particle/#/PointingToDeclaration/)|  [jvm] <br><br>The type of Particle.<br><br>val [particle](index.md#net.axay.kspigot.particles/KSpigotParticle/particle/#/PointingToDeclaration/): Particle   <br>

