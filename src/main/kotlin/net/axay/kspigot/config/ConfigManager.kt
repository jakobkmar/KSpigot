@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package net.axay.kspigot.config

import net.axay.kspigot.kotlinextensions.createIfNotExists
import net.axay.kspigot.main.ValueHolder.getGson
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

/**
 * Creates a new ConfigDelegate object.
 *
 * You can use this as a delegate (with **by**) or
 * a normal object.
 * (Delegate allows direct access to the config
 * object, but does not provide save() or reload()
 * methods.)
 *
 * @param T The class type of the config.
 * @param file The path to the config.
 * @param default Optional default config, which will be
 * used if there is no config file and a new one should
 * be created.
 * @throws java.io.FileNotFoundException If the file does not
 * exist and no default config is specified.
 */
inline fun <reified T : Any> kSpigotJsonConfig(
    file: File,
    noinline default: (() -> T)? = null,
) = ConfigDelegate(T::class, file, default)

/**
 * @see kSpigotJsonConfig
 */
class ConfigDelegate<T : Any>(
    private val configClass: KClass<T>,
    private val file: File,
    private val defaultCallback: (() -> T)?
) {

    private var internalConfig: T = loadIt()

    var data: T
        get() = internalConfig
        set(value) {
            internalConfig = value
        }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = internalConfig

    operator fun setValue(thisRef: Any?, property: KProperty<*>, config: T): Boolean {
        internalConfig = config
        return true
    }

    /**
     * Saves the config object in its current state to disk.
     */
    fun save() = saveIt(internalConfig)

    /**
     * Loads the current state of the config on disk to the config object.
     */
    fun reload() {
        loadIt()
    }

    private fun saveIt(toSave: T) {
        GsonConfigManager.saveConfig(file, toSave, true)
        internalConfig = toSave
    }

    private fun loadIt() = if (defaultCallback == null)
        GsonConfigManager.loadConfig(file, configClass)
    else
        GsonConfigManager.loadOrCreateDefault(file, configClass, true, defaultCallback)

}

internal object GsonConfigManager {

    fun <T : Any> loadConfig(file: File, configClass: KClass<T>): T =
        FileReader(file).use { reader -> return getGson(false).fromJson(reader, configClass.java) }

    fun <T : Any> saveConfig(file: File, config: T, pretty: Boolean = true) {
        file.createIfNotExists()
        FileWriter(file).use { writer ->
            getGson(pretty).toJson(config, writer)
        }
    }

    fun <T : Any> loadOrCreateDefault(
        file: File,
        configClass: KClass<T>,
        pretty: Boolean = true,
        default: () -> T
    ): T {
        try {
            return loadConfig(file, configClass)
        } catch (exc: Exception) {
            default.invoke().let {
                saveConfig(file, it, pretty)
                return it
            }
        }
    }

}