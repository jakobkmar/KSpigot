@file:Suppress("unused")

package net.axay.kspigot.ipaddress

import com.google.gson.JsonObject
import net.axay.kspigot.main.ValueHolder
import org.bukkit.entity.Player
import java.net.URL

private const val IP_API = "http://ip-api.com/json/"
private const val IP_API_FIELDS = "status,message,continent,continentCode,country,countryCode,region,regionName,city,district,zip,lat,lon,timezone,currency,isp,org,query"

val Player.ipAddressData get() = ipAddressData()

fun Player.ipAddressData(language: IPAddressDataLanguage = IPAddressDataLanguage.ENGLISH): IPAddressData? {

    val hostString = address?.hostString ?: return null
    val jsonObject = ValueHolder.gson.fromJson(
            URL("$IP_API${hostString}?fields=${IP_API_FIELDS}?lang=${language.code}").readText(),
            JsonObject::class.java
    )

    if (jsonObject["status"].toString() == "fail") return null

    return IPAddressData(jsonObject)

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

    val ip get() = json.getString("query")

    // region
    val continent get() = json.getString("continent")
    val continentCode get() = json.getString("continentCode")
    val country get() = json.getString("country")
    val countryCode get() = json.getString("countryCode")
    val region get() = json.getString("regionName")
    val regionCode get() = json.getString("region")
    val city get() = json.getString("city")
    val district get() = json.getString("district")
    val postalCode get() = json.getString("zip")
    val timezone get() = json.getString("timezone")

    // position
    val latitude get() = json.getString("lat")
    val longitude get() = json.getString("lon")

    // information
    val internetServiceProvider get() = json.getString("isp")
    val organisation get() = json.getString("org")

}

private fun JsonObject.getString(key: String) = try {
    this[key].toString()
} catch (exc: Exception) {
    null
}