@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.ipaddress.badipdetectionservices

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.floatOrNull
import kotlinx.serialization.json.jsonPrimitive
import net.axay.kspigot.ipaddress.BadIPDetectionResult
import net.axay.kspigot.ipaddress.BadIPDetectionService

class GetIPIntel(
    private val intensity: Float = 0.99f,
    private val contactEmail: String = "foo@bar.com",
) : BadIPDetectionService("getipintel.net") {
    override fun requestString(ip: String) =
        "https://check.getipintel.net/check.php?ip=$ip&contact=$contactEmail&format=json"

    override fun interpreteResult(result: JsonObject): BadIPDetectionResult {
        val probability = result["result"]?.jsonPrimitive?.floatOrNull
            ?: return BadIPDetectionResult.ERROR
        return if (probability >= intensity) BadIPDetectionResult.GENERAL_BAD else BadIPDetectionResult.GOOD
    }
}
