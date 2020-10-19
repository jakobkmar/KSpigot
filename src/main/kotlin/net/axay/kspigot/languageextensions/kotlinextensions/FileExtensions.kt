package net.axay.kspigot.languageextensions.kotlinextensions

import java.io.File

internal fun File.createIfNotExists(): Boolean {
    return if (!exists()) {
        if (!parentFile.exists())
            parentFile.mkdirs()
        createNewFile()
    } else true
}
