# KSpigot

## About

KSpigot is a kotlin extension for the popular [spigot server software](https://spigotmc.org/) for minecraft.
KSpigot adds functionality missing in spigot and partly makes it possible to do it the kotlin way. Most of KSpigot's extensions are stable.

IMPORTANT:
Extensions marked with NMS annotations like `@NMS_GENERAL` or `@NMS_"VERSIONSTRING"` are unstable. 

Extensions marked with the `@UnsafeImplementaion` annotation do not promise to always give the correct result, but are still useful and therefore included in the project. This readme DOES NOT contain any unsafe parts of KSpigot.

## First of all

**Create an instance of KSpigot (the constructor requires your plugin instance)** <br>
Do NOT create multiple instances!
```kotlin
val kSpigot = KSpigot(plugin)
```

**Inside of your `onDisable()` method, call:**
```kotlin
kSpigot.shutdown()
```

## Examples

### Simple runnables and schedulers:

```kotlin
bukkitAsync(kSpigot) { /* short form for async schedulers */ }
```
```kotlin
bukkitSync(kSpigot) { /* sync some code to bukkits main thread */ }
```

```kotlin
bukkitRunnable(
    kSpigot,
    sync = false,
    howoften = 5,
    delay = 25,
    period = 20
) { /* runnable code */ }
```

### Powerful builders

#### For items

```kotlin
val wand = KSpigotItems.buildItem(Material.GOLD_BLOCK) {
    
    amount = 3

    addEnchantment(Enchantment.KNOCKBACK, 2)

    itemMeta {

        displayName = "${ChatColor.GOLD}Magic wand"
        unbreakable = true

        addLore {
            + "This wand is truly special."
            + "Try it!"
        }

        customModelData = 1001

        flag(ItemFlag.HIDE_UNBREAKABLE)

    }
    
}
```

#### For complex chat components

```kotlin
KSpigotChat.buildComponent {

    text {
        text = "You got a friend request! "
        color = ChatColor.of("#4FEA40")
        bold = true
    }
    
    text { 
        text = "[Accept]"
        color = ChatColor.WHITE
        clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "friend accept Foo")
        hoverEventText { 
            text { text = "Click here to accept the friend request!"; color = ChatColor.RED }
        }
    }

}
```
You can also access the builder by calling
```kotlin
commandSender.sendMessage { /* same in here as above */ }
```

#### And more

A lot of additional things are also suitable for builders. Just like fireworks etc...

### NBTData support
Typesafe and consistent

```kotlin
// load nbt
val nbt = entity.nbtData

// retrieve data via keys
val health = nbt["hearts", NBTDataType.INT]

// set data for a given key
nbt["custom", NBTDataType.DOUBLE] = 3.3

// save data to the entity
entity.nbtData = nbt

// serialization support
val serializedString = nbt.serialize()
val deserializeMethod1 = NBTData(serializedString)
val deserializeMethod2 = NBTData.deserialize(serializedString)
```

### Simple extension methods / values (with kotlin getters)

```kotlin
entity.isGroundSolid
entity.isInWater

vector.isFinite

playerInteractEntityEvent.interactItem
```

### Direction API
Handles the hassle of struggling with direction angles for you.

```kotlin
val cardinal = CardinalDirection.fromLocation(loc) // NORTH, EAST, SOUTH, WEST
val vertical = VerticalDirection.fromLocation(loc) // UP, DOWN, STRAIGHT

// convert to BlockFace
val blockFace = cardinal.facing
```

### CustomItemIdentifiers
You want to mess with resourcepacks and extend your possibilities? <br>
Spigot is lacking a representation of custom items (via custom model data). This is what the data class `CustomItemIdentifier` is for!

```kotlin
val identifier = CustomItemIdentifier(itemStack)
// or
val identifier = CustomItemIdentifier(1001, Material.IRON_NUGGET)

// get an itemstack with the custom model data applied
val stack = identifier.itemStack
```

> Any questions? Feel free to contact me!