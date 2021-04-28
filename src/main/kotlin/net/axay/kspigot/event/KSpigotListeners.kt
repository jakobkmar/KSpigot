package net.axay.kspigot.event

import net.axay.kspigot.extensions.pluginManager
import net.axay.kspigot.main.KSpigotMainInstance
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener

/**
 * Shortcut for registering this listener on the given plugin.
 */
fun Listener.register() = pluginManager.registerEvents(this, KSpigotMainInstance)

/**
 * Shortcut for unregistering all events in this listener.
 */
fun Listener.unregister() = HandlerList.unregisterAll(this)

/**
 * Registers the event with a custom event [executor].
 *
 * @param T the type of event
 * @param priority the priority when multiple listeners handle this event
 * @param ignoreCancelled if manual cancellation should be ignored
 * @param executor handles incoming events
 */
inline fun <reified T : Event> Listener.register(
    priority: EventPriority = EventPriority.NORMAL,
    ignoreCancelled: Boolean = false,
    noinline executor: (Listener, Event) -> Unit
) {
    pluginManager.registerEvent(T::class.java, this, priority, executor, KSpigotMainInstance, ignoreCancelled)
}

/**
 * This class represents a [Listener] with
 * only one event to listen to.
 */
interface SingleListener<T : Event> : Listener {
    fun onEvent(event: T)
}

/**
 * Registers the [SingleListener] with its
 * event listener.
 *
 * @param priority the priority when multiple listeners handle this event
 * @param ignoreCancelled if manual cancellation should be ignored
 */
inline fun <reified T : Event> SingleListener<T>.register(
    priority: EventPriority = EventPriority.NORMAL,
    ignoreCancelled: Boolean = false
) {
    register<T>(priority, ignoreCancelled) { _, event ->
        (event as? T)?.let { this.onEvent(it) }
    }
}

/**
 * @param T the type of event to listen to
 * @param priority the priority when multiple listeners handle this event
 * @param ignoreCancelled if manual cancellation should be ignored
 * @param register if the event should be registered immediately
 * @param onEvent the event callback
 */
inline fun <reified T : Event> listen(
    priority: EventPriority = EventPriority.NORMAL,
    ignoreCancelled: Boolean = false,
    register: Boolean = true,
    crossinline onEvent: (event: T) -> Unit
): SingleListener<T> {
    val listener = object : SingleListener<T> {
        override fun onEvent(event: T) = onEvent.invoke(event)
    }
    if(register) listener.register(priority, ignoreCancelled)
    return listener
}