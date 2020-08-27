package net.axay.kspigot.nbt

import net.axay.kspigot.annotations.NMS_General
import net.minecraft.server.v1_16_R2.MojangsonParser
import net.minecraft.server.v1_16_R2.NBTTagCompound

@NMS_General
class NBTData {

    val nbtTagCompound: NBTTagCompound

    constructor(nbtTagCompound: NBTTagCompound?) {
        this.nbtTagCompound = nbtTagCompound ?: NBTTagCompound()
    }

    constructor() {
        this.nbtTagCompound = NBTTagCompound()
    }

    constructor(nbtString: String) : this(MojangsonParser.parse(nbtString))

    fun serialize() = nbtTagCompound.toString()

    /**
     * This method gets the value
     * at the given [key]. The returned [dataType]
     * must be specified.
     * The returned value is null, if it
     * was not possible to find any value at
     * the specified location, or if the type
     * is not the one which was specified.
     */
    operator fun <T> get(key: String, dataType: NBTDataType<T>): T? {
        val value = nbtTagCompound.get(key)
        return if (value != null) {
            dataType.decodeNMS(value)
        } else null
    }

    /**
     * This method sets some [value]
     * at the position of the given [key].
     * The [dataType] of the given [value]
     * must be specified.
     */
    operator fun <T> set(key: String, dataType: NBTDataType<T>, value: T) {
        dataType.writeToCompound(key, value, nbtTagCompound)
    }

    companion object {

        fun deserialize(nbtString: String) = NBTData(nbtString)

    }

}