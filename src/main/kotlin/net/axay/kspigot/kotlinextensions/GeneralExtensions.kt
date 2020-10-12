package net.axay.kspigot.kotlinextensions

import java.io.File

internal inline fun <T, R> Lazy<T>.ifInitialized(block: (T) -> R) = if (isInitialized()) block(value) else null

internal val <T> Lazy<T>.valueIfInitialized get() = ifInitialized { value }

internal fun Lazy<AutoCloseable>.closeIfInitialized() = ifInitialized { value.close() }

internal class MinMaxPair<T : Comparable<T>>(a: T, b: T) {
    val min: T; val max: T
    init {
        if (a >= b) { min = b; max = a }
        else { min = a; max = b }
    }
}

internal fun <T> T.apply(block: (T.() -> Unit)?): T {
    if (block != null)
        apply(block)
    return this
}

internal fun File.createIfNotExists(): Boolean {
    return if (!exists()) {
        if (!parentFile.exists())
            parentFile.mkdirs()
        createNewFile()
    } else true
}
