package net.axay.kspigot.gui

abstract class GUICreator<T : ForInventory> {
    abstract fun createInstance(guiData: GUIData<T>): GUI<T>
}

class SharedGUICreator<T : ForInventory> : GUICreator<T>() {
    override fun createInstance(guiData: GUIData<T>) = GUIShared(guiData)
}

class IndividualGUICreator<T : ForInventory>(
    private val resetOnClose: Boolean = true,
    private val resetOnQuit: Boolean = true
) : GUICreator<T>() {
    override fun createInstance(guiData: GUIData<T>) = GUIIndividual(guiData, resetOnClose, resetOnQuit)
}