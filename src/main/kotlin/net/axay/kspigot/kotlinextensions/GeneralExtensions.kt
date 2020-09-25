package net.axay.kspigot.kotlinextensions

inline fun <T, R> Lazy<T>.ifInitialized(block: (T) -> R) = if (isInitialized()) block(value) else null

fun Lazy<AutoCloseable>.closeIfInitialized() = ifInitialized { value.close() }