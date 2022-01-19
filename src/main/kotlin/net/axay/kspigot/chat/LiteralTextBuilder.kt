@file:Suppress("MemberVisibilityCanBePrivate", "Unused")

package net.axay.kspigot.chat

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.empty
import net.kyori.adventure.text.Component.newline
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack

/**
 * Opens a [LiteralTextBuilder].
 *
 * @param baseText the text you want to begin with, it is okay to let this empty
 * @param builder the builder which can be used to set the style and add child text components
 */
inline fun literalText(
    baseText: String = "",
    builder: LiteralTextBuilder.() -> Unit = { }
) = LiteralTextBuilder(baseText).apply(builder).build()

class LiteralTextBuilder(val internalText: Component) {
    constructor(text: String) : this(Component.text(text))

    var bold: Boolean? = null
    var italic: Boolean? = null
    var underline: Boolean? = null
    var strikethrough: Boolean? = null
    var obfuscate: Boolean? = null

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
    var color: TextColor? = null

    var clickEvent: ClickEvent? = null
    var hoverEvent: HoverEvent<*>? = null

    var siblingText = empty()

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
        siblingText = siblingText.append(LiteralTextBuilder(text).apply(builder).build())
    }

    /**
     * Append a component to the parent.
     *
     * @param component the component
     * @param builder the builder which can be used to set the style and add child text components
     */
    inline fun component(
        component: Component,
        builder: LiteralTextBuilder.() -> Unit = { }
    ) {
        siblingText = siblingText.append(LiteralTextBuilder(component).apply(builder).build())
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
        siblingText = siblingText.append(LiteralTextBuilder(LegacyComponentSerializer.legacy('§').deserialize(text)).apply(builder).build())
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
        hoverEvent = HoverEvent.hoverEvent(
            HoverEvent.Action.SHOW_TEXT,
            LiteralTextBuilder(text).apply(builder).build()
        )
    }

    /**
     * Sets the item which should be displayed when hovering
     * over the text in the chat.
     *
     * @param itemStack the ItemStack
     */
    fun hoverItem(
        itemStack: ItemStack
    ) {
        hoverEvent = itemStack.asHoverEvent()
    }

    /**
     * Sets the entity which should be displayed when hovering
     * over the text in the chat.
     *
     * @param entity the Entity
     */
    fun hoverEntity(
        entity: Entity
    ) {
        hoverEvent = entity.asHoverEvent()
    }

    /**
     * Sets the command which should be executed by the Player if he clicks
     * on the text.
     *
     * @param command the command which should be executed, the `/ should be added here
     * @param onlySuggest if true, the command won't be executed immediately,
     * instead it will be suggested in the command prompt
     */
    fun onClickCommand(command: String, onlySuggest: Boolean = false) {
        clickEvent = if (onlySuggest) ClickEvent.suggestCommand(command) else ClickEvent.runCommand(command)
    }

    /**
     * Sets the String which should be copied to the clipboard if the
     * Player clicks on this text.
     */
    fun onClickCopy(copyText: String) {
        clickEvent = ClickEvent.copyToClipboard(copyText)
    }

    /**
     * Sets the Url which should be opened if the Player clicks on this text
     */
    fun onClickOpenURL(url: String) {
        clickEvent = ClickEvent.openUrl(url)
    }

    /**
     * Adds a line break.
     */
    fun newLine() {
        siblingText = siblingText.append(newline())
    }

    /**
     * Adds an empty line.
     */
    fun emptyLine() {
        newLine()
        newLine()
    }

    fun build(): Component {
        return if (siblingText.children().isNotEmpty()) {
            internalText.stylize().append(siblingText.stylize())
        } else {
            internalText.stylize()
        }
    }

    private fun Component.stylize(): Component {
        var style = style()
        val decorations = style.decorations().toMutableMap()
        decorations[TextDecoration.BOLD] = TextDecoration.State.byBoolean(this@LiteralTextBuilder.bold)
        decorations[TextDecoration.ITALIC] = TextDecoration.State.byBoolean(this@LiteralTextBuilder.italic)
        decorations[TextDecoration.UNDERLINED] = TextDecoration.State.byBoolean(this@LiteralTextBuilder.underline)
        decorations[TextDecoration.STRIKETHROUGH] = TextDecoration.State.byBoolean(this@LiteralTextBuilder.strikethrough)
        decorations[TextDecoration.OBFUSCATED] = TextDecoration.State.byBoolean(this@LiteralTextBuilder.obfuscate)
        style = style.decorations(decorations)
        this@LiteralTextBuilder.color?.let { style = style.color(it) }

        this@LiteralTextBuilder.clickEvent?.let { style = style.clickEvent(it) }
        this@LiteralTextBuilder.hoverEvent?.let { style = style.hoverEvent(it) }

        return this.style(style)
    }
}
