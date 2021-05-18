package net.axay.kspigot.config

import net.axay.kspigot.main.KSpigotMainInstance
import java.io.File

class PluginFile(path: String, child: String? = null) : File(
    "${KSpigotMainInstance.dataFolder}",
    if (child == null) path else File(path, child).path
)
