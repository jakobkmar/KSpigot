package net.axay.kspigot.inventory

import net.axay.kspigot.runnables.task

abstract class InventoryGUIPageChangeCalculator {

    abstract fun calculateNewPage(currentPage: Int, pages: Collection<Int>): Int?

    object InventoryGUIPreviousPageCalculator : InventoryGUIPageChangeCalculator() {
        override fun calculateNewPage(currentPage: Int, pages: Collection<Int>) = pages.sortedDescending().find { it < currentPage }
    }

    object InventoryGUINextPageCalculator : InventoryGUIPageChangeCalculator() {
        override fun calculateNewPage(currentPage: Int, pages: Collection<Int>) = pages.sorted().find { it > currentPage }
    }

    class InventoryGUIConsistentPageCalculator(private val toPage: Int) : InventoryGUIPageChangeCalculator() {
        override fun calculateNewPage(currentPage: Int, pages: Collection<Int>) = toPage
    }

}

enum class InventoryGUIPageChangeEffect {
    INSTANT, SLIDE_HORIZONTALLY
}

class InventoryGUIPageChanger(private val effect: InventoryGUIPageChangeEffect) {

    fun changePage(gui: InventoryGUI, fromPage: Int, toPage: Int) {
        when (effect) {

            InventoryGUIPageChangeEffect.SLIDE_HORIZONTALLY -> {

                val width = gui.data.inventoryGUIType.dimensions.width

                val ifInverted = fromPage >= toPage

                var currentOffset = 1
                gui.data.plugin.task(
                        sync = true,
                        period = 1,
                        howOften = width.toLong()
                ) {

                    if (ifInverted) {
                        gui.loadPage(fromPage, currentOffset)
                        gui.loadPage(toPage, - (width - currentOffset))
                    } else {
                        gui.loadPage(fromPage, -currentOffset)
                        gui.loadPage(toPage, width - currentOffset)
                    }

                    currentOffset++

                }

            }

            InventoryGUIPageChangeEffect.INSTANT -> {

                gui.loadPage(toPage)

            }

        }
    }

}