package net.axay.kspigot.extensions.bukkit

import org.bukkit.util.Vector

val Vector.isFinite: Boolean get() = x.isFinite() && y.isFinite() && z.isFinite()