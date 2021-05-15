@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.ipaddress.badipdetectionservices

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonPrimitive
import net.axay.kspigot.ipaddress.BadIPDetectionResult
import net.axay.kspigot.ipaddress.BadIPDetectionService

class IPHub(
    private val apiKey: String,
    private val ifStrict: Boolean = false,
) : BadIPDetectionService("iphub.info") {
    override fun requestString(ip: String) = "https://v2.api.iphub.info/ip/$ip"
    override fun requestHeaders() = mapOf("X-Key" to apiKey)

    override fun interpreteResult(result: JsonObject): BadIPDetectionResult {
        val ifBlock = result["block"]?.jsonPrimitive?.intOrNull ?: return BadIPDetectionResult.ERROR
        return if (ifBlock == 1 || (ifStrict && ifBlock == 2)) BadIPDetectionResult.GENERAL_BAD else BadIPDetectionResult.GOOD
    }
}
