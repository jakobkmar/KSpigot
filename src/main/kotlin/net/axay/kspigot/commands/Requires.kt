package net.axay.kspigot.commands

import com.mojang.brigadier.builder.ArgumentBuilder
import net.axay.kspigot.commands.internal.ServerCommandSource
import org.bukkit.permissions.Permission

/**
 * Defines that the given [permission] is required to interact with this
 * path of the command.
 */
fun ArgumentBuilder<ServerCommandSource, *>.requiresPermission(permission: String): ArgumentBuilder<*, *> =
    requires {
        it.bukkitSender.hasPermission(permission)
    }

/**
 * Defines that the given [permission] is required to interact with this
 * path of the command.
 */
fun ArgumentBuilder<ServerCommandSource, *>.requiresPermission(permission: Permission): ArgumentBuilder<*, *> =
    requires {
        it.bukkitSender.hasPermission(permission)
    }
