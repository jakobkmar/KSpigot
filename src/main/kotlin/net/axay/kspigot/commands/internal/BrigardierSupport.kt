@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.commands.internal

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.axay.kspigot.event.listen
import net.axay.kspigot.extensions.onlinePlayers
import net.axay.kspigot.extensions.server
import net.axay.kspigot.main.KSpigotMainInstance
import net.minecraft.commands.CommandSourceStack
import org.bukkit.event.player.PlayerJoinEvent

/**
 * This class provides Brigardier support. It does that
 * by using reflection once. Additionally, this class is
 * using some obfuscated functions.
 */
object BrigardierSupport {
    @PublishedApi
    internal val commands = LinkedHashSet<LiteralArgumentBuilder<CommandSourceStack>>()

    internal var executedDefaultRegistration = false
        private set

    init {
        listen<PlayerJoinEvent> { event ->
            val player = event.player
            val permAttachment = player.addAttachment(KSpigotMainInstance)
            commands.forEach {
                permAttachment.setPermission("minecraft.command.${it.literal}", true)
            }
        }
    }

    @Suppress("HasPlatformType")
    fun resolveCommandManager() = (server as org.bukkit.craftbukkit.v1_20_R1.CraftServer)
        .server.vanillaCommandDispatcher

    internal fun registerAll() {
        executedDefaultRegistration = true

        // TODO unregister commands which are now missing due to a possible reload
        if (commands.isNotEmpty()) {
            commands.forEach {
                resolveCommandManager().dispatcher.register(it)
            }
            if (onlinePlayers.isNotEmpty())
                updateCommandTree()
        }
    }

    fun updateCommandTree() {
        onlinePlayers.forEach {
            // send the command tree
            resolveCommandManager().sendCommands((it as org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer).handle)
        }
    }
}
