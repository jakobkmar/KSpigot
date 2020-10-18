@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.inventory

import net.axay.kspigot.event.listen
import org.bukkit.entity.HumanEntity
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

// EXTENSIONS

fun HumanEntity.openGUI(gui: InventoryGUI<*>, page: Int? = null): InventoryView? {

    closeInventory()

    if (page != null)
        gui.loadPageUnsafe(page)

    return openInventory(gui.bukkitInventory)

}

// GUI HOLDER

object InventoryGUIHolder : AutoCloseable {

    private val registered = HashSet<InventoryGUI<ForInventory>>()

    fun register(inventoryGUI: InventoryGUI<ForInventory>) {
        registered.add(inventoryGUI)
    }

    fun unregister(inventoryGUI: InventoryGUI<ForInventory>) {
        registered.remove(inventoryGUI)
    }

    init {

        listen<InventoryClickEvent> {

            val clickedInv = it.clickedInventory ?: return@listen

            val inv = registered.find { search -> search.isThisInv(clickedInv) } ?: return@listen
            val invPage = inv.currentPageInt

            val slot = inv.data.pages[invPage]?.slots?.get(it.slot)
            if (slot != null)
                slot.onClick(InventoryGUIClickEvent(it, inv))
            else
                it.isCancelled = true

        }
    }

    override fun close() {
        registered.forEach { inv -> inv.bukkitInventory.viewers.forEach { it.closeInventory() } }
        registered.clear()
    }

}

// EVENT

class InventoryGUIClickEvent<T : ForInventory>(
    val bukkitEvent: InventoryClickEvent,
    val gui: InventoryGUI<T>
)

/*
 * INVENTORY GUI
 */

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

    internal abstract fun loadPageUnsafe(
        page: InventoryGUIPage<*>,
        offsetHorizontally: Int = 0,
        offsetVertically: Int = 0
    )

    internal abstract fun loadPageUnsafe(page: Int, offsetHorizontally: Int = 0, offsetVertically: Int = 0)

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

    override fun loadPageUnsafe(page: InventoryGUIPage<*>, offsetHorizontally: Int, offsetVertically: Int) {

        val ifOffset = offsetHorizontally != 0 || offsetVertically != 0

        if (!ifOffset)
            currentPageInt = page.number

        page.slots.let { slots ->

            val dimensions = data.inventoryType.dimensions

            if (ifOffset) {
                dimensions.invSlots.forEach {
                    dimensions.invSlotsWithRealSlots[it.add(offsetHorizontally, offsetVertically)]?.let { slotToClear ->
                        if (dimensions.realSlots.contains(slotToClear))
                            bukkitInventory.clear(slotToClear)
                    }
                }
            } else {
                bukkitInventory.clear()
            }

            slots.forEach {
                val slot = it.value
                if (slot is InventoryGUIElement) {

                    if (ifOffset) {
                        val invSlot = InventorySlot.fromRealSlot(it.key, dimensions)
                        if (invSlot != null) {
                            val offsetSlot = invSlot.add(offsetHorizontally, offsetVertically).realSlotIn(dimensions)
                            if (offsetSlot != null)
                                bukkitInventory.setItem(offsetSlot, slot.inventoryGUIElementData.itemStack)
                        }
                    } else {
                        bukkitInventory.setItem(it.key, slot.inventoryGUIElementData.itemStack)
                    }

                }

            }

        }

    }

    override fun loadPageUnsafe(page: Int, offsetHorizontally: Int, offsetVertically: Int) {
        data.pages[page]?.let { loadPageUnsafe(it, offsetHorizontally, offsetVertically) }
    }

    override operator fun set(slot: InventorySlotCompound<T>, value: ItemStack) {
        slot.realSlotsWithInvType(data.inventoryType).forEach {
            bukkitInventory.setItem(it, value)
        }
    }

}

class InventoryGUIPage<T : ForInventory>(
    val number: Int,
    internal val slots: Map<Int, InventoryGUISlot<T>>,
    val transitionTo: PageChangeEffect?,
    val transitionFrom: PageChangeEffect?
)