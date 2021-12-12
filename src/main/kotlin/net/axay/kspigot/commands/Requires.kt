package net.axay.kspigot.commands

import com.mojang.brigadier.builder.ArgumentBuilder
import net.minecraft.commands.CommandSourceStack
import org.bukkit.permissions.Permission

/**
 * Defines that the given [permission] is required to interact with this
 * path of the command.
 */
fun ArgumentBuilder<CommandSourceStack, *>.requiresPermission(permission: String): ArgumentBuilder<*, *> =
    requires {
        it.bukkitSender.hasPermission(permission)
    }

/**
 * Defines that the given [permission] is required to interact with this
 * path of the command.
 */
fun ArgumentBuilder<CommandSourceStack, *>.requiresPermission(permission: Permission): ArgumentBuilder<*, *> =
    requires {
        it.bukkitSender.hasPermission(permission)
    }
