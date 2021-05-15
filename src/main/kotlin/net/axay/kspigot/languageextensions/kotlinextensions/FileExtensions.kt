package net.axay.kspigot.languageextensions.kotlinextensions

import java.io.File

fun File.createIfNotExists(): Boolean {
    return if (!exists()) {
        if (!parentFile.exists())
            parentFile.mkdirs()
        if (isDirectory)
            mkdir()
        else createNewFile()
    } else true
}
