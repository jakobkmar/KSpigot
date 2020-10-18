package net.axay.kspigot.data

import net.axay.kspigot.annotations.NMS_General
import net.minecraft.server.v1_16_R2.NBTTagCompound
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftEntity
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack

@NMS_General
var Entity.nbtData: NBTData
    get() {
        val nbtTagCompound = NBTTagCompound()
        (this as CraftEntity).handle.load(nbtTagCompound)
        return NBTData(nbtTagCompound)
    }
    set(value) {
        (this as CraftEntity).handle.save(value.nbtTagCompound)
    }

@NMS_General
val ItemStack.nbtData: NBTData
    get() {
        CraftItemStack.asNMSCopy(this).let {
            return if (it.hasTag()) NBTData(it.tag) else NBTData()
        }
    }