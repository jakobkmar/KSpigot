package net.axay.kspigot.languageextensions.kotlinextensions

internal fun <T> T.applyIfNotNull(block: (T.() -> Unit)?): T {
    if (block != null)
        apply(block)
    return this
}
