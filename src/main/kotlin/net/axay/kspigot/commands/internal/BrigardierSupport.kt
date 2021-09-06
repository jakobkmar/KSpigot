@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.commands.internal

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.axay.kspigot.annotations.NMS_1_17
import net.axay.kspigot.annotations.NMS_General
import net.axay.kspigot.event.listen
import net.axay.kspigot.extensions.onlinePlayers
import net.axay.kspigot.extensions.server
import net.axay.kspigot.main.KSpigotMainInstance
import net.axay.kspigot.utils.reflectField
import net.minecraft.commands.CommandListenerWrapper
import org.bukkit.event.player.PlayerJoinEvent

typealias ServerCommandSource = CommandListenerWrapper

/**
 * This class provides Brigardier support. It does that
 * by using reflection once. Additionally, this class is
 * using some obfuscated functions.
 */
object BrigardierSupport {
    @PublishedApi
    internal val commands = LinkedHashSet<LiteralArgumentBuilder<CommandListenerWrapper>>()

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

    /**
     * The command manager is used to hold the command dispatcher,
     * and to manage and dispatch the brigardier commands for
     * all players on the server.
     */
    @Suppress("HasPlatformType") // do not refer non-lazily to the type in this class
    @NMS_General
    val commandManager by lazy {
        (server as org.bukkit.craftbukkit.v1_17_R1.CraftServer).server.vanillaCommandDispatcher
    }

    /**
     * The command dispatcher is used to register brigardier commands.
     */
    @NMS_1_17
    val commandDispatcher by lazy {
        // g = the command dispatcher
        commandManager.reflectField<CommandDispatcher<CommandListenerWrapper>>("g")
    }

    @NMS_General
    internal fun registerAll() {
        executedDefaultRegistration = true

        // TODO unregister commands which are now missing due to a possible reload
        if (commands.isNotEmpty()) {
            commands.forEach {
                commandDispatcher.register(it)
            }
            if (onlinePlayers.isNotEmpty())
                updateCommandTree()
        }
    }

    @NMS_General
    fun updateCommandTree() {
        onlinePlayers.forEach {
            // send the command tree
            commandManager.a((it as org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer).handle)
        }
    }
}
