@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.ipaddress.badipdetectionservices

import net.axay.kspigot.ipaddress.BadIPDetectionResult
import net.axay.kspigot.ipaddress.BadIPDetectionService
import net.axay.kspigot.languageextensions.getStringOrNull
import org.json.JSONObject

class GetIPIntel(
    private val intensity: Float = 0.99f,
    private val contactEmail: String = "foo@bar.com",
) : BadIPDetectionService("getipintel.net") {
    override fun requestString(ip: String) =
        "http://check.getipintel.net/check.php?ip=$ip&contact=$contactEmail&format=json"

    override fun interpreteResult(result: JSONObject): BadIPDetectionResult {
        val probability = result.getStringOrNull("result")?.toFloatOrNull()
            ?: return BadIPDetectionResult.ERROR
        return if (probability >= intensity) BadIPDetectionResult.GENERAL_BAD else BadIPDetectionResult.GOOD
    }
}