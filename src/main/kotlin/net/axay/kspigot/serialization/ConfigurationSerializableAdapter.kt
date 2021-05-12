@file:Suppress("unused")

package net.axay.kspigot.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ByteArraySerializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.bukkit.Color
import org.bukkit.Location
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.util.BoundingBox
import org.bukkit.util.Vector
import org.bukkit.util.io.BukkitObjectInputStream
import org.bukkit.util.io.BukkitObjectOutputStream
import java.io.ByteArrayOutputStream
import kotlin.reflect.KClass

object ColorSerializer : KSerializerForBukkit<Color>(Color::class)
object BoundingBoxSerializer : KSerializerForBukkit<BoundingBox>(BoundingBox::class)
object ItemMetaSerializer : KSerializerForBukkit<ItemMeta>(ItemMeta::class)
object ItemStackSerializer : KSerializerForBukkit<ItemStack>(ItemStack::class)
object LocationSerializer : KSerializerForBukkit<Location>(Location::class)
object VectorSerializer : KSerializerForBukkit<Vector>(Vector::class)
open class KSerializerForBukkit<T : ConfigurationSerializable>(
    private val kClass: KClass<T>,
) : KSerializer<T> {
    override val descriptor = ByteArraySerializer().descriptor
    override fun serialize(encoder: Encoder, value: T) {
        val bytes = ByteArrayOutputStream()
        BukkitObjectOutputStream(bytes).use {
            it.writeObject(value)
        }
        encoder.encodeSerializableValue(ByteArraySerializer(), bytes.toByteArray())
    }

    override fun deserialize(decoder: Decoder): T {
        BukkitObjectInputStream(
            decoder.decodeSerializableValue(ByteArraySerializer()).inputStream()
        ).use {
            @Suppress("UNCHECKED_CAST")
            return it.readObject() as? T
                ?: throw IllegalStateException("The object can not be deserialized to an object of the type ${kClass.simpleName}")
        }
    }
}