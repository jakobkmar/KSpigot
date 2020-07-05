@file:Suppress("ClassName")

package net.axay.kspigot.annotations

/**
 * This element is useful, but not
 * safe to use.
 * Its implementation does not promise
 * to always give the expected results.
 */
annotation class UnsafeImplementation

/**
 * This element uses [net.minecraft.server]
 * in some way. Because of that, it is
 * unstable and should be checked every time
 * with a version change.
 */
annotation class NMS_1_16_1