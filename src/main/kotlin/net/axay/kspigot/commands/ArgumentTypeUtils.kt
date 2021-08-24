package net.axay.kspigot.commands

import com.mojang.brigadier.arguments.*

object ArgumentTypeUtils {
    @Suppress("UNCHECKED_CAST")
    inline fun <reified T> fromReifiedType() = when (T::class) {
        Boolean::class -> BoolArgumentType.bool()
        Int::class -> IntegerArgumentType.integer()
        Long::class -> LongArgumentType.longArg()
        Float::class -> FloatArgumentType.floatArg()
        Double::class -> DoubleArgumentType.doubleArg()
        String::class -> StringArgumentType.string()

        else -> throw IllegalArgumentException("The specified type '${T::class.qualifiedName}' does not have corresponding default argument type")
    } as ArgumentType<T>
}
