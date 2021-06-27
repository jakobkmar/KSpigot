package net.axay.kspigot.utils

/**
 * Loads the value of the given field for this object.
 */
fun <T> Any.reflectField(field: String): T {
    val reflectedField = this::class.java.getDeclaredField(field)
    reflectedField.isAccessible = true
    @Suppress("UNCHECKED_CAST")
    return reflectedField.get(this) as T
}
