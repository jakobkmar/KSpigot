package net.axay.kspigot.languageextensions

import com.google.gson.Gson
import com.google.gson.JsonObject
import java.net.URL

internal fun JsonObject.getStringOrNull(key: String): String? {
    return try {
        this[key].toString()
    } catch (exc: Exception) {
        null
    }
}

internal fun Gson.fromUrlJson(url: String): JsonObject? {
    return fromJson(
        URL(url).readText(),
        JsonObject::class.java
    ) ?: return null
}