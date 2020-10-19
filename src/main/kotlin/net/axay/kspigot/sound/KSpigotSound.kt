package net.axay.kspigot.sound

import net.axay.kspigot.extensions.bukkit.worldOrException
import net.axay.kspigot.languageextensions.applyIfNotNull
import org.bukkit.Location
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.entity.Player

data class KSpigotSound(
    val sound: Sound,
    var volume: Float = 1f,
    var pitch: Float = 1f,
    var category: SoundCategory? = null
) {

    /**
     * Plays the sound at the location. It
     * will be audible for everyone near it.
     */
    fun playAt(loc: Location) {
        val curCategory = category
        if (curCategory != null)
            loc.worldOrException.playSound(loc, sound, curCategory, volume, pitch)
        else
            loc.worldOrException.playSound(loc, sound, volume, pitch)
    }

    /**
     * Plays the sound at the location of the
     * player. It will be only audible for the player.
     */
    fun playFor(player: Player) {
        val curCategory = category
        if (curCategory != null)
            player.playSound(player.location, sound, curCategory, volume, pitch)
        else
            player.playSound(player.location, sound, volume, pitch)
    }

}

/**
 * Accesses the sound builder.
 * @see KSpigotSound
 */
fun sound(sound: Sound, builder: KSpigotSound.() -> Unit) = KSpigotSound(sound).apply(builder)

/**
 * Accesses the sound builder and then immediately
 * plays the sound at the given location.
 * @see KSpigotSound
 */
fun Location.sound(sound: Sound, builder: (KSpigotSound.() -> Unit)? = null) =
    KSpigotSound(sound).applyIfNotNull(builder).playAt(this)

/**
 * Accesses the sound builder and then immediately
 * plays the sound for the player.
 * @see KSpigotSound
 */
fun Player.sound(sound: Sound, builder: (KSpigotSound.() -> Unit)? = null) =
    KSpigotSound(sound).applyIfNotNull(builder).playFor(this)
