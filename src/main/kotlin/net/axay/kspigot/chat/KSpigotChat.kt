@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.chat

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.*
import net.md_5.bungee.api.chat.hover.content.Entity
import net.md_5.bungee.api.chat.hover.content.Item
import net.md_5.bungee.api.chat.hover.content.Text
import java.awt.Color

inline fun chatComponent(builder: KSpigotComponentBuilder.() -> Unit): Array<out BaseComponent> {
    return KSpigotComponentBuilder().apply(builder).create()
}

class KSpigotComponentBuilder {
    private val components = ArrayList<BaseComponent>()

    inline fun text(text: String, builder: TextComponent.() -> Unit = { }) {
        this += TextComponent(text).apply(builder)
    }

    inline fun keybind(keybind: String, builder: KeybindComponent.() -> Unit = { }) {
        this += KeybindComponent(keybind).apply(builder)
    }

    inline fun score(name: String, objective: String, value: String?, builder: ScoreComponent.() -> Unit = { }) {
        if (value != null)
            this += ScoreComponent(name, objective, value).apply(builder)
        else
            this += ScoreComponent(name, objective).apply(builder)
    }

    inline fun selector(selector: String, builder: SelectorComponent.() -> Unit = { }) {
        this += SelectorComponent(selector).apply(builder)
    }

    inline fun translatable(
        translatable: String,
        with: Array<BaseComponent>,
        builder: TranslatableComponent.() -> Unit = { },
    ) {
        this += TranslatableComponent(translatable, with).apply(builder)
    }

    fun legacyText(text: String, color: ChatColor = ChatColor.WHITE, builder: BaseComponent.() -> Unit = { }) {
        this += TextComponent.fromLegacyText(text, color).onEach { it.apply(builder) }
    }

    operator fun plusAssign(baseComponent: BaseComponent) {
        components += baseComponent
    }

    operator fun plusAssign(baseComponents: Array<out BaseComponent>) {
        components += baseComponents
    }

    fun create() = components.toTypedArray()
}

inline fun BaseComponent.hoverEventText(builder: KSpigotComponentBuilder.() -> Unit) {
    hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, Text(KSpigotComponentBuilder().apply(builder).create()))
}

fun BaseComponent.hoverEventItem(id: String, count: Int, tag: ItemTag) {
    hoverEvent = HoverEvent(HoverEvent.Action.SHOW_ITEM, Item(id, count, tag))
}

fun BaseComponent.hoverEventEntity(type: String, id: String, baseComponent: BaseComponent) {
    hoverEvent = HoverEvent(HoverEvent.Action.SHOW_ENTITY, Entity(type, id, baseComponent))
}

fun BaseComponent.clickEvent(action: ClickEvent.Action, value: String) {
    clickEvent = ClickEvent(action, value)
}

/**
 * Creates a new [ChatColor] instance from the provided
 * hex code.
 * Format example: `"#4BD6CB"`
 */
fun col(hex: String): ChatColor = ChatColor.of(hex)

/**
 * Creates a new [ChatColor] instance from the provided
 * hex code.
 * Format example: `0x4BD6CB`
 */
fun col(rgb: Int): ChatColor = ChatColor.of(Color(rgb))
