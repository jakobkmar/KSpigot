@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.chat

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.*
import org.bukkit.command.CommandSender

object KSpigotChat {

    inline fun buildComponent(builder: KSpigotComponentBuilder.() -> Unit): Array<out BaseComponent> {
        return KSpigotComponentBuilder().apply(builder).create()
    }

    fun CommandSender.sendMessage(vararg components: BaseComponent) {
        this.spigot().sendMessage(*components)
    }

    /**
     * Takes objects of the type [String] or [ChatColor],
     * combines them to a message and sends it to the player.
     */
    fun CommandSender.sendMessage(vararg messageParts: Any) {
        sendMessage(buildComponent {

            var currentColor = ChatColor.WHITE
            val currentMessage = StringBuilder()

            fun buildText() {
                text {
                    text = currentMessage.toString()
                    color = currentColor
                }
                currentMessage.clear()
            }

            for (messagePart in messageParts.withIndex()) {

                val index = messagePart.index
                val value = messagePart.value

                if (value is String) {
                    currentMessage.append(value)
                    if (index == messageParts.lastIndex)
                        buildText()
                } else if (value is ChatColor) {
                    buildText()
                    currentColor = value
                }

            }

        })
    }

}

class KSpigotComponentBuilder {

    val components = ArrayList<BaseComponent>()

    inline fun text(builder: TextComponentBuilder.() -> Unit) {
        val textComponent = TextComponentBuilder().apply(builder).textComponent
        if (textComponent != null)
            append(textComponent)
    }

    inline fun keybind(builder: KeybindComponentBuilder.() -> Unit) {
        val keybindComponent = KeybindComponentBuilder().apply(builder).keybindComponent
        if (keybindComponent != null)
            append(keybindComponent)
    }

    inline fun score(builder: ScoreComponentBuilder.() -> Unit) {
        val scoreComponent = ScoreComponentBuilder().apply(builder).scoreComponent
        if (scoreComponent != null)
            append(scoreComponent)
    }

    inline fun selector(builder: SelectorComponentBuilder.() -> Unit) {
        val selectorComponent = SelectorComponentBuilder().apply(builder).selectorComponent
        if (selectorComponent != null)
            append(selectorComponent)
    }

    inline fun translatable(builder: TranslatableComponentBuilder.() -> Unit) {
        val translatableComponent = TranslatableComponentBuilder().apply(builder).translatableComponent
        if (translatableComponent != null)
            append(translatableComponent)
    }

    fun append(baseComponent: BaseComponent) { components += baseComponent }
    fun create() = components.toTypedArray()

}

open class BaseComponentBuilder {

    // style
    var color: ChatColor? = null
    var font: String? = null
    var bold: Boolean? = null
    var italic: Boolean? = null
    var underlined: Boolean? = null
    var strikethrough: Boolean? = null
    var obfuscated: Boolean? = null

    // behaviour
    var insertion: String? = null

    // events
    var hoverEvent: HoverEvent? = null
    var clickEvent: ClickEvent? = null

    // TODO
    // var extra: List<BaseComponent>? = null

    inline fun hoverEvent(action: HoverEvent.Action, builder: KSpigotComponentBuilder.() -> Unit)
            = HoverEvent(action, KSpigotComponentBuilder().apply(builder).create())

    protected fun applyTo(baseComponent: BaseComponent) {

        color?.let { baseComponent.color = it }
        font?.let { baseComponent.font = it }
        bold?.let { baseComponent.isBold = it }
        italic?.let { baseComponent.isItalic = it }
        underlined?.let { baseComponent.isUnderlined = it }
        strikethrough?.let { baseComponent.isStrikethrough = it }
        obfuscated?.let { baseComponent.isObfuscated = it }

        insertion?.let { baseComponent.insertion = it }

        hoverEvent?.let { baseComponent.hoverEvent = it }
        clickEvent?.let { baseComponent.clickEvent = it }

    }

}

class TextComponentBuilder : BaseComponentBuilder() {

    var text: String? = null

    val textComponent: TextComponent?
        get() {
            text?.let { curText ->
                val toReturn = TextComponent(curText)
                super.applyTo(toReturn)
                return toReturn
            }
            return null
        }

}

class KeybindComponentBuilder : BaseComponentBuilder() {

    var keybind: String? = null

    val keybindComponent: KeybindComponent?
        get() {
            val toReturn = KeybindComponent(keybind ?: return null)
            super.applyTo(toReturn)
            return toReturn
        }

}

class ScoreComponentBuilder : BaseComponentBuilder() {

    var name: String? = null
    var objective: String? = null
    var value: String? = null

    val scoreComponent: ScoreComponent?
        get() {
            name?.let { curName -> objective?.let { curObjective ->
                value?.let { curValue ->
                    val toReturn = ScoreComponent(curName, curObjective, curValue)
                    super.applyTo(toReturn)
                    return toReturn
                }
                val toReturn = ScoreComponent(curName, curObjective)
                super.applyTo(toReturn)
                toReturn
            } }
            return null
        }

}

class SelectorComponentBuilder : BaseComponentBuilder() {

    var selector: String? = null

    val selectorComponent: SelectorComponent?
        get() {
            val toReturn = SelectorComponent(selector ?: return null)
            super.applyTo(toReturn)
            return toReturn
        }

}

class TranslatableComponentBuilder : BaseComponentBuilder() {

    var translatable: String? = null
    var with: MutableList<BaseComponent>? = null

    val translatableComponent: TranslatableComponent?
        get() {
            translatable?.let { curTranslatable -> with?.let { curWith ->
                val toReturn = TranslatableComponent(curTranslatable, *curWith.toTypedArray())
                super.applyTo(toReturn)
                return toReturn
            } }
            return null
        }

}