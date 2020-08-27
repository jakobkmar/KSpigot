package net.axay.kspigot.nbt

import net.axay.kspigot.annotations.NMS_General
import net.minecraft.server.v1_16_R1.*

/**
 * @property T the JVM data type
 */
@NMS_General
interface NBTDataType<T> {

    fun decodeNMS(nbtBase: NBTBase): T?
    fun writeToCompound(key: String, data: T, compound: NBTTagCompound)

    companion object {

        val COMPOUND = nbtDataType<NBTData, NBTTagCompound>({ NBTData(it) }, { key, data, compound -> compound.set(key, data.nbtTagCompound) })
        val BYTE = nbtDataType<Byte, NBTTagByte>({ it.asByte() }, { key, data, compound -> compound.setByte(key, data) })
        val BYTE_ARRAY = nbtDataType<ByteArray, NBTTagByteArray>({ it.bytes }, { key, data, compound -> compound.setByteArray(key, data) })
        val DOUBLE = nbtDataType<Double, NBTTagDouble>({ it.asDouble() }, { key, data, compound -> compound.setDouble(key, data) })
        val FLOAT = nbtDataType<Float, NBTTagFloat>({ it.asFloat() }, { key, data, compound -> compound.setFloat(key, data) })
        val INT = nbtDataType<Int, NBTTagInt>({ it.asInt() }, { key, data, compound -> compound.setInt(key, data) })
        val INT_ARRAY = nbtDataType<IntArray, NBTTagIntArray>({ it.ints }, { key, data, compound -> compound.setIntArray(key, data) })
        val LONG = nbtDataType<Long, NBTTagLong>({ it.asLong() }, { key, data, compound -> compound.setLong(key, data) })
        val LONG_ARRAY = nbtDataType<LongArray, NBTTagLongArray>({ it.longs }, { key, data, compound -> compound.set(key, NBTTagLongArray(data)) })
        val SHORT = nbtDataType<Short, NBTTagShort>({ it.asShort() }, { key, data, compound -> compound.setShort(key, data) })
        val STRING = nbtDataType<String, NBTTagString>({ it.asString() }, { key, data, compound -> compound.setString(key, data) })

    }

}

private inline fun <T, reified E> nbtDataType(
        crossinline decodeNMS: (E) -> T,
        crossinline writeToCompound: (key: String, data: T, compound: NBTTagCompound) -> Unit
): NBTDataType<T> {

    return object : NBTDataType<T> {

        override fun decodeNMS(nbtBase: NBTBase) = if (nbtBase is E) decodeNMS.invoke(nbtBase) else null

        override fun writeToCompound(key: String, data: T, compound: NBTTagCompound) = writeToCompound.invoke(key, data, compound)

    }

}