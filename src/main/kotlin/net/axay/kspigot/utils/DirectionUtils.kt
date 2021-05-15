package net.axay.kspigot.utils

import com.google.common.base.Enums
import org.bukkit.Location
import org.bukkit.block.BlockFace

/**
 * This enum class is useful for determining
 * vertical directions (pitch).
 */
enum class VerticalDirection {
    UP, DOWN, STRAIGHT;

    val facing: BlockFace?
        get() = Enums.getIfPresent(BlockFace::class.java, this.name).orNull()

    companion object {
        fun fromLocation(location: Location): VerticalDirection {
            val pitch: Float = location.pitch
            return when {
                pitch <= -45 -> DOWN
                pitch >= 45 -> UP
                else -> STRAIGHT
            }
        }
    }
}

/**
 * This enum class is useful for determining
 * cardinal directions (yaw).
 */
enum class CardinalDirection {
    NORTH, EAST, SOUTH, WEST;

    val facing: BlockFace?
        get() = Enums.getIfPresent(BlockFace::class.java, this.name).orNull()

    companion object {
        fun fromLocation(location: Location): CardinalDirection {
            var yaw: Float = location.yaw
            if (yaw < 0) yaw += 360f
            return when {
                yaw >= 315 || yaw < 45 -> SOUTH
                yaw < 135 -> WEST
                yaw < 225 -> NORTH
                yaw < 315 -> EAST
                else -> NORTH
            }
        }
    }
}
