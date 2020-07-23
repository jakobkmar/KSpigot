package net.axay.kspigot.nbt

import net.axay.kspigot.annotations.NMS_General
import net.minecraft.server.v1_16_R1.*

@NMS_General
class NBTData {

    val nbtTagCompound: NBTTagCompound

    constructor(nbtTagCompound: NBTTagCompound?) {
        this.nbtTagCompound = nbtTagCompound ?: NBTTagCompound()
    }

    constructor(nbtString: String) : this(MojangsonParser.parse(nbtString))

    fun serialize() = nbtTagCompound.toString()

    /**
     * This method gets the value
     * at the given [key]. The returned type
     * must be specified.
     * The returned value is null, if it
     * was not possible to find any value at
     * the specified location, or if the type
     * is not the one which was specified.
     */
    operator fun <T> get(key: String): T? {
        val value = nbtTagCompound.get(key)
        return if (value != null) {
            val loadedValue = when (value) {
                is NBTTagCompound -> NBTData(value)
                is NBTTagByte -> value.asByte()
                is NBTTagByteArray -> value.bytes
                is NBTTagDouble -> value.asDouble()
                is NBTTagFloat -> value.asFloat()
                is NBTTagInt -> value.asInt()
                is NBTTagIntArray -> value.ints
                is NBTTagList -> value
                is NBTTagLong -> value.asLong()
                is NBTTagLongArray -> value.longs
                is NBTTagShort -> value.asShort()
                is NBTTagString -> value.asString()
                else -> null
            }
            return loadedValue as? T
        } else null
    }

    /**
     * This method sets some [value]
     * at the position of the given [key].
     */
    operator fun <T> set(key: String, value: T): Boolean {
        when (value) {
            is NBTData -> nbtTagCompound.set(key, value.nbtTagCompound)
            is Byte -> nbtTagCompound.setByte(key, value)
            is ByteArray -> nbtTagCompound.setByteArray(key, value)
            is Double -> nbtTagCompound.setDouble(key, value)
            is Float -> nbtTagCompound.setFloat(key, value)
            is Int -> nbtTagCompound.setInt(key, value)
            is IntArray -> nbtTagCompound.setIntArray(key, value)
            is List<*> -> nbtTagCompound.set(key, value as NBTList<*>)
            is Long -> nbtTagCompound.setLong(key, value)
            is LongArray -> nbtTagCompound.set(key, NBTTagLongArray(value))
            is Short -> nbtTagCompound.setShort(key, value)
            is String -> nbtTagCompound.setString(key, value)
            else -> return false
        }
        return true
    }

    companion object {

        fun deserialize(nbtString: String) = NBTData(nbtString)

    }

}