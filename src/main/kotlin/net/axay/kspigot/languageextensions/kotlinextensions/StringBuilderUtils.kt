package net.axay.kspigot.languageextensions.kotlinextensions

internal fun stringBuilder(builder: StringBuilder.() -> Unit) = StringBuilder().apply(builder).toString()

inline fun multiLine(builder: MultiLineBuilder.() -> Unit) = MultiLineBuilder().apply(builder).build()

class MultiLineBuilder {

    private val stringBuilder = StringBuilder()

    operator fun String.unaryPlus() {
        stringBuilder.appendLine(this)
    }

    fun build() = stringBuilder.toString()

}