package net.axay.kspigot.ipaddress.badipdetectionservices

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonPrimitive
import net.axay.kspigot.ipaddress.BadIPDetectionResult
import net.axay.kspigot.ipaddress.BadIPDetectionService

class VPNBlocker : BadIPDetectionService("vpnblocker.net") {
    override fun requestString(ip: String) = "https://api.vpnblocker.net/v2/json/$ip"

    override fun interpreteResult(result: JsonObject): BadIPDetectionResult {
        val isBad = result["host-ip"]?.jsonPrimitive
        return when {
            isBad != null -> if (isBad.boolean) BadIPDetectionResult.GENERAL_BAD else BadIPDetectionResult.GOOD
            else -> {
                val remaining = result["remaining_requests"]?.jsonPrimitive?.intOrNull
                    ?: return BadIPDetectionResult.ERROR
                if (remaining <= 0) BadIPDetectionResult.LIMIT else BadIPDetectionResult.ERROR
            }
        }
    }
}
