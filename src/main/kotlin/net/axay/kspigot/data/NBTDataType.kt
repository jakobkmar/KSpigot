package net.axay.kspigot.data

import net.axay.kspigot.annotations.NMS_General
import net.minecraft.nbt.*

@NMS_General
interface NBTDataType<T> {
    fun decodeNMS(nbtBase: NBTBase): T?
    fun writeToCompound(key: String, data: T, compound: NBTTagCompound)

    companion object {
        val COMPOUND = nbtDataType<NBTData, NBTTagCompound>(
            { NBTData(it) },
            { key, data, compound -> compound.a(key, data.nbtTagCompound) }
        )
        val BYTE = nbtDataType<Byte, NBTTagByte>(
            { it.a() },
            { key, data, compound -> compound.a(key, data) }
        )
        val BYTE_ARRAY = nbtDataType<ByteArray, NBTTagByteArray>(
            { it.d() },
            { key, data, compound -> compound.a(key, data) }
        )
        val DOUBLE = nbtDataType<Double, NBTTagDouble>(
            { it.i() },
            { key, data, compound -> compound.a(key, data) }
        )
        val FLOAT = nbtDataType<Float, NBTTagFloat>(
            { it.j() },
            { key, data, compound -> compound.a(key, data) }
        )
        val INT = nbtDataType<Int, NBTTagInt>(
            { it.f() },
            { key, data, compound -> compound.a(key, data) }
        )
        val INT_ARRAY = nbtDataType<IntArray, NBTTagIntArray>(
            { it.f() },
            { key, data, compound -> compound.a(key, data) }
        )
        val LONG = nbtDataType<Long, NBTTagLong>(
            { it.e() },
            { key, data, compound -> compound.a(key, data) }
        )
        val LONG_ARRAY = nbtDataType<LongArray, NBTTagLongArray>(
            { it.f() },
            { key, data, compound -> compound.a(key, NBTTagLongArray(data)) }
        )
        val SHORT = nbtDataType<Short, NBTTagShort>(
            { it.g() },
            { key, data, compound -> compound.a(key, data) }
        )
        val STRING = nbtDataType<String, NBTTagString>(
            { it.e_() },
            { key, data, compound -> compound.a(key, data) }
        )
    }
}

/**
 * @property T the JVM data type
 * @property E the NBT data type
 */
private inline fun <T, reified E> nbtDataType(
    crossinline decodeNMS: (E) -> T,
    crossinline writeToCompound: (key: String, data: T, compound: NBTTagCompound) -> Unit,
): NBTDataType<T> {
    return object : NBTDataType<T> {
        override fun decodeNMS(nbtBase: NBTBase) = if (nbtBase is E) decodeNMS.invoke(nbtBase) else null
        override fun writeToCompound(key: String, data: T, compound: NBTTagCompound) =
            writeToCompound.invoke(key, data, compound)
    }
}
