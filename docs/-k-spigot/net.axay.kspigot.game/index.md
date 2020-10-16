---
title: net.axay.kspigot.game -
---
//[KSpigot](../index.md)/[net.axay.kspigot.game](index.md)



# Package net.axay.kspigot.game  


## Types  
  
|  Name|  Summary| 
|---|---|
| [GamePhase](-game-phase/index.md)| [jvm]  <br>Content  <br>class [GamePhase](-game-phase/index.md)(**length**: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), **start**: () -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?, **end**: () -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)?, **counterMessage**: ([Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)) -> [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?)  <br><br><br>
| [GamePhaseBuilder](-game-phase-builder/index.md)| [jvm]  <br>Content  <br>class [GamePhaseBuilder](-game-phase-builder/index.md)(**length**: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html))  <br><br><br>
| [GamePhaseSystem](-game-phase-system/index.md)| [jvm]  <br>Content  <br>class [GamePhaseSystem](-game-phase-system/index.md)(**gamePhases**: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)<out [GamePhase](-game-phase/index.md)>)  <br><br><br>
| [GamePhaseSystemBuilder](-game-phase-system-builder/index.md)| [jvm]  <br>Content  <br>class [GamePhaseSystemBuilder](-game-phase-system-builder/index.md)  <br><br><br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [buildCounterMessageCallback](build-counter-message-callback.md)| [jvm]  <br>Content  <br>fun [buildCounterMessageCallback](build-counter-message-callback.md)(beforeTime: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, afterTime: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, hours: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), minutes: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), seconds: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): ([Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)) -> [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
| [buildGame](build-game.md)| [jvm]  <br>Content  <br>fun [buildGame](build-game.md)(builder: [GamePhaseSystemBuilder](-game-phase-system-builder/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [GamePhaseSystem](-game-phase-system/index.md)  <br><br><br>

