@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package net.axay.kspigot.chat

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.chat.hover.content.Text

/**
 * Opens a [LiteralTextBuilder].
 *
 * @param baseText the text you want to begin with, it is okay to let this empty
 * @param builder the builder which can be used to set the style and add child text components
 */
inline fun literalText(
    baseText: String = "",
    builder: LiteralTextBuilder.() -> Unit = { }
) = LiteralTextBuilder(baseText).apply(builder).build() as TextComponent

class LiteralTextBuilder(val internalText: BaseComponent, ) {
    constructor(text: String) : this(TextComponent(text))

    var bold: Boolean? = null
    var italic: Boolean? = null
    var underline: Boolean? = null
    var strikethrough: Boolean? = null

    /**
     * The text color.
     * This can be set in the following way:
     *
     * e.g. Medium turquoise:
     *  - `color = col(0x4BD6CB)`
     *  - `color = col(4970187)`
     *  - `color = col("#4BD6CB")`
     *  - `color = KColors.MEDIUMTURQUOISE`
     */
    var color: ChatColor? = null

    var clickEvent: ClickEvent? = null
    var hoverEvent: HoverEvent? = null

    val siblingText = TextComponent("")

    /**
     * Append text to the parent.
     *
     * @param text the raw text (without formatting)
     * @param builder the builder which can be used to set the style and add child text components
     */
    inline fun text(
        text: String = "",
        builder: LiteralTextBuilder.() -> Unit = { }
    ) {
        siblingText.addExtra(LiteralTextBuilder(text).apply(builder).build())
    }

    /**
     * Append text to the parent.
     *
     * @param text the text instance
     * @param builder the builder which can be used to set the style and add child text components
     */
    inline fun text(
        text: BaseComponent,
        builder: LiteralTextBuilder.() -> Unit = { }
    ) {
        siblingText.addExtra(LiteralTextBuilder(text).apply(builder).build())
    }

    /**
     * Append the given legacy text to the parent. This
     * allows you to use legacy color codes (e.g. `§c` for red).
     * It is **not** recommended to use this.
     *
     * @param text the text instance
     * @param builder the builder which can be used to set the style and add child text components
     */
    inline fun legacyText(
        text: String,
        builder: LiteralTextBuilder.() -> Unit = { }
    ) {
        TextComponent.fromLegacyText(text).forEach {
            siblingText.addExtra(LiteralTextBuilder(it).apply(builder).build())
        }
    }

    /**
     * Sets the text which should be displayed when hovering
     * over the text in the chat.
     *
     * @param text the raw text (without formatting)
     * @param builder the builder which can be used to set the style and add child text components
     */
    inline fun hoverText(
        text: String = "",
        builder: LiteralTextBuilder.() -> Unit = { }
    ) {
        hoverEvent = HoverEvent(
            HoverEvent.Action.SHOW_TEXT,
            Text(arrayOf(LiteralTextBuilder(text).apply(builder).build()))
        )
    }

    /**
     * Adds a line break.
     */
    fun newLine() {
        siblingText.addExtra(TextComponent("\n"))
    }

    /**
     * Adds an empty line.
     */
    fun emptyLine() {
        newLine()
        newLine()
    }

    fun build() = internalText.apply {
        this@LiteralTextBuilder.bold?.let { isBold = it }
        this@LiteralTextBuilder.italic?.let { isItalic = it }
        this@LiteralTextBuilder.underline?.let { isUnderlined = it }
        this@LiteralTextBuilder.strikethrough?.let { isStrikethrough = it }
        this@LiteralTextBuilder.color?.let { color = it }
        this@LiteralTextBuilder.clickEvent?.let { clickEvent = it }
        this@LiteralTextBuilder.hoverEvent?.let { hoverEvent = it }

        if (siblingText.extra.isNotEmpty())
            addExtra(siblingText)
    }
}
