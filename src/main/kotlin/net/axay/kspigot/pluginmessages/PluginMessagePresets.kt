@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.pluginmessages

import org.bukkit.entity.Player

/**
 * Sends the sending player to the given server
 */
class PluginMessageConnect(
    val servername: String
) : BungeePluginMessagePlayerSpecific {
    override fun sendWithPlayer(player: Player) = sendPluginMessageToBungeeCord(
        player, "Connect", listOf(servername)
    )
}

/**
 * Responds with the amount of players currently online
 * on the given server.
 */
class PluginMessagePlayerCount(
    val servername: String,
    private val response: (Int) -> Unit
) : BungeePluginMessageRandomPlayer {
    override fun send() = sendPluginMessageToBungeeCordRandomPlayer(
        "PlayerCount", listOf(servername)
    ) {
        response.invoke(it.readInt())
    }
}

/**
 * Responds with the amount of players currently online
 * on all servers.
 */
class PluginMessagePlayerCountAllServers(
    private val response: (Int) -> Unit
) : BungeePluginMessageRandomPlayer {
    override fun send() = sendPluginMessageToBungeeCordRandomPlayer(
        "PlayerCount", listOf("ALL")
    ) {
        response.invoke(it.readInt())
    }
}

/**
 * Responds with the names of all players currently online
 * on the given server.
 */
class PluginMessagePlayerList(
    val servername: String,
    private val response: (List<String>) -> Unit
) : BungeePluginMessageRandomPlayer {
    override fun send() = sendPluginMessageToBungeeCordRandomPlayer(
        "PlayerList", listOf(servername)
    ) {
        response.invoke(it.readUTF().split(", "))
    }
}

/**
 * Responds with the names of all players currently online
 * on all servers.
 */
class PluginMessagePlayerListAllServers(
    val servername: String,
    private val response: (List<String>) -> Unit
) : BungeePluginMessageRandomPlayer {
    override fun send() = sendPluginMessageToBungeeCordRandomPlayer(
        "PlayerList", listOf("ALL")
    ) {
        response.invoke(it.readUTF().split(", "))
    }
}

/**
 * Responds with the names of all servers in the
 * BungeeCord network.
 */
class PluginMessageGetServers(
    private val response: (List<String>) -> Unit
) : BungeePluginMessageRandomPlayer {
    override fun send() = sendPluginMessageToBungeeCordRandomPlayer(
        "GetServers"
    ) {
        response.invoke(it.readUTF().split(", "))
    }
}