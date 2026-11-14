package net.axay.kspigot.ipaddress.badipdetectionservices

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import net.axay.kspigot.ipaddress.BadIPDetectionResult
import net.axay.kspigot.ipaddress.BadIPDetectionService

class VPNDetection : BadIPDetectionService("vpndetection.io") {
    override fun requestString(ip: String) = "https://api.vpndetection.io/$ip"

    override fun interpreteResult(result: JsonObject): BadIPDetectionResult {
        if (result["ip"] == null) return BadIPDetectionResult.ERROR
        return when {
            result["is_vpn"]?.jsonPrimitive?.booleanOrNull == true -> BadIPDetectionResult.VPN
            result["is_tor"]?.jsonPrimitive?.booleanOrNull == true -> BadIPDetectionResult.TOR
            result["is_relay"]?.jsonPrimitive?.booleanOrNull == true -> BadIPDetectionResult.PROXY
            result["asn"]?.jsonObject?.get("type")?.jsonPrimitive?.contentOrNull == "hosting" -> BadIPDetectionResult.HOSTING
            else -> BadIPDetectionResult.GOOD
        }
    }
}
