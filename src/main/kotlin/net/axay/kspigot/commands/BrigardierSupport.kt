package net.axay.kspigot.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.axay.kspigot.extensions.server
import net.minecraft.commands.CommandListenerWrapper
import org.bukkit.craftbukkit.v1_17_R1.CraftServer

object BrigardierSupport {
    @PublishedApi
    internal val commands = ArrayList<LiteralArgumentBuilder<CommandListenerWrapper>>()

    fun registerAll() {
        val commandManager = (server as CraftServer).server.commandDispatcher

        val dispatcherField = net.minecraft.commands.CommandDispatcher::class.java.getDeclaredField("g")
        dispatcherField.isAccessible = true
        @Suppress("UNCHECKED_CAST")
        val dispatcher = dispatcherField.get(commandManager) as CommandDispatcher<CommandListenerWrapper>

        commands.forEach { dispatcher.register(it) }
    }
}
