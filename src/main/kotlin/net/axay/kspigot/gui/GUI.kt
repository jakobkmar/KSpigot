@file:Suppress("MemberVisibilityCanBePrivate")

package net.axay.kspigot.gui

import net.axay.kspigot.event.listen
import net.axay.kspigot.extensions.bukkit.closeForViewers
import net.axay.kspigot.main.PluginInstance
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class GUIData<T : ForInventory>(
    val guiType: GUIType<T>,
    val title: Component,
    internal val pages: Map<Int, GUIPage<T>>,
    val defaultPage: Int,
    val transitionTo: InventoryChangeEffect?,
    val transitionFrom: InventoryChangeEffect?,
    internal val generalOnClick: ((GUIClickEvent<T>) -> Unit)?,
    internal val onClose: ((GUICloseEvent<T>) -> Unit)?
)

abstract class GUI<T : ForInventory>(
    val data: GUIData<T>,
) {
    /**
     * Returns the instance beloning to the given player.
     * If not existing, a new instance will be created.
     */
    abstract fun getInstance(player: Player): GUIInstance<T>

    /**
     * Returns all active instances of this GUI.
     */
    abstract fun getAllInstances(): Collection<GUIInstance<T>>

    /**
     * Closes this GUI for all viewers and unregisters
     * all instances.
     */
    abstract fun closeGUI()

    protected fun unregisterAndClose() {
        getAllInstances().forEach {
            it.bukkitInventory.closeForViewers()
            it.unregister()
        }
    }
}

class GUIShared<T : ForInventory>(
    guiData: GUIData<T>,
) : GUI<T>(guiData) {
    private var _singleInstance: GUIInstance<T>? = null
    val singleInstance
        get() = _singleInstance ?: GUIInstance(this, null).apply {
            _singleInstance = this
            register()
        }

    override fun getInstance(player: Player) = singleInstance

    override fun getAllInstances() = _singleInstance?.let { listOf(it) } ?: emptyList()

    override fun closeGUI() {
        unregisterAndClose()
        _singleInstance = null
    }
}

class GUIIndividual<T : ForInventory>(
    guiData: GUIData<T>,
    resetOnClose: Boolean,
    resetOnQuit: Boolean,
) : GUI<T>(guiData) {
    private val playerInstances = HashMap<Player, GUIInstance<T>>()

    override fun getInstance(player: Player) =
        playerInstances[player] ?: createInstance(player)

    override fun getAllInstances() = playerInstances.values

    private fun createInstance(player: Player) =
        GUIInstance(this, player).apply {
            playerInstances[player] = this
            register()
        }

    fun deleteInstance(player: Player) = playerInstances.remove(player)?.unregister()

    override fun closeGUI() {
        unregisterAndClose()
        playerInstances.clear()
    }

    init {
        if (resetOnClose || data.onClose != null) {
            listen<InventoryCloseEvent> {
                 if (playerInstances[it.player]?.bukkitInventory != it.inventory) return@listen

                if (data.onClose != null) {
                    data.onClose.invoke(GUICloseEvent(it, playerInstances[it.player]!!, it.player as Player))
                }

                if (resetOnClose) {
                    deleteInstance(it.player as? Player ?: return@listen)
                }
            }
        }

        if (resetOnQuit) {
            listen<PlayerQuitEvent> {
                deleteInstance(it.player)
            }
        }
    }
}

class GUIInstance<T : ForInventory>(
    val gui: GUI<T>,
    holder: Player?,
) {
    internal val bukkitInventory = gui.data.guiType.createBukkitInv(holder, gui.data.title)

    private val currentElements = HashSet<GUIElement<*>>()

    internal var isInMove: Boolean = false

    var currentPageInt: Int = gui.data.defaultPage; private set
    val currentPage
        get() = getPage(currentPageInt)
            ?: throw IllegalStateException("The currentPageInt has no associated page!")

    init {
        loadPageUnsafe(gui.data.defaultPage)
    }

    internal fun loadPageUnsafe(page: Int, offsetHorizontally: Int = 0, offsetVertically: Int = 0) {
        gui.data.pages[page]?.let { loadPageUnsafe(it, offsetHorizontally, offsetVertically) }
    }

    internal fun loadPageUnsafe(page: GUIPage<*>, offsetHorizontally: Int = 0, offsetVertically: Int = 0) {
        val ifOffset = offsetHorizontally != 0 || offsetVertically != 0

        if (!ifOffset) {
            // unregister this inv from all elements on the previous page
            currentElements.forEach { it.stopUsing(this) }
            currentElements.clear()
            // register this inv for all new elements
            HashSet(page.slots.values).forEach {
                if (it is GUIElement) {
                    currentElements += it
                    it.startUsing(this)
                }
            }

            currentPageInt = page.number
        }

        loadContent(page.slots, offsetHorizontally, offsetVertically)
    }

    internal fun loadContent(
        content: Map<Int, GUISlot<*>>,
        offsetHorizontally: Int = 0,
        offsetVertically: Int = 0,
    ) {
        val ifOffset = offsetHorizontally != 0 || offsetVertically != 0
        val dimensions = gui.data.guiType.dimensions
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

    /**
     * Registers this GUI.
     * (KSpigot will listen for actions in the inventory.)
     */
    @Suppress("UNCHECKED_CAST")
    fun register() = PluginInstance.guiHolder.register(this as GUIInstance<ForInventory>)

    /**
     * Stops KSpigot from listening to actions in this
     * GUI anymore.
     */
    fun unregister() {
        @Suppress("UNCHECKED_CAST")
        PluginInstance.guiHolder.unregister(this as GUIInstance<ForInventory>)
        // unregister this inv from all elements
        currentElements.forEach { it.stopUsing(this) }
        currentElements.clear()
    }

    /**
     * @return True, if the [inventory] belongs to this GUI.
     */
    fun isThisInv(inventory: Inventory) = inventory == bukkitInventory

    /**
     * Loads the specified page in order to display it in the GUI.
     */
    fun loadPage(page: GUIPage<T>) = loadPageUnsafe(page)

    /**
     * Temporarily sets the given item at the given slots.
     */
    operator fun set(slot: InventorySlotCompound<T>, value: ItemStack) {
        slot.realSlotsWithInvType(gui.data.guiType).forEach {
            bukkitInventory.setItem(it, value)
        }
    }

    /**
     * Searches for a page associated to the given [page] index.
     */
    fun getPage(page: Int?) = gui.data.pages[page]

    /**
     * Reloads the current page.
     */
    fun reloadCurrentPage() {
        if (!isInMove)
            loadPage(currentPage)
    }
}
