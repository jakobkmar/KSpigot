package net.axay.kspigot.ipaddress.badipdetectionservices

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.jsonPrimitive
import net.axay.kspigot.ipaddress.BadIPDetectionResult
import net.axay.kspigot.ipaddress.BadIPDetectionService

/**
 * IP2Location.io lookup for bad IP addresses.
 *
 * The API key is optional. Without a key, the service allows 1,000 queries/day.
 * A free key gives 50K queries/month. Plus and Security plans add VPN, Tor,
 * residential proxy and data center flags.
 *
 * @param apiKey Optional IP2Location.io API key. Omit it to use the keyless quota.
 */
class IP2LocationIO(
    private val apiKey: String? = null
) : BadIPDetectionService("ip2location.io") {
    override fun requestString(ip: String) =
        if (apiKey.isNullOrEmpty()) "https://api.ip2location.io/?ip=$ip"
        else "https://api.ip2location.io/?key=$apiKey&ip=$ip"

    override fun interpreteResult(result: JsonObject): BadIPDetectionResult {
        if (result["ip"] == null) return BadIPDetectionResult.ERROR

        val proxy = result["proxy"] as? JsonObject
        fun flag(name: String) = proxy?.get(name)?.jsonPrimitive?.booleanOrNull == true

        return when {
            flag("is_vpn") -> BadIPDetectionResult.VPN
            flag("is_tor") -> BadIPDetectionResult.TOR
            flag("is_public_proxy") || flag("is_web_proxy") || flag("is_residential_proxy") ->
                BadIPDetectionResult.PROXY
            flag("is_data_center") -> BadIPDetectionResult.HOSTING
            result["is_proxy"]?.jsonPrimitive?.booleanOrNull == true -> BadIPDetectionResult.PROXY
            else -> BadIPDetectionResult.GOOD
        }
    }
}
