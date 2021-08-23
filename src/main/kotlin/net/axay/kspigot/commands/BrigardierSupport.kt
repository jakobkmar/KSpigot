@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.commands

import com.mojang.brigadier.tree.LiteralCommandNode
import me.lucko.commodore.CommodoreProvider
import net.axay.kspigot.main.PluginInstance

/**
 * This class provides Brigardier support. It does that
 * by using reflection once. Additionally, this class is
 * using some obfuscated functions.
 */
object BrigardierSupport {
    private val provider = if (CommodoreProvider.isSupported()) CommodoreProvider.getCommodore(PluginInstance) else kotlin.run {
        PluginInstance.logger.severe("Could not initialize Brigardier support on the current Minecraft version! (Requested by ${PluginInstance.name})")
        null
    }

    fun register(name: String, brigardierCommand: LiteralCommandNode<*>) {
        val command = PluginInstance.getCommand(name)
        if (command == null) {
            PluginInstance.logger.severe("Could not register command '$name' of plugin ${PluginInstance.name}! Maybe it is missing from the plugin.yml?")
            return
        }
        provider?.register(command, brigardierCommand)
    }
}
