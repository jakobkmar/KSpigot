package net.axay.kspigot.main

import net.axay.kspigot.extensions.bukkit.warn
import net.axay.kspigot.extensions.console
import net.axay.kspigot.gui.GUIHolder
import net.axay.kspigot.languageextensions.kotlinextensions.closeIfInitialized
import net.axay.kspigot.runnables.KRunnableHolder
import org.bukkit.plugin.java.JavaPlugin

/**
 * This is the main instance of KSpigot.
 *
 * This class replaces (and inherits from) the
 * JavaPlugin class. Your main plugin class should
 * inherit from this abstract class.
 *
 * **Instead** of overriding [onLoad()], [onEnable()]
 * and [onDisable()] **override**:
 * - [load()] (called first)
 * - [startup()]  (called second)
 * - [shutdown()] (called in the "end")
 */
abstract class KSpigot : JavaPlugin() {
    // lazy properties
    private val kRunnableHolderProperty = lazy { KRunnableHolder }
    private val guiHolderProperty = lazy { GUIHolder }
    internal val kRunnableHolder by kRunnableHolderProperty
    internal val guiHolder by guiHolderProperty

    /**
     * Called when the plugin was loaded
     */
    open fun load() {}

    /**
     * Called when the plugin was enabled
     */
    open fun startup() {}

    /**
     * Called when the plugin gets disabled
     */
    open fun shutdown() {}

    final override fun onLoad() {
        internalMainInstance = this
        load()
    }

    final override fun onEnable() {
        startup()
    }

    final override fun onDisable() {
        shutdown()
        // avoid unnecessary load of lazy properties
        kRunnableHolderProperty.closeIfInitialized()
        guiHolderProperty.closeIfInitialized()
    }
}

private var internalMainInstance: KSpigot? = null
    set(value) {
        if (field != null) {
            console.warn("The main instance of KSpigot has been modified, even though it has already been set by another plugin!")
        }
        field = value
    }

val KSpigotMainInstance = internalMainInstance!!
