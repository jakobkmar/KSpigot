package net.axay.kspigot.localization

import org.apache.commons.lang.text.StrSubstitutor
import org.bukkit.entity.Player
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.*

/**
 * Handles localization of strings using java [ResourceBundle]s.
 *
 * Message property files should reside in `src/main/resources` named
 * `messages_locale.properties`, where `locale` is formatted as returned by
 * [Locale.toString].
 */
object Localization {
    /**
     * This function determines which locale is used for a player, by default
     * [Locale.US] is always returned.
     *
     * It is invoked every time a message is localized, so some sort of caching
     * is advisable in many cases.
     */
    var localeProvider: (Player) -> Locale = { Locale.US }
    private val bundles: MutableMap<Locale, ResourceBundle> = HashMap()

    /**
     * Returns the localized string for [key]
     *
     * @throws MissingResourceException if no messages exist for the given
     * locale or [key] was not found in the messages
     */
    fun get(locale: Locale, key: String): String =
        getOrLoadBundle(locale)?.getString(key) ?: throw MissingResourceException(
            "Messages for locale '$locale' not found", "ResourceBundle", key
        )

    /**
     * Additionally formats the localized string with the named [args], which
     * are represented as `{argumentName}` in the translations
     */
    fun get(locale: Locale, key: String, vararg args: Pair<String, Any?>): String =
        StrSubstitutor.replace(
            get(locale, key),
            mapOf(*args),
            "{", "}"
        )

    private fun getOrLoadBundle(locale: Locale): ResourceBundle? {
        return bundles[locale] ?: InputStreamReader(
            javaClass
                .classLoader
                .getResourceAsStream("messages_$locale.properties") ?: return null,
            StandardCharsets.UTF_8
        ).use {
            val bundle = PropertyResourceBundle(it)
            bundles[locale] = bundle
            bundle
        }
    }
}

/**
 * Returns the localized message using the locale provided by
 * [Localization.localeProvider]
 *
 * @see Localization.get
 */
fun Player.localize(key: String) =
    Localization.get(Localization.localeProvider(this), key)

/**
 * @see Localization.get
 */
fun Player.localize(key: String, vararg args: Pair<String, Any?>) =
    Localization.get(Localization.localeProvider(this), key, *args)
