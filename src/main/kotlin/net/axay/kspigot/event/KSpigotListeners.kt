package net.axay.kspigot.event

import net.axay.kspigot.extensions.pluginManager
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

/**
 * Shortcut for registering this listener on the given plugin.
 */

fun Listener.register(plugin: Plugin) = pluginManager.registerEvents(this, plugin)

fun Listener.unregister() = HandlerList.unregisterAll(this)

fun <T : Event> Plugin.listen(onEvent: (T) -> Unit): SingleListener<T> {
    val listener = object : SingleListener<T> {
        override fun onEvent(event: T) = onEvent.invoke(event)
    }
    listener.register(this)
    return listener
}

interface SingleListener<T : Event> : Listener {
    @EventHandler
    fun onEvent(event: T)
}