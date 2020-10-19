package net.axay.kspigot.languageextensions

import org.json.JSONObject

internal fun JSONObject.getStringOrNull(key: String): String? {
    return try {
        this[key].toString()
    } catch (exc: Exception) {
        null
    }
}