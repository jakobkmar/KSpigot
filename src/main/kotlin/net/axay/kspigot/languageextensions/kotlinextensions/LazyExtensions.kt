package net.axay.kspigot.languageextensions.kotlinextensions

internal inline fun <T, R> Lazy<T>.ifInitialized(block: (T) -> R) = if (isInitialized()) block(value) else null
internal val <T> Lazy<T>.valueIfInitialized get() = ifInitialized { value }
internal fun Lazy<AutoCloseable>.closeIfInitialized() = ifInitialized { value.close() }