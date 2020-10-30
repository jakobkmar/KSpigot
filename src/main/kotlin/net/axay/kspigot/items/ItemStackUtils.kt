package net.axay.kspigot.items

import net.axay.kspigot.chat.KColors
import net.md_5.bungee.api.ChatColor

/**
 * Converts this string into a list of strings, which
 * can be used for minecraft lorelists.
 */
@Suppress("RemoveRedundantSpreadOperator")
fun String.toLoreList(lineLength: Int = 40, lineColor: ChatColor = KColors.RESET)
        = toLoreList(lineLength, *arrayOf(lineColor))

/**
 * Converts this string into a list of strings, which
 * can be used for minecraft lorelists.
 */
fun String.toLoreList(lineLength: Int = 40, vararg lineColors: ChatColor = arrayOf(KColors.RESET)): List<String> {

    val lineColor = lineColors.joinToString()

    val loreList = ArrayList<String>()

    val lineBuilder = StringBuilder()

    fun submitLine() {
        loreList += "$lineColor$lineBuilder"
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
val CharSequence.lengthWithoutMinecraftColour: Int get() {

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