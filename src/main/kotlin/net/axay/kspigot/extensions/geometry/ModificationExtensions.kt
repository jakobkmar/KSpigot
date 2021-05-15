@file:Suppress("unused")

package net.axay.kspigot.extensions.geometry

import org.bukkit.Location
import org.bukkit.util.Vector

/*
 * LOCATION
 */

// INCREASE
// all
infix fun Location.increase(distance: Number) = add(distance, distance, distance)

// single
infix fun Location.increaseX(distance: Number) = add(distance, 0.0, 0.0)
infix fun Location.increaseY(distance: Number) = add(0.0, distance, 0.0)
infix fun Location.increaseZ(distance: Number) = add(0.0, 0.0, distance)

// pair
infix fun Location.increaseXY(distance: Number) = add(distance, distance, 0.0)
infix fun Location.increaseYZ(distance: Number) = add(0.0, distance, distance)
infix fun Location.increaseXZ(distance: Number) = add(distance, 0.0, distance)

// REDUCE
// all
infix fun Location.reduce(distance: Number) = subtract(distance, distance, distance)

// single
infix fun Location.reduceX(distance: Number) = subtract(distance, 0.0, 0.0)
infix fun Location.reduceY(distance: Number) = subtract(0.0, distance, 0.0)
infix fun Location.reduceZ(distance: Number) = subtract(0.0, 0.0, distance)

// pair
infix fun Location.reduceXY(distance: Number) = subtract(distance, distance, 0.0)
infix fun Location.reduceYZ(distance: Number) = subtract(0.0, distance, distance)
infix fun Location.reduceXZ(distance: Number) = subtract(distance, 0.0, distance)

// extensions
fun Location.add(x: Number, y: Number, z: Number) = add(x.toDouble(), y.toDouble(), z.toDouble())
fun Location.subtract(x: Number, y: Number, z: Number) = subtract(x.toDouble(), y.toDouble(), z.toDouble())
val Location.blockLoc: Location get() = Location(world, blockX.toDouble(), blockY.toDouble(), blockZ.toDouble())
infix fun Location.relationTo(loc: Location) = this.subtract(loc).toSimple()

// operator functions
// immutable
operator fun Location.plus(vec: Vector) = clone().add(vec)
operator fun Location.minus(vec: Vector) = clone().subtract(vec)
operator fun Location.plus(loc: Location) = clone().add(loc)
operator fun Location.minus(loc: Location) = clone().subtract(loc)
operator fun Location.plus(loc: SimpleLocation3D) = clone().add(loc.x, loc.y, loc.z)
operator fun Location.minus(loc: SimpleLocation3D) = clone().subtract(loc.x, loc.y, loc.z)

// mutable
operator fun Location.plusAssign(vec: Vector) {
    add(vec)
}

operator fun Location.minusAssign(vec: Vector) {
    subtract(vec)
}

operator fun Location.plusAssign(loc: Location) {
    add(loc)
}

operator fun Location.minusAssign(loc: Location) {
    subtract(loc)
}

operator fun Location.plusAssign(loc: SimpleLocation3D) {
    add(loc.x, loc.y, loc.z)
}

operator fun Location.minusAssign(loc: SimpleLocation3D) {
    subtract(loc.x, loc.y, loc.z)
}

// mutable with return
infix fun Location.increase(vec: Vector) = add(vec)
infix fun Location.reduce(vec: Vector) = subtract(vec)
infix fun Location.increase(loc: Location) = add(loc)
infix fun Location.reduce(loc: Location) = subtract(loc)
infix fun Location.increase(loc: SimpleLocation3D) = add(loc.x, loc.y, loc.z)
infix fun Location.reduce(loc: SimpleLocation3D) = subtract(loc.x, loc.y, loc.z)

/*
 * VECTOR
 */

val Vector.isFinite: Boolean get() = x.isFinite() && y.isFinite() && z.isFinite()

// fast construct
fun vec(x: Number = 0.0, y: Number = 0.0, z: Number = 0.0) = Vector(x.toDouble(), y.toDouble(), z.toDouble())
fun vecXY(x: Number, y: Number) = vec(x, y)
fun vecXZ(x: Number, z: Number) = vec(x, z = z)
fun vecYZ(y: Number, z: Number) = vec(y = y, z = z)
fun vecX(x: Number) = vec(x)
fun vecY(y: Number) = vec(y = y)
fun vecZ(z: Number) = vec(z = z)

// operator functions
// immutable
operator fun Vector.plus(vec: Vector) = clone().add(vec)
operator fun Vector.minus(vec: Vector) = clone().subtract(vec)
operator fun Vector.times(vec: Vector) = clone().multiply(vec)
operator fun Vector.times(num: Number) = clone().multiply(num.toDouble())

// mutable
operator fun Vector.plusAssign(vec: Vector) {
    add(vec)
}

operator fun Vector.minusAssign(vec: Vector) {
    subtract(vec)
}

operator fun Vector.timesAssign(vec: Vector) {
    multiply(vec)
}

operator fun Vector.timesAssign(num: Number) {
    multiply(num.toDouble())
}

// mutable with return
infix fun Vector.increase(vec: Vector) = add(vec)
infix fun Vector.reduce(vec: Vector) = subtract(vec)
infix fun Vector.multiply(vec: Vector) = multiply(vec)
infix fun Vector.multiply(num: Number) = multiply(num.toDouble())
