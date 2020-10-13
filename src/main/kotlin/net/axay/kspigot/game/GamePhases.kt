@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.game

import net.axay.kspigot.extensions.broadcast
import net.axay.kspigot.runnables.task

class GamePhaseSystem(vararg gamePhases: GamePhase) {
    val gamePhases = gamePhases.toMutableList()
    fun begin() = gamePhases.removeAt(0).startIt(gamePhases)
}

fun buildCounterMessageCallback(
    beforeTime: String? = null,
    afterTime: String? = null,
    hours: String = "h",
    minutes: String = "m",
    seconds: String = "s"
): (Long) -> String = { curSeconds ->

    StringBuilder().apply {
        append(beforeTime)

        val hourTime = (curSeconds / 3600)
        val minuteTime = ((curSeconds % 3600) / 60)
        val secondsTime = (curSeconds % 60)

        if (hourTime != 0L)
            append("${hourTime.toString().run { if (length < 2) "0$this" else this }}:")

        if ((hourTime != 0L && secondsTime != 0L) || (minuteTime != 0L && secondsTime != 0L))
            append("${minuteTime.toString().run { if (length < 2) "0$this" else this }}:")
        else if (minuteTime != 0L)
            append(minuteTime.toString().run { if (length < 2) "0$this" else this })

        if (secondsTime != 0L)
            append(secondsTime.toString().run { if (length < 2) "0$this" else this })

        append(" ").append(kotlin.run {
            return@run when {
                secondsTime != 0L -> seconds
                minuteTime != 0L -> minutes
                else -> hours
            }
        })

        append(afterTime)
    }.toString()

}

class GamePhase(
        val length: Long,
        val start: (() -> Unit)?,
        val end: (() -> Unit)?,
        val counterMessage: ((secondsLeft: Long) -> String)?
) {
    fun startIt(phaseQueue: MutableList<GamePhase>) {
        start?.invoke()
        task(
                period = 20,
                howOften = (length / 20) + 1,
                endCallback = {

                    end?.invoke()

                    if (phaseQueue.isNotEmpty())
                        phaseQueue.removeAt(0).startIt(phaseQueue)

                }
        ) {

            if (counterMessage != null) {
                val currentCounter = it.counterDownToZero
                if (currentCounter?.isCounterValue == true)
                    broadcast(counterMessage.invoke(currentCounter))
            }

        }
    }
}

private val Long.isCounterValue: Boolean get() = when (this) {
    1L, 2L, 3L, 4L, 5L, 10L, 15L, 20L, 30L -> true
    0L -> false
    else -> this % 60 == 0L
}