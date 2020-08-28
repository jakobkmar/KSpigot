@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.chat

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.*
import net.md_5.bungee.api.chat.hover.content.Entity
import net.md_5.bungee.api.chat.hover.content.Item
import net.md_5.bungee.api.chat.hover.content.Text

inline fun chatComponent(builder: KSpigotComponentBuilder.() -> Unit): Array<out BaseComponent> {
    return KSpigotComponentBuilder().apply(builder).create()
}

class KSpigotComponentBuilder {

    private val components = ArrayList<BaseComponent>()

    inline fun text(text: String, builder: TextComponent.() -> Unit = { }) {
        append(TextComponent(text).apply(builder))
    }

    inline fun keybind(keybind: String, builder: KeybindComponent.() -> Unit = { }) {
        append(KeybindComponent(keybind).apply(builder))
    }

    inline fun score(name: String, objective: String, value: String?, builder: ScoreComponent.() -> Unit = { }) {
        if (value != null)
            append(ScoreComponent(name, objective, value).apply(builder))
        else
            append(ScoreComponent(name, objective).apply(builder))
    }

    inline fun selector(selector: String, builder: SelectorComponent.() -> Unit = { }) {
        append(SelectorComponent(selector).apply(builder))
    }

    inline fun translatable(translatable: String, with: Array<BaseComponent>, builder: TranslatableComponent.() -> Unit = { }) {
        append(TranslatableComponent(translatable, with).apply(builder))
    }

    fun append(baseComponent: BaseComponent) { components += baseComponent }
    fun create() = components.toTypedArray()

}

/**
 * BASE COMPONENT
 */

// extensions

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
 * GLOBAL SHORTCUTS
 */

fun col(hex: String): ChatColor = ChatColor.of(hex)