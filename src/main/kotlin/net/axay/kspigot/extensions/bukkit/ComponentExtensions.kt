@file:Suppress("unused")
package net.axay.kspigot.extensions.bukkit

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TranslatableComponent
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import net.kyori.adventure.translation.GlobalTranslator
import java.util.Locale

/**
 * Returns a [Component] from a [String]
 */
fun String.toComponent(): Component = Component.text(this)

/**
 * Returns a [TranslatableComponent] with the given [String] as key and [args]
 */
fun String.asTranslatable(vararg args: Component): TranslatableComponent = Component.translatable(this, *args)

/**
 * Returns a [Component] from a [String] with legacy formatting
 */
fun String.legacyToComponent(): Component = LegacyComponentSerializer.legacyAmpersand().deserialize(this)

/**
 * Returns a [String] with legacy formatting from a [Component]
 *
 * Note: Render [TranslatableComponent]s before using this
 */
fun Component.toLegacyString(): String = LegacyComponentSerializer.legacyAmpersand().serialize(this)

/**
 * Returns a [String] from a [Component]
 *
 * Note: Render [TranslatableComponent]s before using this
 */
fun Component.plainText(): String = PlainTextComponentSerializer.plainText().serialize(this)

/**
 * Renders a [TranslatableComponent] with the given [locale]
 */
fun TranslatableComponent.render(locale: Locale): Component = GlobalTranslator.render(this, locale)
