package net.axay.kspigot.inventory

import net.axay.kspigot.main.KSpigot
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
    INSTANT,
    SLIDE_HORIZONTALLY,
    SLIDE_VERTICALLY,
    SWIPE_HORIZONTALLY,
    SWIPE_VERTICALLY,
}

class InventoryGUIPageChanger(private val effect: InventoryGUIPageChangeEffect) {

    fun changePage(gui: InventoryGUI, fromPage: Int, toPage: Int) {
        when (effect) {

            InventoryGUIPageChangeEffect.INSTANT -> gui.loadPage(toPage)

            InventoryGUIPageChangeEffect.SLIDE_HORIZONTALLY -> {

                val width = gui.data.inventoryGUIType.dimensions.width

                changePageEffect(gui.data.plugin, fromPage, toPage, width) { currentOffset, ifInverted ->
                    if (ifInverted) {
                        gui.loadPage(fromPage, offsetVertically = currentOffset)
                        gui.loadPage(toPage, offsetVertically = -(width - currentOffset))
                    } else {
                        gui.loadPage(fromPage, offsetVertically = -currentOffset)
                        gui.loadPage(toPage, offsetVertically = width - currentOffset)
                    }
                }

            }

            InventoryGUIPageChangeEffect.SLIDE_VERTICALLY -> {

                val height = gui.data.inventoryGUIType.dimensions.heigth

                changePageEffect(gui.data.plugin, fromPage, toPage, height) { currentOffset, ifInverted ->
                    if (ifInverted) {
                        gui.loadPage(fromPage, offsetVertically = currentOffset)
                        gui.loadPage(toPage, offsetVertically = -(height - currentOffset))
                    } else {
                        gui.loadPage(fromPage, offsetVertically = -currentOffset)
                        gui.loadPage(toPage, offsetVertically = height - currentOffset)
                    }
                }

            }

            InventoryGUIPageChangeEffect.SWIPE_HORIZONTALLY -> {

                val width = gui.data.inventoryGUIType.dimensions.width

                changePageEffect(gui.data.plugin, fromPage, toPage, width) { currentOffset, ifInverted ->
                    if (ifInverted) {
                        gui.loadPage(toPage, offsetVertically = -(width - currentOffset))
                    } else {
                        gui.loadPage(toPage, offsetVertically = width - currentOffset)
                    }
                }

            }

            InventoryGUIPageChangeEffect.SWIPE_VERTICALLY -> {

                val height = gui.data.inventoryGUIType.dimensions.heigth

                changePageEffect(gui.data.plugin, fromPage, toPage, height) { currentOffset, ifInverted ->
                    if (ifInverted) {
                        gui.loadPage(toPage, offsetVertically = -(height - currentOffset))
                    } else {
                        gui.loadPage(toPage, offsetVertically = height - currentOffset)
                    }
                }

            }

        }
    }

    companion object {

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

    }

}