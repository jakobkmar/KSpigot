@file:Suppress("unused")

package net.axay.kspigot.data

import net.axay.kspigot.annotations.NMS_General
import net.minecraft.nbt.CompoundTag
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftEntity
import org.bukkit.craftbukkit.v1_20_R3.inventory.CraftItemStack
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack

@NMS_General
var Entity.nbtData: CompoundTag
    get() {
        val nbtTagCompound = CompoundTag()
        (this as CraftEntity).handle.save(nbtTagCompound)
        return nbtTagCompound
    }
    set(value) {
        (this as CraftEntity).handle.load(value)
    }

@NMS_General
val ItemStack.nbtData: CompoundTag
    get() {
        CraftItemStack.asNMSCopy(this).let {
            return if (it.hasTag()) (it.tag ?: CompoundTag()) else CompoundTag()
        }
    }
