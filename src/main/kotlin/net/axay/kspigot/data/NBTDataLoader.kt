package net.axay.kspigot.data

import net.axay.kspigot.annotations.NMS_General
import net.minecraft.nbt.NBTTagCompound
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftEntity
import org.bukkit.craftbukkit.v1_18_R1.inventory.CraftItemStack
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack

@NMS_General
var Entity.nbtData: NBTData
    get() {
        val nbtTagCompound = NBTTagCompound()
        (this as CraftEntity).handle.g(nbtTagCompound)
        return NBTData(nbtTagCompound)
    }
    set(value) {
        (this as CraftEntity).handle.f(value.nbtTagCompound)
    }

@NMS_General
val ItemStack.nbtData: NBTData
    get() {
        CraftItemStack.asNMSCopy(this).let {
            return if (it.t() != null) NBTData(it.t()) else NBTData()
        }
    }
