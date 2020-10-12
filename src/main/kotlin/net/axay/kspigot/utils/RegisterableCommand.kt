package net.axay.kspigot.utils

import net.axay.kspigot.main.KSpigotMainInstance
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
    fun registerAs(commandName: String): Boolean {
        KSpigotMainInstance.getCommand(commandName)?.let {
            it.setExecutor(this)
            if (this is TabCompleter)
                it.tabCompleter = this
            return true
        }
        return false
    }

}