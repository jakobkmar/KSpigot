@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.game

import net.axay.kspigot.extensions.broadcast
import net.axay.kspigot.main.KSpigot
import net.axay.kspigot.runnables.task

class GamePhaseSystem(vararg gamePhases: GamePhase) {
    val gamePhases = gamePhases.toMutableList()
    fun begin(kSpigot: KSpigot) = gamePhases.removeAt(0).startIt(kSpigot, gamePhases)
}

fun counterMessage(prefix: String? = null, beginning: String, seconds: String, second: String): (Long) -> String = {
    val realPrefix = prefix?.plus(" ") ?: ""
    if (it <= 1L) "$realPrefix$beginning $it $second" else "$prefix $beginning $it $seconds"
}

class GamePhase(
        val length: Long,
        val start: (() -> Unit)?,
        val end: (() -> Unit)?,
        val counterMessage: ((secondsLeft: Long) -> String)?
) {
    fun startIt(kSpigot: KSpigot, phaseQueue: MutableList<GamePhase>) {
        start?.invoke()
        kSpigot.task(
                period = 20,
                howOften = length / 20,
                endCallback = {

                    end?.invoke()

                    if (phaseQueue.isNotEmpty())
                        phaseQueue.removeAt(0).startIt(kSpigot, phaseQueue)

                }
        ) {

            if (counterMessage != null) {
                val currentCounter = it.counterDown
                if (currentCounter?.isCounterValue == true)
                    broadcast(counterMessage.invoke(currentCounter))
            }

        }
    }
}

private val Long.isCounterValue: Boolean get() = when (this) {
    1L, 2L, 3L, 4L, 5L, 10L, 15L, 20L, 30L -> true
    else -> this % 60 == 0L
}