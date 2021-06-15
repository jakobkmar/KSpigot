@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package net.axay.kspigot.config

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.axay.kspigot.languageextensions.kotlinextensions.createIfNotExists
import java.io.File
import kotlin.reflect.KProperty

@Deprecated("Use the kSpigotConfig method instead, as it is more universal.", replaceWith = ReplaceWith("kSpigotConfig"))
/**
 * @see kSpigotConfig
 */
inline fun <reified T : Any> kSpigotJsonConfig(
    file: File,
    saveAfterLoad: Boolean = false,
    stringFormat: Json = Json,
    noinline default: (() -> T)? = null,
) = kSpigotConfig(file, saveAfterLoad, stringFormat, default)

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
 * @param saveAfterLoad If true, the loaded config will be saved
 * immediately. This is useful, if the config structure was changed
 * and new default parameters were applied.
 * @param default Optional default config, which will be
 * used if there is no config file and a new one should
 * be created.
 * @param json The json instance to use for serialization.
 * @throws java.io.FileNotFoundException If the file does not
 * exist and no default config is specified.
 */
inline fun <reified T : Any> kSpigotConfig(
    file: File,
    saveAfterLoad: Boolean = false,
    stringFormat: Json = Json,
    noinline default: (() -> T)? = null,
) = object : ConfigDelegate<T>(file, saveAfterLoad, default) {
    private var internalConfig: T = loadIt()
    var data: T
        get() = internalConfig
        set(value) {
            internalConfig = value
        }

    override operator fun getValue(thisRef: Any?, property: KProperty<*>) = internalConfig
    override operator fun setValue(thisRef: Any?, property: KProperty<*>, config: T): Boolean {
        internalConfig = config
        return true
    }

    override fun save() = saveIt(internalConfig)

    override fun reload() {
        loadIt()
    }

    override fun saveIt(toSave: T) {
        JsonConfigManager.saveConfig(file, toSave, stringFormat)
        internalConfig = toSave
    }

    override fun loadIt(): T {
        val loaded = if (default == null)
            JsonConfigManager.loadConfig(file, stringFormat)
        else
            JsonConfigManager.loadOrCreateDefault(file, stringFormat, default)

        if (saveAfterLoad)
            saveIt(loaded)

        return loaded
    }
}

/**
 * @see kSpigotJsonConfig
 */
abstract class ConfigDelegate<T : Any>(
    private val file: File,
    private val saveAfterLoad: Boolean,
    private val defaultCallback: (() -> T)?,
) {
    abstract operator fun getValue(thisRef: Any?, property: KProperty<*>): T
    abstract operator fun setValue(thisRef: Any?, property: KProperty<*>, config: T): Boolean

    /**
     * Saves the config object in its current state to disk.
     */
    abstract fun save()

    /**
     * Loads the current state of the config on disk to the config object.
     */
    abstract fun reload()

    abstract fun saveIt(toSave: T)

    protected abstract fun loadIt(): T
}

object JsonConfigManager {
    inline fun <reified T : Any> loadConfig(file: File, stringFormat: Json): T {
        return stringFormat.decodeFromString(file.readText())
    }

    inline fun <reified T : Any> saveConfig(file: File, config: T, stringFormat: Json) {
        file.createIfNotExists()
        file.writeText(stringFormat.encodeToString(config))
    }

    inline fun <reified T : Any> loadOrCreateDefault(
        file: File,
        stringFormat: Json,
        default: () -> T,
    ): T {
        try {
            return loadConfig(file, stringFormat)
        } catch (exc: Exception) {
            default.invoke().let {
                saveConfig(file, it, stringFormat)
                return it
            }
        }
    }
}
