package net.axay.kspigot.extensions.bukkit

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.bukkit.inventory.meta.BookMeta

val BookMeta.content
    get() =
        StringBuilder().apply {
            for (it in pages().map { LegacyComponentSerializer.legacy('§').serialize(it) }) {
                if (isNotEmpty())
                    append('\n')
                append(it)
            }
        }.toString()
