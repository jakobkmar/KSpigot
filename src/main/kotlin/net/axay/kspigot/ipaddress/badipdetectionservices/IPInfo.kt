package net.axay.kspigot.ipaddress.badipdetectionservices

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.jsonPrimitive
import net.axay.kspigot.ipaddress.BadIPDetectionResult
import net.axay.kspigot.ipaddress.BadIPDetectionService

class IPInfo(
    private val token: String,
) : BadIPDetectionService("ipinfo.io") {
    override fun requestString(ip: String) = "https://ipinfo.io/$ip/privacy?token=$token"

    override fun interpreteResult(result: JsonObject): BadIPDetectionResult {
        return when {
            result["vpn"]?.jsonPrimitive?.boolean == true -> BadIPDetectionResult.VPN
            result["proxy"]?.jsonPrimitive?.boolean == true -> BadIPDetectionResult.PROXY
            result["tor"]?.jsonPrimitive?.boolean == true -> BadIPDetectionResult.TOR
            result["hosting"]?.jsonPrimitive?.boolean == true -> BadIPDetectionResult.HOSTING
            else -> BadIPDetectionResult.GOOD
        }
    }
}
