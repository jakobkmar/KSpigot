package net.axay.kspigot.pluginmessages

import net.axay.kspigot.extensions.onlinePlayers
import net.axay.kspigot.main.KSpigotMainInstance
import org.bukkit.entity.Player
import java.io.ByteArrayOutputStream
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
    content: List<String>? = null
): Boolean {
    val randomPlayer = onlinePlayers.randomOrNull()
    return if (randomPlayer != null) {
        sendPluginMessageToBungeeCord(randomPlayer, subChannel, content)
        true
    } else false
}

/**
 * Sends a plugin message on the "BungeeCord" channel.
 * Specify your sub channel and if necessary add the
 * required content.
 */
fun sendPluginMessageToBungeeCord(
    player: Player,
    subChannel: String,
    content: List<String>? = null
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

    player.sendPluginMessage(KSpigotMainInstance, "BungeeCord", msgbytes.toByteArray())

}