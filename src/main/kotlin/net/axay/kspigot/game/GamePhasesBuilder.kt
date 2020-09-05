@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.game

class GamePhaseSystemBuilder {

    private val gamePhases = mutableListOf<GamePhase>()
    fun build() = GamePhaseSystem(*gamePhases.toTypedArray())

    fun phase(length: Long, builder: GamePhaseBuilder.() -> Unit) {
        gamePhases += GamePhaseBuilder(length).apply(builder).build()
    }

}

class GamePhaseBuilder(val length: Long) {

    private var start: (() -> Unit)? = null
    private var end: (() -> Unit)? = null
    private var counterMessage: ((secondsLeft: Long) -> String)? = null

    fun start(callback: () -> Unit) {
        start = callback
    }

    fun end(callback: () -> Unit) {
        end = callback
    }

    fun counterMessage(callback: (secondsLeft: Long) -> String) {
        counterMessage = callback
    }

    fun counterMessage(prefix: String, beginning: String, seconds: String, second: String) {
        counterMessage = net.axay.kspigot.game.counterMessage(prefix, beginning, seconds, second)
    }

    fun build() = GamePhase(length, start, end, counterMessage)

}

fun buildGame(builder: GamePhaseSystemBuilder.() -> Unit) = GamePhaseSystemBuilder().apply(builder).build()