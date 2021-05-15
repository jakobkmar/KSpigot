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

    fun counterMessage(
        beforeTime: String? = null,
        afterTime: String? = null,
        hours: String = "h",
        minutes: String = "m",
        seconds: String = "s",
    ) {
        counterMessage = buildCounterMessageCallback(beforeTime, afterTime, hours, minutes, seconds)
    }

    fun build() = GamePhase(length, start, end, counterMessage)
}

fun buildGame(builder: GamePhaseSystemBuilder.() -> Unit) = GamePhaseSystemBuilder().apply(builder).build()
