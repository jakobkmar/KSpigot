package net.axay.kspigot.nbt

import net.axay.kspigot.annotations.NMS_General
import net.minecraft.server.v1_16_R1.NBTTagCompound
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftEntity
import org.bukkit.craftbukkit.v1_16_R1.inventory.CraftItemStack
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack

@NMS_General
val Entity.nbtData: NBTData get() {
    val nbtTagCompound = NBTTagCompound()
    (this as CraftEntity).handle.load(nbtTagCompound)
    return NBTData(nbtTagCompound)
}

@NMS_General
val ItemStack.nbtData: NBTData get() = NBTData(CraftItemStack.asNMSCopy(this).tag)