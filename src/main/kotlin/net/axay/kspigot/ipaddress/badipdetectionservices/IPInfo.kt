package net.axay.kspigot.ipaddress.badipdetectionservices

import net.axay.kspigot.ipaddress.BadIPDetectionResult
import net.axay.kspigot.ipaddress.BadIPDetectionService
import net.axay.kspigot.languageextensions.getStringOrNull
import org.json.JSONObject

class IPInfo(
    private val token: String,
) : BadIPDetectionService("ipinfo.io") {
    override fun requestString(ip: String) = "https://ipinfo.io/$ip/privacy?token=$token"
    override fun interpreteResult(result: JSONObject): BadIPDetectionResult {
        return when {
            result.getStringOrNull("vpn").toBoolean() -> BadIPDetectionResult.VPN
            result.getStringOrNull("proxy").toBoolean() -> BadIPDetectionResult.PROXY
            result.getStringOrNull("tor").toBoolean() -> BadIPDetectionResult.TOR
            result.getStringOrNull("hosting").toBoolean() -> BadIPDetectionResult.HOSTING
            else -> BadIPDetectionResult.GOOD
        }
    }
}