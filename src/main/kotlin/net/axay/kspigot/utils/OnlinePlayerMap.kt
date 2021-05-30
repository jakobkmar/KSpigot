@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.utils

import net.axay.kspigot.event.listen
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerQuitEvent
import java.util.*

object PlayerMapHolder {
    internal val maps = HashSet<OnlinePlayerMap<*>>()

    init {
        listen<PlayerQuitEvent> { event ->
            maps.removeIf {
                if (it.internalMap.remove(event.player.uniqueId) != null) {
                    it.internalMap.isEmpty()
                } else false
            }
        }
    }
}

/**
 * A map where entries will be removed
 * atomatically when a player leaves the server.
 */
class OnlinePlayerMap<V> {
    val internalMap = HashMap<UUID, V>()

    operator fun get(player: Player) = internalMap[player.uniqueId]

    operator fun set(player: Player, value: V) {
        if (internalMap.isEmpty())
            PlayerMapHolder.maps += this
        internalMap[player.uniqueId] = value
    }

    operator fun minusAssign(player: Player) {
        internalMap -= player.uniqueId
        if (internalMap.isEmpty())
            PlayerMapHolder.maps -= this
    }
}
