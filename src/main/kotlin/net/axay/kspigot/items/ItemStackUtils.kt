@file:Suppress("unused")

package net.axay.kspigot.items

import net.axay.kspigot.chat.KColors
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration

/**
 * Converts this string into a list of components, which
 * can be used for minecraft lorelists.
 */
fun String.toLoreList(lineColor: TextColor = KColors.WHITE, vararg lineDecorations: TextDecoration = arrayOf(), lineLength: Int = 40): List<Component> {
    val loreList = ArrayList<Component>()
    val lineBuilder = StringBuilder()
    fun submitLine() {
        loreList += Component.text(lineBuilder.toString()).color(lineColor).decorations(lineDecorations.toMutableSet(), true)
        lineBuilder.clear()
    }

    fun addWord(word: String) {
        if (lineBuilder.lengthWithoutMinecraftColour + word.lengthWithoutMinecraftColour > lineLength)
            submitLine()

        if (lineBuilder.isNotEmpty())
            lineBuilder.append(" ")

        lineBuilder.append(word)
    }

    split(" ").forEach { addWord(it) }

    if (lineBuilder.isNotEmpty())
        submitLine()

    return loreList
}

/**
 * Returns the length of this sequence, ignoring
 * all minecraft colour codes.
 */
val CharSequence.lengthWithoutMinecraftColour: Int
    get() {
        var count = 0
        var isPreviousColourCode = false

        this.forEachIndexed { index, char ->
            if (isPreviousColourCode) {
                isPreviousColourCode = false
                return@forEachIndexed
            }

            if (char == '§') {
                if (lastIndex >= index + 1) {
                    val nextChar = this[index + 1]
                    if (nextChar.isLetter() || nextChar.isDigit())
                        isPreviousColourCode = true
                    else
                        count++
                }
            } else count++
        }

        return count
    }
