package net.axay.kspigot.ipaddress.badipdetectionservices

import net.axay.kspigot.ipaddress.BadIPDetectionResult
import net.axay.kspigot.ipaddress.BadIPDetectionService
import net.axay.kspigot.languageextensions.getStringOrNull
import org.json.JSONObject

class VPNBlocker : BadIPDetectionService("vpnblocker.net") {

    override fun requestString(ip: String) = "http://api.vpnblocker.net/v2/json/$ip"

    override fun interpreteResult(result: JSONObject): BadIPDetectionResult {
        val isBad = result.getStringOrNull("host-ip")
        return when {
            isBad != null -> if (isBad.toBoolean()) BadIPDetectionResult.GENERAL_BAD else BadIPDetectionResult.GOOD
            else -> {
                val remaining = result.getStringOrNull("remaining_requests")?.toIntOrNull()
                    ?: return BadIPDetectionResult.ERROR
                if (remaining <= 0) BadIPDetectionResult.LIMIT else BadIPDetectionResult.ERROR
            }
        }
    }

}