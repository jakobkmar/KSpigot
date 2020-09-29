package net.axay.kspigot.kotlinextensions

inline fun <T, R> Lazy<T>.ifInitialized(block: (T) -> R) = if (isInitialized()) block(value) else null
val <T> Lazy<T>.valueIfInitialized get() = ifInitialized { value }

fun Lazy<AutoCloseable>.closeIfInitialized() = ifInitialized { value.close() }

class MinMaxPair<T : Comparable<T>>(a: T, b: T) {

    val min: T
    val max: T

    init {
        if (a >= b) {
            min = b
            max = a
        } else {
            min = a
            max = b
        }
    }

}