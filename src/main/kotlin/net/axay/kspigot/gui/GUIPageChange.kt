package net.axay.kspigot.gui

import net.axay.kspigot.runnables.task

abstract class GUIPageChangeCalculator {

    abstract fun calculateNewPage(currentPage: Int, pages: Collection<Int>): Int?

    object GUIPreviousPageCalculator : GUIPageChangeCalculator() {
        override fun calculateNewPage(currentPage: Int, pages: Collection<Int>) =
            pages.sortedDescending().find { it < currentPage }
    }

    object GUINextPageCalculator : GUIPageChangeCalculator() {
        override fun calculateNewPage(currentPage: Int, pages: Collection<Int>) =
            pages.sorted().find { it > currentPage }
    }

    class GUIConsistentPageCalculator(private val toPage: Int) : GUIPageChangeCalculator() {
        override fun calculateNewPage(currentPage: Int, pages: Collection<Int>) = toPage
    }

}

enum class PageChangeEffect {
    INSTANT,
    SLIDE_HORIZONTALLY,
    SLIDE_VERTICALLY,
    SWIPE_HORIZONTALLY,
    SWIPE_VERTICALLY,
}

enum class InventoryChangeEffect(
    val effect: PageChangeEffect
) {
    INSTANT(PageChangeEffect.INSTANT)
}

internal fun GUI<*>.changePage(
    effect: PageChangeEffect,
    fromPage: GUIPage<*>,
    toPage: GUIPage<*>
) {

    val fromPageInt = fromPage.number
    val toPageInt = toPage.number

    when (effect) {

        PageChangeEffect.INSTANT -> loadPageUnsafe(toPage)

        PageChangeEffect.SLIDE_HORIZONTALLY -> {

            val width = data.guiType.dimensions.width

            changePageEffect(fromPageInt, toPageInt, width) { currentOffset, ifInverted ->
                if (ifInverted) {
                    loadPageUnsafe(fromPage, offsetHorizontally = currentOffset)
                    loadPageUnsafe(toPage, offsetHorizontally = -(width - currentOffset))
                } else {
                    loadPageUnsafe(fromPage, offsetHorizontally = -currentOffset)
                    loadPageUnsafe(toPage, offsetHorizontally = width - currentOffset)
                }
            }

        }

        PageChangeEffect.SLIDE_VERTICALLY -> {

            val height = data.guiType.dimensions.height

            changePageEffect(fromPageInt, toPageInt, height) { currentOffset, ifInverted ->
                if (ifInverted) {
                    loadPageUnsafe(fromPage, offsetVertically = currentOffset)
                    loadPageUnsafe(toPage, offsetVertically = -(height - currentOffset))
                } else {
                    loadPageUnsafe(fromPage, offsetVertically = -currentOffset)
                    loadPageUnsafe(toPage, offsetVertically = height - currentOffset)
                }
            }

        }

        PageChangeEffect.SWIPE_HORIZONTALLY -> {

            val width = data.guiType.dimensions.width

            changePageEffect(fromPageInt, toPageInt, width) { currentOffset, ifInverted ->
                if (ifInverted) {
                    loadPageUnsafe(toPage, offsetHorizontally = -(width - currentOffset))
                } else {
                    loadPageUnsafe(toPage, offsetHorizontally = width - currentOffset)
                }
            }

        }

        PageChangeEffect.SWIPE_VERTICALLY -> {

            val height = data.guiType.dimensions.height

            changePageEffect(fromPageInt, toPageInt, height) { currentOffset, ifInverted ->
                if (ifInverted) {
                    loadPageUnsafe(toPage, offsetVertically = -(height - currentOffset))
                } else {
                    loadPageUnsafe(toPage, offsetVertically = height - currentOffset)
                }
            }

        }

    }
}

internal fun GUI<*>.changeGUI(
    effect: InventoryChangeEffect,
    fromPage: GUIPage<*>,
    toPage: GUIPage<*>
) = changePage(effect.effect, fromPage, toPage)

private inline fun changePageEffect(
    fromPage: Int,
    toPage: Int,
    doFor: Int,
    crossinline effect: (currentOffset: Int, ifInverted: Boolean) -> Unit,
) {

    val ifInverted = fromPage >= toPage

    var currentOffset = 1
    task(
        sync = true,
        period = 1,
        howOften = doFor.toLong()
    ) {

        effect.invoke(currentOffset, ifInverted)

        currentOffset++

    }

}