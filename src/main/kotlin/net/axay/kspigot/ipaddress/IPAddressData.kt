@file:Suppress("unused")

package net.axay.kspigot.ipaddress

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.bukkit.entity.Player

@Suppress("HttpUrlsUsage")
private const val IP_API = "http://ip-api.com/json/"
private val IP_API_FIELDS = listOf(
    "status",
    "message",
    "continent",
    "continentCode",
    "country",
    "countryCode",
    "region",
    "regionName",
    "city",
    "district",
    "zip",
    "lat",
    "lon",
    "timezone",
    "currency",
    "isp",
    "org",
    "query"
).joinToString(",")

/**
 * @return The players ip address.
 */
val Player.ipAddressOrNull get() = address?.hostString

/**
 * @see ipAddressData
 */
val Player.ipAddressData get() = ipAddressData()

/**
 * @param language The preferred language of the result.
 * @return An object containing all the data which could
 * be found out about the IP address of the player.
 */
fun Player.ipAddressData(language: IPAddressDataLanguage = IPAddressDataLanguage.ENGLISH): IPAddressData? {
    return try {
        val hostString = address?.hostString ?: return null
        val jsonObject = Json.decodeFromString<JsonObject>(
            "$IP_API${hostString}?fields=${IP_API_FIELDS}?lang=${language.code}"
        )

        if (jsonObject["status"]?.jsonPrimitive?.toString() == "fail") return null

        IPAddressData(jsonObject)
    } catch (exc: Exception) {
        null
    }
}

enum class IPAddressDataLanguage(val code: String) {
    ENGLISH("en"),
    GERMAN("de"),
    SPANISH("es"),
    PORTUGUESE("pt-BR"),
    FRENCH("fr"),
    JAPANESE("ja"),
    CHINESE("zh-CN"),
    RUSSIAN("ru")
}

class IPAddressData(private val json: JsonObject) {
    val ip by lazy { json["query"]?.jsonPrimitive?.toString() }

    // region
    val continent by lazy { json["continent"]?.jsonPrimitive?.toString() }
    val continentCode by lazy { json["continentCode"]?.jsonPrimitive?.toString() }
    val country by lazy { json["country"]?.jsonPrimitive?.toString() }
    val countryCode by lazy { json["countryCode"]?.jsonPrimitive?.toString() }
    val region by lazy { json["regionName"]?.jsonPrimitive?.toString() }
    val regionCode by lazy { json["region"]?.jsonPrimitive?.toString() }
    val city by lazy { json["city"]?.jsonPrimitive?.toString() }
    val district by lazy { json["district"]?.jsonPrimitive?.toString() }
    val postalCode by lazy { json["zip"]?.jsonPrimitive?.toString() }
    val timezone by lazy { json["timezone"]?.jsonPrimitive?.toString() }

    // position
    val latitude by lazy { json["lat"]?.jsonPrimitive?.toString() }
    val longitude by lazy { json["lon"]?.jsonPrimitive?.toString() }

    // information
    val internetServiceProvider by lazy { json["isp"]?.jsonPrimitive?.toString() }
    val organisation by lazy { json["org"]?.jsonPrimitive?.toString() }
}
