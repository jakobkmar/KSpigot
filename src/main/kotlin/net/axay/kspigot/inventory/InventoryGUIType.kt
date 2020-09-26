@file:Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")

package net.axay.kspigot.inventory

import org.bukkit.Bukkit
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

class InventoryGUIType<T : ForInventory>(
        val dimensions: InventoryDimensions,
        val bukkitType: InventoryType? = null
) {

    private val size = dimensions.width * dimensions.heigth

    companion object {

        val ONE_BY_NINE = InventoryGUIType<ForInventoryOneByNine>(InventoryDimensions(9, 1))
        val TWO_BY_NINE = InventoryGUIType<ForInventoryTwoByNine>(InventoryDimensions(9, 2))
        val THREE_BY_NINE = InventoryGUIType<ForInventoryThreeByNine>(InventoryDimensions(9, 3))
        val FOUR_BY_NINE = InventoryGUIType<ForInventoryFourByNine>(InventoryDimensions(9, 4))
        val FIVE_BY_NINE = InventoryGUIType<ForInventoryFiveByNine>(InventoryDimensions(9, 5))
        val SIX_BY_NINE = InventoryGUIType<ForInventorySixByNine>(InventoryDimensions(9, 6))
        val ONE_BY_FIVE = InventoryGUIType<ForInventoryOneByFive>(InventoryDimensions(5, 1), bukkitType = InventoryType.HOPPER)
        val THREE_BY_THREE = InventoryGUIType<ForInventoryThreeByThree>(InventoryDimensions(3, 3), bukkitType = InventoryType.DROPPER)

    }

    fun createBukkitInv(holder: InventoryHolder? = null, title: String? = null): Inventory {
        val realTitle = title ?: ""
        return when {
            bukkitType != null -> Bukkit.createInventory(holder, bukkitType, realTitle)
            else -> Bukkit.createInventory(holder, size, realTitle)
        }
    }

}

// INVENTORY TYPE SAFETY

interface ForInventory

interface ForInventoryOneByNine : ForInventory
interface ForInventoryTwoByNine : ForInventory
interface ForInventoryThreeByNine : ForInventory
interface ForInventoryFourByNine : ForInventory
interface ForInventoryFiveByNine : ForInventory
interface ForInventorySixByNine : ForInventory
interface ForInventoryThreeByThree : ForInventory
interface ForInventoryOneByFive : ForInventory

interface ForEveryInventory
    : ForInventoryOneByNine, ForInventoryTwoByNine, ForInventoryThreeByNine,
        ForInventoryFourByNine, ForInventoryFiveByNine, ForInventorySixByNine,
        ForInventoryThreeByThree, ForInventoryOneByFive