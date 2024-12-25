@file:Suppress("unused")

package net.axay.kspigot.data

import net.axay.kspigot.annotations.NMS
import net.minecraft.core.component.DataComponents
import net.minecraft.nbt.CompoundTag
import org.bukkit.craftbukkit.entity.CraftEntity
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack

@NMS
var Entity.nbtData: CompoundTag
    get() {
        val nbtTagCompound = CompoundTag()
        (this as CraftEntity).handle.save(nbtTagCompound)
        return nbtTagCompound
    }
    set(value) {
        (this as CraftEntity).handle.load(value)
    }

@NMS
val ItemStack.nbtData: CompoundTag
    get() = (this as? CraftItemStack)?.handle?.get(DataComponents.CUSTOM_DATA)?.copyTag() ?: CompoundTag()
