package net.axay.kspigot.pluginmessages

import net.axay.kspigot.extensions.onlinePlayers
import net.axay.kspigot.main.PluginInstance
import org.bukkit.entity.Player
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException

/**
 * Chooses a random player to send the message.
 * Be careful to not send a player specific message
 * using this method!
 * @see sendPluginMessageToBungeeCord
 * @return True, if a player was found to send the message.
 */
fun sendPluginMessageToBungeeCordRandomPlayer(
    subChannel: String,
    content: List<String>? = null,
    responseTimeout: Int = 20,
    onResponse: ((message: DataInputStream) -> Unit)? = null,
): Boolean {
    val randomPlayer = onlinePlayers.randomOrNull()
    return if (randomPlayer != null) {
        sendPluginMessageToBungeeCord(randomPlayer, subChannel, content, responseTimeout, onResponse)
        true
    } else false
}

/**
 * Sends a plugin message on the "BungeeCord" channel.
 * Specify your sub channel and if necessary add the
 * required content.
 * @param player The player which should be used to send the message.
 * @param subChannel The channel, where the message should be send. (Usually the channel defines the behaviour.)
 * @param content The optional content of the message.
 * @param responseTimeout The time in seconds after which the response counts as "not arrived".
 * @param onResponse The optional reponse callback.
 */
fun sendPluginMessageToBungeeCord(
    player: Player,
    subChannel: String,
    content: List<String>? = null,
    responseTimeout: Int = 20,
    onResponse: ((message: DataInputStream) -> Unit)? = null,
) {
    val msgbytes = ByteArrayOutputStream()
    val msgout = DataOutputStream(msgbytes)

    try {
        msgout.writeUTF(subChannel)

        if (content != null)
            for (messagePart in content)
                msgout.writeUTF(messagePart)
    } catch (e: IOException) {
        e.printStackTrace()
    }

    if (onResponse != null)
        BungeePluginMessageResponseCallback(subChannel, responseTimeout, onResponse)

    player.sendPluginMessage(PluginInstance, "BungeeCord", msgbytes.toByteArray())
}
