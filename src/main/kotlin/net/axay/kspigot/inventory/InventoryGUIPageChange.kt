package net.axay.kspigot.inventory

import net.axay.kspigot.main.KSpigot
import net.axay.kspigot.runnables.task

abstract class InventoryGUIPageChangeCalculator {

    abstract fun calculateNewPage(currentPage: Int, pages: Collection<Int>): Int?

    object InventoryGUIPreviousPageCalculator : InventoryGUIPageChangeCalculator() {
        override fun calculateNewPage(currentPage: Int, pages: Collection<Int>)
                = pages.sortedDescending().find { it < currentPage }
    }

    object InventoryGUINextPageCalculator : InventoryGUIPageChangeCalculator() {
        override fun calculateNewPage(currentPage: Int, pages: Collection<Int>)
                = pages.sorted().find { it > currentPage }
    }

    class InventoryGUIConsistentPageCalculator(private val toPage: Int) : InventoryGUIPageChangeCalculator() {
        override fun calculateNewPage(currentPage: Int, pages: Collection<Int>) = toPage
    }

}

enum class InventoryGUIPageChangeEffect {
    INSTANT,
    SLIDE_HORIZONTALLY,
    SLIDE_VERTICALLY,
    SWIPE_HORIZONTALLY,
    SWIPE_VERTICALLY,
}

internal fun InventoryGUI<*>.changePage(effect: InventoryGUIPageChangeEffect, fromPage: InventoryGUIPage<*>?, toPage: InventoryGUIPage<*>?) {

    val fromPageInt = fromPage?.number ?: 0
    val toPageInt = toPage?.number ?: 0

    when (effect) {

        InventoryGUIPageChangeEffect.INSTANT -> loadPageUnsafe(toPage)

        InventoryGUIPageChangeEffect.SLIDE_HORIZONTALLY -> {

            val width = data.inventoryType.dimensions.width

            changePageEffect(data.plugin, fromPageInt, toPageInt, width) { currentOffset, ifInverted ->
                if (ifInverted) {
                    loadPageUnsafe(fromPage, offsetHorizontally = currentOffset)
                    loadPageUnsafe(toPage, offsetHorizontally = -(width - currentOffset))
                } else {
                    loadPageUnsafe(fromPage, offsetHorizontally = -currentOffset)
                    loadPageUnsafe(toPage, offsetHorizontally = width - currentOffset)
                }
            }

        }

        InventoryGUIPageChangeEffect.SLIDE_VERTICALLY -> {

            val height = data.inventoryType.dimensions.heigth

            changePageEffect(data.plugin, fromPageInt, toPageInt, height) { currentOffset, ifInverted ->
                if (ifInverted) {
                    loadPageUnsafe(fromPage, offsetVertically = currentOffset)
                    loadPageUnsafe(toPage, offsetVertically = -(height - currentOffset))
                } else {
                    loadPageUnsafe(fromPage, offsetVertically = -currentOffset)
                    loadPageUnsafe(toPage, offsetVertically = height - currentOffset)
                }
            }

        }

        InventoryGUIPageChangeEffect.SWIPE_HORIZONTALLY -> {

            val width = data.inventoryType.dimensions.width

            changePageEffect(data.plugin, fromPageInt, toPageInt, width) { currentOffset, ifInverted ->
                if (ifInverted) {
                    loadPageUnsafe(toPage, offsetHorizontally = -(width - currentOffset))
                } else {
                    loadPageUnsafe(toPage, offsetHorizontally = width - currentOffset)
                }
            }

        }

        InventoryGUIPageChangeEffect.SWIPE_VERTICALLY -> {

            val height = data.inventoryType.dimensions.heigth

            changePageEffect(data.plugin, fromPageInt, toPageInt, height) { currentOffset, ifInverted ->
                if (ifInverted) {
                    loadPageUnsafe(toPage, offsetVertically = -(height - currentOffset))
                } else {
                    loadPageUnsafe(toPage, offsetVertically = height - currentOffset)
                }
            }

        }

    }
}

private inline fun changePageEffect(
        kSpigot: KSpigot,
        fromPage: Int,
        toPage: Int,
        doFor: Int,
        crossinline effect: (currentOffset: Int, ifInverted: Boolean) -> Unit,
) {

    val ifInverted = fromPage >= toPage

    var currentOffset = 1
    kSpigot.task(
            sync = true,
            period = 1,
            howOften = doFor.toLong()
    ) {

        effect.invoke(currentOffset, ifInverted)

        currentOffset++

    }

}