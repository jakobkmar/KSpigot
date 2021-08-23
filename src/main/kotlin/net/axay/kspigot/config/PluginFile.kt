package net.axay.kspigot.config

import net.axay.kspigot.main.PluginInstance
import java.io.File

class PluginFile(path: String, child: String? = null) : File(
    "${PluginInstance.dataFolder}",
    if (child == null) path else File(path, child).path
)
