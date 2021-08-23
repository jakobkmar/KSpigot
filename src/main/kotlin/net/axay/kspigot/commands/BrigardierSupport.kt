@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.axay.kspigot.annotations.NMS_1_17
import net.axay.kspigot.annotations.NMS_General
import net.axay.kspigot.extensions.onlinePlayers
import net.axay.kspigot.extensions.server
import net.axay.kspigot.utils.reflectField
import net.minecraft.commands.CommandListenerWrapper
import org.bukkit.craftbukkit.v1_17_R1.CraftServer
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer

/**
 * This class provides Brigardier support. It does that
 * by using reflection once. Additionally, this class is
 * using some obfuscated functions.
 */
object BrigardierSupport {
    @PublishedApi
    internal val commands = LinkedHashSet<LiteralArgumentBuilder<CommandListenerWrapper>>()

    private var executedDefaultRegistration = false

    /**
     * The command manager is used to hold the command dispatcher,
     * and to manage and dispatch the brigardier commands for
     * all players on the server.
     */
    @Suppress("HasPlatformType") // do not refer non-lazily to the type in this class
    @NMS_General
    val commandManager by lazy {
        (server as CraftServer).server.commandDispatcher
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

        if (commands.isNotEmpty()) {
            commands.forEach { commandDispatcher.register(it) }
            if (onlinePlayers.isNotEmpty())
                updateCommandTree()
        }
    }

    @NMS_1_17
    fun updateCommandTree() {
        onlinePlayers.forEach {
            // send the command treee
            commandManager.a((it as CraftPlayer).handle)
        }
    }

    /**
     * Registers this command at the [CommandDispatcher] of the server.
     *
     * @param sendToPlayers whether the new command tree should be send to
     * all players, this is true by default, but you can disable it if you are
     * calling this function as the server is starting
     */
    @NMS_General
    fun LiteralArgumentBuilder<CommandListenerWrapper>.register(sendToPlayers: Boolean = true) {
        if (!executedDefaultRegistration)
            commands += this
        else {
            commandDispatcher.register(this)
            if (sendToPlayers)
                updateCommandTree()
        }
    }
}
