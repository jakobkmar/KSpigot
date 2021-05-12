@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.ipaddress.badipdetectionservices

import net.axay.kspigot.ipaddress.BadIPDetectionResult
import net.axay.kspigot.ipaddress.BadIPDetectionService
import net.axay.kspigot.languageextensions.getStringOrNull
import org.json.JSONObject

class IPHub(
    private val apiKey: String,
    private val ifStrict: Boolean = false,
) : BadIPDetectionService("iphub.info") {
    override fun requestString(ip: String) = "http://v2.api.iphub.info/ip/$ip"
    override fun requestHeaders() = mapOf("X-Key" to apiKey)
    override fun interpreteResult(result: JSONObject): BadIPDetectionResult {
        val ifBlock = result.getStringOrNull("block")?.toInt() ?: return BadIPDetectionResult.ERROR
        return if (ifBlock == 1 || (ifStrict && ifBlock == 2)) BadIPDetectionResult.GENERAL_BAD else BadIPDetectionResult.GOOD
    }
}