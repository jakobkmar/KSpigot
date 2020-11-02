package net.axay.kspigot.pluginmessages

import org.bukkit.entity.Player

interface BungeePluginMessageRandomPlayer {
    /**
     * @return True, if a player was found to send the message.
     */
    fun send(): Boolean
}

interface BungeePluginMessagePlayerSpecific {
    fun sendWithPlayer(player: Player)
}