@file:Suppress("MemberVisibilityCanBePrivate", "Unused")

package net.axay.kspigot.chat

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.JoinConfiguration
import org.bukkit.command.CommandSender

fun CommandSender.sendMessage(vararg components: Component) {
    this.sendMessage(Component.join(JoinConfiguration.separator(Component.newline()), *components))
}

/**
 * Opens a [LiteralTextBuilder].
 *
 * @param baseText the text you want to begin with, it is okay to let this empty
 * @param builder the builder which can be used to set the style and add child text components
 */
inline fun CommandSender.sendText(
    baseText: String = "",
    crossinline builder: LiteralTextBuilder.() -> Unit = { }
) = this.sendMessage(literalText(baseText, builder))

@Suppress("DEPRECATION")
@Deprecated(
    "Outdated api, use literalText instead",
    ReplaceWith("sendText { builder.invoke() }", "net.axay.kspigot.chat.sendText")
)
fun CommandSender.sendMessage(builder: KSpigotComponentBuilder.() -> Unit) {
    this.spigot().sendMessage(*KSpigotComponentBuilder().apply(builder).create())
}
