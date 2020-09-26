package net.axay.kspigot.kotlinextensions

inline fun <T, R> Lazy<T>.ifInitialized(block: (T) -> R) = if (isInitialized()) block(value) else null
val <T> Lazy<T>.valueIfInitialized get() = ifInitialized { value }

fun Lazy<AutoCloseable>.closeIfInitialized() = ifInitialized { value.close() }