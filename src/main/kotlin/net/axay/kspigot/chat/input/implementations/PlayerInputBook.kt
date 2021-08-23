package net.axay.kspigot.chat.input.implementations

import net.axay.kspigot.chat.input.PlayerInput
import net.axay.kspigot.chat.input.PlayerInputResult
import net.axay.kspigot.event.listen
import net.axay.kspigot.extensions.bukkit.content
import net.axay.kspigot.items.itemStack
import net.axay.kspigot.items.meta
import net.axay.kspigot.main.PluginInstance
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerEditBookEvent
import org.bukkit.inventory.meta.BookMeta
import org.bukkit.persistence.PersistentDataType

internal class PlayerInputBookComprehensive(
    player: Player,
    callback: (PlayerInputResult<String>) -> Unit,
    timeoutSeconds: Int,
) : PlayerInputBook<String>(player, callback, timeoutSeconds) {
    override fun loadBookContent(bookMeta: BookMeta) = bookMeta.content
}

internal class PlayerInputBookPaged(
    player: Player,
    callback: (PlayerInputResult<List<String>>) -> Unit,
    timeoutSeconds: Int,
) : PlayerInputBook<List<String>>(player, callback, timeoutSeconds) {
    override fun loadBookContent(bookMeta: BookMeta): List<String> = bookMeta.pages
}

internal abstract class PlayerInputBook<T>(
    player: Player,
    callback: (PlayerInputResult<T>) -> Unit,
    timeoutSeconds: Int,
) : PlayerInput<T>(player, callback, timeoutSeconds) {
    private val id = getID()

    val bookItemStack = itemStack(Material.WRITABLE_BOOK) {
        meta {
            persistentDataContainer[idKey, PersistentDataType.INTEGER] = id
        }
    }

    init {
        player.inventory.addItem(bookItemStack)
    }

    abstract fun loadBookContent(bookMeta: BookMeta): T

    override val inputListeners = listOf(
        listen<PlayerEditBookEvent> {
            val meta = it.newBookMeta
            if (meta.persistentDataContainer[idKey, PersistentDataType.INTEGER] == id) {
                onReceive(loadBookContent(meta))
                usedIDs -= id
                it.isCancelled = true
                player.inventory.removeItem(bookItemStack)
            }
        }
    )

    override fun onTimeout() {
        player.closeInventory()
        usedIDs -= id
    }

    companion object {
        val idKey = NamespacedKey(PluginInstance, "kspigot_bookinput_id")

        internal val usedIDs = ArrayList<Int>()

        fun getID(): Int {
            var returnID = (0..Int.MAX_VALUE).random()
            while (usedIDs.contains(returnID)) returnID = (0..Int.MAX_VALUE).random()
            return returnID
        }
    }
}
