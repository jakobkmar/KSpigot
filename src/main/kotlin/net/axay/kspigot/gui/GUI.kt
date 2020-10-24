@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.gui

import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

private const val DEFAULT_PAGE = 1

class GUIData<T : ForInventory>(
        val guiType: GUIType<T>,
        val title: String?,
        internal val pages: Map<Int, GUIPage<T>>,
        val transitionTo: InventoryChangeEffect?,
        val transitionFrom: InventoryChangeEffect?,
        internal val generalOnClick: ((GUIClickEvent<T>) -> Unit)?
)

abstract class GUI<T : ForInventory>(
    val data: GUIData<T>
) {

    var currentPageInt: Int = DEFAULT_PAGE; protected set
    val currentPage
        get() = getPage(currentPageInt)
            ?: throw IllegalStateException("The currentPageInt has no associated page!")

    internal abstract val bukkitInventory: Inventory

    internal var isInMove: Boolean = false

    internal val currentElements = HashSet<GUIElement<*>>()

    internal abstract fun loadPageUnsafe(
        page: Int,
        offsetHorizontally: Int = 0,
        offsetVertically: Int = 0
    )

    internal abstract fun loadPageUnsafe(
        page: GUIPage<*>,
        offsetHorizontally: Int = 0,
        offsetVertically: Int = 0
    )

    internal abstract fun loadContent(
        content: Map<Int, GUISlot<*>>,
        offsetHorizontally: Int = 0,
        offsetVertically: Int = 0
    )

    /**
     * @return True, if the [inventory] belongs to this GUI.
     */
    abstract fun isThisInv(inventory: Inventory): Boolean

    /**
     * Registers this GUI.
     * (KSpigot will listen for actions in the inventory.)
     */
    @Suppress("UNCHECKED_CAST")
    fun register() = GUIHolder.register(this as GUI<ForInventory>)

    /**
     * Stops KSpigot from listening to actions in this
     * GUI anymore.
     */
    @Suppress("UNCHECKED_CAST")
    fun unregister() = GUIHolder.unregister(this as GUI<ForInventory>)

    /**
     * Loads the specified page in order to display it in the GUI.
     */
    fun loadPage(page: GUIPage<T>) = loadPageUnsafe(page)

    /**
     * Temporarily sets the given item at the given slots.
     */
    abstract operator fun set(slot: InventorySlotCompound<T>, value: ItemStack)

    /**
     * Searches for a page associated to the given [page] index.
     */
    fun getPage(page: Int?) = data.pages[page]

    /**
     * Reloads the current page.
     */
    fun reloadCurrentPage() {
        if (!isInMove)
            loadPage(currentPage)
    }

}

// Inventory GUI implementations

class GUIShared<T : ForInventory>(
    GUIData: GUIData<T>
) : GUI<T>(GUIData) {

    override val bukkitInventory = data.guiType.createBukkitInv(null, data.title)

    init {
        loadPageUnsafe(DEFAULT_PAGE)
    }

    override fun isThisInv(inventory: Inventory) = inventory == bukkitInventory

    override fun loadPageUnsafe(page: Int, offsetHorizontally: Int, offsetVertically: Int) {
        data.pages[page]?.let { loadPageUnsafe(it, offsetHorizontally, offsetVertically) }
    }

    override fun loadPageUnsafe(page: GUIPage<*>, offsetHorizontally: Int, offsetVertically: Int) {

        val ifOffset = offsetHorizontally != 0 || offsetVertically != 0

        if (!ifOffset) {

            // unregister this inv from all elements on the previous page
            currentElements.forEach { it.stopUsing(this) }
            currentElements.clear()

            // register this inv for all new elements
            HashSet(page.slots.values).forEach { if (it is GUIElement) {
                currentElements += it
                it.startUsing(this)
            } }

            currentPageInt = page.number

        }

        loadContent(page.slots, offsetHorizontally, offsetVertically)

    }

    override fun loadContent(
        content: Map<Int, GUISlot<*>>,
        offsetHorizontally: Int,
        offsetVertically: Int
    ) {

        val ifOffset = offsetHorizontally != 0 || offsetVertically != 0

        val dimensions = data.guiType.dimensions

        // clear the space which will be redefined
        if (ifOffset) {
            dimensions.invSlots.forEach {
                val slotToClear = dimensions.invSlotsWithRealSlots[it.add(offsetHorizontally, offsetVertically)]
                if (slotToClear != null) bukkitInventory.clear(slotToClear)
            }
        } else bukkitInventory.clear()

        // render the given content
        content.forEach {

            val slot = it.value
            if (slot is GUIElement) {

                if (ifOffset) {
                    val invSlot = InventorySlot.fromRealSlot(it.key, dimensions)
                    if (invSlot != null) {
                        val offsetSlot = invSlot.add(offsetHorizontally, offsetVertically).realSlotIn(dimensions)
                        if (offsetSlot != null) bukkitInventory.setItem(offsetSlot, slot.getItemStack(offsetSlot))
                    }
                } else bukkitInventory.setItem(it.key, slot.getItemStack(it.key))

            }

        }

    }

    override operator fun set(slot: InventorySlotCompound<T>, value: ItemStack) {
        slot.realSlotsWithInvType(data.guiType).forEach {
            bukkitInventory.setItem(it, value)
        }
    }

}