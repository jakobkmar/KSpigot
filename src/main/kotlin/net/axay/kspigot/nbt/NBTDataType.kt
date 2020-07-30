package net.axay.kspigot.nbt

import net.axay.kspigot.annotations.NMS_General
import net.minecraft.server.v1_16_R1.*

@NMS_General
interface NBTDataType<T> {

    fun decodeNMS(nbtBase: NBTBase): T?
    fun writeToCompound(key: String, data: T, compound: NBTTagCompound)

    companion object {

        val COMPOUND = object : NBTDataType<NBTData> {
            override fun decodeNMS(nbtBase: NBTBase) = if (nbtBase is NBTTagCompound) NBTData(nbtBase) else null
            override fun writeToCompound(key: String, data: NBTData, compound: NBTTagCompound) {
                compound.set(key, data.nbtTagCompound)
            }
        }
        val BYTE = object : NBTDataType<Byte> {
            override fun decodeNMS(nbtBase: NBTBase) = if (nbtBase is NBTTagByte) nbtBase.asByte() else null
            override fun writeToCompound(key: String, data: Byte, compound: NBTTagCompound) = compound.setByte(key, data)
        }
        val BYTE_ARRAY = object : NBTDataType<ByteArray> {
            override fun decodeNMS(nbtBase: NBTBase) = if (nbtBase is NBTTagByteArray) nbtBase.bytes else null
            override fun writeToCompound(key: String, data: ByteArray, compound: NBTTagCompound) = compound.setByteArray(key, data)
        }
        val DOUBLE = object : NBTDataType<Double> {
            override fun decodeNMS(nbtBase: NBTBase) = if (nbtBase is NBTTagDouble) nbtBase.asDouble() else null
            override fun writeToCompound(key: String, data: Double, compound: NBTTagCompound) = compound.setDouble(key, data)
        }
        val FLOAT = object : NBTDataType<Float> {
            override fun decodeNMS(nbtBase: NBTBase) = if (nbtBase is NBTTagFloat) nbtBase.asFloat() else null
            override fun writeToCompound(key: String, data: Float, compound: NBTTagCompound) = compound.setFloat(key, data)
        }
        val INT = object : NBTDataType<Int> {
            override fun decodeNMS(nbtBase: NBTBase) = if (nbtBase is NBTTagInt) nbtBase.asInt() else null
            override fun writeToCompound(key: String, data: Int, compound: NBTTagCompound) = compound.setInt(key, data)
        }
        val INT_ARRAY = object : NBTDataType<IntArray> {
            override fun decodeNMS(nbtBase: NBTBase) = if (nbtBase is NBTTagIntArray) nbtBase.ints else null
            override fun writeToCompound(key: String, data: IntArray, compound: NBTTagCompound) = compound.setIntArray(key, data)
        }
        val LIST = object : NBTDataType<List<*>> {
            override fun decodeNMS(nbtBase: NBTBase): List<*>? = if (nbtBase is List<*>) nbtBase else null
            override fun writeToCompound(key: String, data: List<*>, compound: NBTTagCompound) {
                (data as? NBTList<*>)?.let { compound.set(key, it) }
            }
        }
        val LONG = object : NBTDataType<Long> {
            override fun decodeNMS(nbtBase: NBTBase) = if (nbtBase is NBTTagLong) nbtBase.asLong() else null
            override fun writeToCompound(key: String, data: Long, compound: NBTTagCompound) = compound.setLong(key, data)
        }
        val LONG_ARRAY = object : NBTDataType<LongArray> {
            override fun decodeNMS(nbtBase: NBTBase) = if (nbtBase is NBTTagLongArray) nbtBase.longs else null
            override fun writeToCompound(key: String, data: LongArray, compound: NBTTagCompound) {
                compound.set(key, NBTTagLongArray(data))
            }
        }
        val SHORT = object : NBTDataType<Short> {
            override fun decodeNMS(nbtBase: NBTBase) = if (nbtBase is NBTTagShort) nbtBase.asShort() else null
            override fun writeToCompound(key: String, data: Short, compound: NBTTagCompound) = compound.setShort(key, data)
        }
        val STRING = object : NBTDataType<String> {
            override fun decodeNMS(nbtBase: NBTBase) = if (nbtBase is NBTTagString) nbtBase.asString() else null
            override fun writeToCompound(key: String, data: String, compound: NBTTagCompound) = compound.setString(key, data)
        }

    }

}