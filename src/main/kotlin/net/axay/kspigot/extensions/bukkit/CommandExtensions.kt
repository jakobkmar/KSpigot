package net.axay.kspigot.extensions.bukkit

import net.axay.kspigot.main.KSpigotMainInstance
import org.bukkit.command.CommandExecutor
import org.bukkit.command.TabCompleter

/**
 * Registers this CommandExecutor for
 * the given command.
 * @return If the command was registered successfully.
 */
fun CommandExecutor.register(commandName: String): Boolean {
    KSpigotMainInstance.getCommand(commandName)?.let {
        it.setExecutor(this)
        if (this is TabCompleter)
            it.tabCompleter = this
        return true
    }
    return false
}
