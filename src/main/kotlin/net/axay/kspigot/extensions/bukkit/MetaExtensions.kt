package net.axay.kspigot.extensions.bukkit

import org.bukkit.inventory.meta.BookMeta

val BookMeta.content
    get() =
        StringBuilder().apply {
            for (it in pages) {
                if (isNotEmpty())
                    append('\n')
                append(it)
            }
        }.toString()
