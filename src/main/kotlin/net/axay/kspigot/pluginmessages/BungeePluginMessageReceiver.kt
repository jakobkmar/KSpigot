package net.axay.kspigot.pluginmessages

import net.axay.kspigot.runnables.taskRunLater
import org.bukkit.entity.Player
import org.bukkit.plugin.messaging.PluginMessageListener
import java.io.ByteArrayInputStream
import java.io.DataInputStream

internal class BungeePluginMessageResponseCallback(
    val subChannel: String,
    val timeoutSeconds: Int,
    val onResponse: (message: DataInputStream) -> Unit,
) {

    init {
        BungeePluginMessageReceiver.registered += this

        taskRunLater(20L * timeoutSeconds) {
            BungeePluginMessageReceiver.registered -= this
        }
    }
}

private object BungeePluginMessageReceiver : PluginMessageListener {
    val registered = ArrayList<BungeePluginMessageResponseCallback>()
    override fun onPluginMessageReceived(channel: String, player: Player, message: ByteArray) {
        if (channel != "BungeeCord") return
        val msgbytes = ByteArrayInputStream(message)
        val msgin = DataInputStream(msgbytes)
        val subChannel = msgin.readUTF()
        val callback = registered.find { it.subChannel == subChannel }
        if (callback != null) {
            registered -= callback
            callback.onResponse.invoke(msgin)
        }
    }
}
