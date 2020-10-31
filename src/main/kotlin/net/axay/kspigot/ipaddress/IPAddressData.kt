@file:Suppress("unused")

package net.axay.kspigot.ipaddress

import com.google.gson.JsonObject
import net.axay.kspigot.languageextensions.fromUrlJson
import net.axay.kspigot.languageextensions.getStringOrNull
import net.axay.kspigot.main.ValueHolder
import org.bukkit.entity.Player

private const val IP_API = "http://ip-api.com/json/"
private const val IP_API_FIELDS =
    "status,message,continent,continentCode,country,countryCode,region,regionName,city,district,zip,lat,lon,timezone,currency,isp,org,query"

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

        val jsonObject = ValueHolder.getGson().fromUrlJson(
            "$IP_API${hostString}?fields=${IP_API_FIELDS}?lang=${language.code}"
        ) ?: return null

        if (jsonObject["status"].toString() == "fail") return null

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

    val ip get() = json.getStringOrNull("query")

    // region
    val continent get() = json.getStringOrNull("continent")
    val continentCode get() = json.getStringOrNull("continentCode")
    val country get() = json.getStringOrNull("country")
    val countryCode get() = json.getStringOrNull("countryCode")
    val region get() = json.getStringOrNull("regionName")
    val regionCode get() = json.getStringOrNull("region")
    val city get() = json.getStringOrNull("city")
    val district get() = json.getStringOrNull("district")
    val postalCode get() = json.getStringOrNull("zip")
    val timezone get() = json.getStringOrNull("timezone")

    // position
    val latitude get() = json.getStringOrNull("lat")
    val longitude get() = json.getStringOrNull("lon")

    // information
    val internetServiceProvider get() = json.getStringOrNull("isp")
    val organisation get() = json.getStringOrNull("org")

}