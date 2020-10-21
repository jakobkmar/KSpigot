@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.inventory

import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

private const val DEFAULT_PAGE = 1

class InventoryGUIData<T : ForInventory>(
    val inventoryType: InventoryType<T>,
    val title: String?,
    internal val pages: Map<Int, InventoryGUIPage<T>>,
    val transitionTo: InventoryChangeEffect?,
    val transitionFrom: InventoryChangeEffect?,
    internal val generalOnClick: ((InventoryGUIClickEvent<T>) -> Unit)?
)

abstract class InventoryGUI<T : ForInventory>(
    val data: InventoryGUIData<T>
) {

    var currentPageInt: Int = DEFAULT_PAGE; protected set
    val currentPage
        get() = getPage(currentPageInt)
            ?: throw IllegalStateException("The currentPageInt has no associated page!")

    internal abstract val bukkitInventory: Inventory

    internal var isInMove: Boolean = false

    internal abstract fun loadPageUnsafe(
        page: Int,
        offsetHorizontally: Int = 0,
        offsetVertically: Int = 0
    )

    internal abstract fun loadPageUnsafe(
        page: InventoryGUIPage<*>,
        offsetHorizontally: Int = 0,
        offsetVertically: Int = 0
    )

    internal abstract fun loadContent(
        content: Map<Int, InventoryGUISlot<*>>,
        offsetHorizontally: Int = 0,
        offsetVertically: Int = 0
    )

    /**
     * @return True, if the [inventory] belongs to this GUI.
     */
    abstract fun isThisInv(inventory: Inventory): Boolean

    /**
     * Registers this InventoryGUI.
     * (KSpigot will listen for actions in the inventory.)
     */
    @Suppress("UNCHECKED_CAST")
    fun register() = InventoryGUIHolder.register(this as InventoryGUI<ForInventory>)

    /**
     * Stops KSpigot from listening to actions in this
     * InventoryGUI anymore.
     */
    @Suppress("UNCHECKED_CAST")
    fun unregister() = InventoryGUIHolder.unregister(this as InventoryGUI<ForInventory>)

    /**
     * Loads the specified page in order to display it in the GUI.
     */
    fun loadPage(page: InventoryGUIPage<T>) = loadPageUnsafe(page)

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

class InventoryGUIShared<T : ForInventory>(
    inventoryGUIData: InventoryGUIData<T>
) : InventoryGUI<T>(inventoryGUIData) {

    override val bukkitInventory = data.inventoryType.createBukkitInv(null, data.title)

    init {
        loadPageUnsafe(DEFAULT_PAGE)
    }

    override fun isThisInv(inventory: Inventory) = inventory == bukkitInventory

    override fun loadPageUnsafe(page: Int, offsetHorizontally: Int, offsetVertically: Int) {
        data.pages[page]?.let { loadPageUnsafe(it, offsetHorizontally, offsetVertically) }
    }

    override fun loadPageUnsafe(page: InventoryGUIPage<*>, offsetHorizontally: Int, offsetVertically: Int) {

        val ifOffset = offsetHorizontally != 0 || offsetVertically != 0

        if (!ifOffset) {

            // unregister this inv from all elements on the previous page
            HashSet(currentPage.slots.values).forEach { if (it is InventoryGUIElement) it.stopUsing(this) }

            currentPageInt = page.number

        }

        loadContent(page.slots, offsetHorizontally, offsetVertically)

    }

    override fun loadContent(
        content: Map<Int, InventoryGUISlot<*>>,
        offsetHorizontally: Int,
        offsetVertically: Int
    ) {

        val ifOffset = offsetHorizontally != 0 || offsetVertically != 0

        val dimensions = data.inventoryType.dimensions

        // clear the space which will be redefined
        if (ifOffset) {
            dimensions.invSlots.forEach {
                val slotToClear = dimensions.invSlotsWithRealSlots[it.add(offsetHorizontally, offsetVertically)]
                if (slotToClear != null) bukkitInventory.clear(slotToClear)
            }
        } else bukkitInventory.clear()

        content.forEach {

            val slot = it.value
            if (slot is InventoryGUIElement) {

                if (ifOffset) {
                    val invSlot = InventorySlot.fromRealSlot(it.key, dimensions)
                    if (invSlot != null) {
                        val offsetSlot = invSlot.add(offsetHorizontally, offsetVertically).realSlotIn(dimensions)
                        if (offsetSlot != null) bukkitInventory.setItem(offsetSlot, slot.getItemStack(offsetSlot))
                    }
                } else {
                    bukkitInventory.setItem(it.key, slot.getItemStack(it.key))
                    slot.startUsing(this)
                }

            }

        }

    }

    override operator fun set(slot: InventorySlotCompound<T>, value: ItemStack) {
        slot.realSlotsWithInvType(data.inventoryType).forEach {
            bukkitInventory.setItem(it, value)
        }
    }

}