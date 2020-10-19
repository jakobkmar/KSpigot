package net.axay.kspigot.ipaddress

import net.axay.kspigot.ipaddress.badipdetectionservices.GetIPIntel
import net.axay.kspigot.ipaddress.badipdetectionservices.IPHub
import org.bukkit.entity.Player
import org.json.JSONException
import org.json.JSONObject

/**
 * Checks if the IP address of the player is not a
 * normal residential address.
 *
 * This function returns true if just one of all
 * available detection services detects a bad IP address.
 *
 * @param detector The compound of detection services.
 */
fun Player.hasBadIP(detector: BadIPDetector = BadIPDetector.DEFAULT) =
    checkIP(detector).filterValues { it.isBad }.isNotEmpty()

/**
 * Checks if the IP address of the player is not a
 * normal residential address.
 *
 * This function returns the result of each
 * detection service given by the [detector].
 *
 * @param detector The compound of detection services.
 */
fun Player.checkIP(
    detector: BadIPDetector = BadIPDetector.DEFAULT,
    breakOnHit: Boolean = true
): Map<BadIPDetectionService, BadIPDetectionResult> {
    val ip = address?.hostString ?: return emptyMap()
    return detector.checkIP(ip, breakOnHit)
}

/**
 * @param services A list of [BadIPDetectionService]s.
 * The order matters!
 */
class BadIPDetector(
    val services: List<BadIPDetectionService>
) {

    constructor(vararg services: BadIPDetectionService) : this(services.toList())

    companion object {
        val DEFAULT = BadIPDetector(
            listOf(
                GetIPIntel(),
                IPHub()
            )
        )
    }

    fun checkIP(ip: String, breakOnHit: Boolean = true) =
        HashMap<BadIPDetectionService, BadIPDetectionResult>().apply {
            for (it in services) {

                val curResult = it.isBad(ip)
                this[it] = curResult

                if (curResult.isBad && breakOnHit) break

            }
        }

}

enum class BadIPDetectionResult(
    val isBad: Boolean,
    val typeName: String
) {

    GENERAL_BAD(true, "bad ip"),

    VPN(true, "vpn"),
    PROXY(true, "proxy"),
    TOR(true, "tor network"),
    HOSTING(true, "hosting"),

    GOOD(false, "valid ip"),
    ERROR(false, "error"),
    LIMIT(false, "limit");

}

abstract class BadIPDetectionService(
    val name: String
) {

    protected abstract fun requestString(ip: String): String

    protected abstract fun interpreteResult(result: JSONObject): BadIPDetectionResult

    fun isBad(ip: String): BadIPDetectionResult {
        val response = khttp.get(requestString(ip))
        if (response.statusCode == 429)
            return BadIPDetectionResult.LIMIT
        else {

            val result = try {
                response.jsonObject
            } catch (exc: JSONException) {
                null
            } ?: return BadIPDetectionResult.ERROR

            return try {
                interpreteResult(result)
            } catch (exc: Exception) {
                return BadIPDetectionResult.ERROR
            }

        }
    }

}