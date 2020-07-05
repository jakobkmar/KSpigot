@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.utils

import org.bukkit.Color
import org.bukkit.FireworkEffect
import org.bukkit.inventory.meta.FireworkMeta

object KSpigotFirework {

    inline fun buildFireworkMeta(fireworkMeta: FireworkMeta, builder: KSpigotFireworkBuilder.() -> Unit): FireworkMeta {
        return KSpigotFireworkBuilder().apply(builder).applyTo(fireworkMeta)
    }

    fun FireworkMeta.build(builder: KSpigotFireworkBuilder.() -> Unit) = buildFireworkMeta(this, builder)

}

class KSpigotFireworkBuilder {

    val effects = ArrayList<FireworkEffect>()

    var power: Int? = null

    inline fun effect(builder: KSpigotFireworkEffectBuilder.() -> Unit) {
        effects += KSpigotFireworkEffectBuilder().apply(builder).build()
    }

    fun applyTo(fireworkMeta: FireworkMeta): FireworkMeta {

        fireworkMeta.addEffects(effects)

        power?.let { fireworkMeta.power = it }

        return fireworkMeta
    }

}

class KSpigotFireworkEffectBuilder {

    private val fireworkBuilder = FireworkEffect.builder()

    var type: FireworkEffect.Type? = null
    var trail: Boolean? = null
    var flicker: Boolean? = null

    fun fade(vararg colors: Color) {
        fireworkBuilder.withFade(*colors)
    }

    fun color(vararg colors: Color) {
        fireworkBuilder.withColor(*colors)
    }

    fun build(): FireworkEffect {

        type?.let { fireworkBuilder.with(it) }
        trail?.let { fireworkBuilder.trail(it) }
        flicker?.let { fireworkBuilder.flicker(it) }

        return fireworkBuilder.build()

    }

}