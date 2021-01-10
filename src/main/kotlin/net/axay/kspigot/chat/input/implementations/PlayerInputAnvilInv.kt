package net.axay.kspigot.chat.input.implementations

import net.axay.kspigot.chat.KColors
import net.axay.kspigot.chat.input.PlayerInput
import net.axay.kspigot.chat.input.PlayerInputResult
import net.axay.kspigot.event.listen
import net.axay.kspigot.extensions.bukkit.closeForViewers
import net.axay.kspigot.items.itemStack
import net.axay.kspigot.items.meta
import net.axay.kspigot.main.KSpigotMainInstance
import net.wesjd.anvilgui.AnvilGUI
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent

internal class PlayerInputAnvilInv(
    player: Player,
    callback: (PlayerInputResult<String>) -> Unit,
    timeoutSeconds: Int,
    invTitle: String,
    startText: String,
    renameItemDescription: List<String>
) : PlayerInput<String>(player, callback, timeoutSeconds) {

    private val anvilInv =
        AnvilGUI.Builder().plugin(KSpigotMainInstance)
            .onClose { onReceive(null) }
            .onComplete { _, text ->
                return@onComplete if (text.isNotEmpty()) {
                    onReceive(text)
                    AnvilGUI.Response.close()
                } else
                    AnvilGUI.Response.text("Type here...")
            }
            .title("${KColors.ORANGERED}$invTitle")
            .itemLeft(
                itemStack(Material.PAPER) {
                    meta {
                        lore = renameItemDescription.map { "${KColors.INDIANRED}$it" }
                    }
                }
            )
            .text("${KColors.ORANGERED}$startText")
            .open(player)

    override val inputListeners = listOf(
        listen<InventoryClickEvent> {
            if (it.clickedInventory == anvilInv.inventory)
                it.isCancelled = true
        }
    )

    override fun onTimeout() {
        anvilInv.inventory.closeForViewers()
    }

}