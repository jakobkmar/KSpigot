package net.axay.kspigot.utils

import net.axay.kspigot.main.KSpigot
import org.bukkit.command.CommandExecutor
import org.bukkit.command.TabCompleter

interface RegisterableCommand : CommandExecutor {

    /**
     * Registers this executor for the given command
     * and for the given instance of [kSpigot].
     *
     * @return true if the command was found -
     * false if not
     */
    fun registerCommand(commandName: String, kSpigot: KSpigot): Boolean {
        kSpigot.plugin.getCommand(commandName)?.let {
            it.setExecutor(this)
            if (this is TabCompleter)
                it.tabCompleter = this
            return true
        }
        return false
    }

}