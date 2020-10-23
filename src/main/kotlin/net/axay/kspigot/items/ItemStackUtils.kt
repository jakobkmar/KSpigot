package net.axay.kspigot.items

import net.axay.kspigot.chat.KColors
import net.md_5.bungee.api.ChatColor

fun String.toLoreList(lineLength: Int = 40, lineColor: ChatColor = KColors.RESET): List<String> {

    val loreList = ArrayList<String>()

    val lineBuilder = StringBuilder()

    fun submitLine() {
        loreList += "$lineColor$lineBuilder"
        lineBuilder.clear()
    }

    fun addWord(word: String) {

        if (lineBuilder.length + word.length > lineLength)
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