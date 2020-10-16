---
title: net.axay.kspigot.particles -
---
//[KSpigot](../index.md)/[net.axay.kspigot.particles](index.md)



# Package net.axay.kspigot.particles  


## Types  
  
|  Name|  Summary| 
|---|---|
| [KSpigotParticle](-k-spigot-particle/index.md)| [jvm]  <br>Content  <br>data class [KSpigotParticle](-k-spigot-particle/index.md)(**particle**: Particle, **amount**: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), **offset**: Vector?, **extra**: [Number](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/index.html), **data**: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?, **force**: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [particle](particle.md)| [jvm]  <br>Brief description  <br><br><br>Accesses the particle builder.<br><br>  <br>Content  <br>fun [particle](particle.md)(particle: Particle, builder: [KSpigotParticle](-k-spigot-particle/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [KSpigotParticle](-k-spigot-particle/index.md)  <br><br><br>[jvm]  <br>Brief description  <br><br><br>Accesses the particle builder and then immediately spawns the particle at the given location.<br><br>  <br>Content  <br>fun Location.[particle](particle.md)(particle: Particle, builder: [KSpigotParticle](-k-spigot-particle/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?)  <br><br><br>[jvm]  <br>Brief description  <br><br><br>Accesses the particle builder and then immediately spawns the particle for the player.<br><br>  <br>Content  <br>fun Player.[particle](particle.md)(particle: Particle, builder: [KSpigotParticle](-k-spigot-particle/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?)  <br><br><br>

