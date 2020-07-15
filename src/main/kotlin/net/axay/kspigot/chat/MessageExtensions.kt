@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.chat

import net.md_5.bungee.api.chat.BaseComponent
import org.bukkit.command.CommandSender

fun CommandSender.sendMessage(vararg components: BaseComponent) {
    this.spigot().sendMessage(*components)
}

fun CommandSender.sendMessage(builder: KSpigotComponentBuilder.() -> Unit) {
    this.spigot().sendMessage(*KSpigotComponentBuilder().apply(builder).create())
}