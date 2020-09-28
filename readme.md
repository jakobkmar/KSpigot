# KSpigot

## Dependency

Latest version: <br>
[ ![Download](https://api.bintray.com/packages/bluefireoly/KSpigot/KSpigot/images/download.svg) ](https://bintray.com/bluefireoly/KSpigot/KSpigot/_latestVersion)

<details>
<summary><b>Gradle (Kotlin-Script)</b></summary>
<p>

**The repository:**
```kotlin
jcenter()
```

**The dependency:**

```kotlin
implementation("net.axay", "KSpigot", "VERSION_HERE")
```

</p>
</details>

<details>
<summary><b>Gradle (Groovy)</b></summary>
<p>

**The repository:**
```groovy
jcenter()
```

**The dependency:**

```groovy
implementation 'net.axay:KSpigot:VERSION_HERE'
```

</p>
</details>

<details>
<summary><b>Maven</b></summary>
<p>

**The repository:**
```xml
<repository>
    <id>jcenter</id>
    <name>jcenter</name>
    <url>https://jcenter.bintray.com</url>
</repository>
```

**The dependency:**
```xml
<dependency>
	<groupId>net.axay</groupId>
	<artifactId>KSpigot</artifactId>
	<version>VERSION_HERE</version>
</dependency>
```

</p>
</details>
<br>

If you work with KSpigot, you also need Spigot as a dependency.

## About

KSpigot is a kotlin extension for the popular [spigot server software](https://spigotmc.org/) for minecraft.
KSpigot adds functionality missing in spigot and partly makes it possible to do it the kotlin way. Most of KSpigot's extensions are stable.

IMPORTANT:
Extensions marked with NMS annotations like `@NMS_GENERAL` or `@NMS_"VERSIONSTRING"` are unstable. 

Extensions marked with the `@UnsafeImplementaion` annotation do not promise to always give the correct result, but are still useful and therefore included in the project. This readme DOES NOT contain any unsafe parts of KSpigot.

## First of all

**Inherit from `KSpigot` instead of `JavaPlugin` in your main class** <br>
```kotlin
class MyPluginMain : KSpigot()
```

**Replaced methods:**
(override these instead)
```kotlin
onLoad() with load()
onEnable() with startup()
onDisable() with shutdown()
```

## Examples

### Simple runnables and schedulers:

```kotlin
plugin.async { /* short form for async tasks */ }
```
```kotlin
plugin.sync { /* sync some code to bukkits main thread */ }
```

```kotlin
kSpigot.task(
    sync = false,
    delay = 25,
    period = 20,
    howOften = 5
) { 
    // runnable code...

    // you can access the following counter variables in here
    println(counterUp) // starting from zero
    println(counterDownToOne) // starting from howOften
    println(counterDownToZero) // starting from howOften - 1
}
```
NOTE: The counters are nullable, because howOften is (when null) infinite.

#### Safe runnables

With the `kSpigot.task()` method you have the possibility to set the parameter `safe = true`. When doing this, the defined `endCallback` will be executed under any circumstances (except a major server crash). If you define `endCallback`, but do not set `safe = true` the `endCallback` will only be executed when the task ends, because the limit of `howOften` was reached.

#### Chainable runnables

This makes it possible to do resource intensive tasks asynchronous and then doing something with the result synchronous (e.g. because Spigot forces you) in a simple way.

```kotlin
kSpigot.firstAsync { 
    // do a resource intensive task
    ArrayList<Block>() // -> this will be forwarded to the next part of the chain as "it"
}.thenSync {
    // do something with the result
    it?.forEach { }
}.thenAsync { 
    // and so on...
}
```

### Inventory GUI API

Tutorial coming soon...

### Powerful builders

#### For items

```kotlin
val wand = itemStack(Material.GOLD_BLOCK) {
           
    amount = 3
    
    addEnchantment(Enchantment.KNOCKBACK, 2)
    
    meta {
    
       name = "${ChatColor.GOLD}Magic wand"
       isUnbreakable = true
    
       addLore {
           + "This wand is truly special."
           + "Try it!"
       }
    
       customModel = 1001
    
       flag(ItemFlag.HIDE_UNBREAKABLE)
    
    }
           
}
```

#### For complex chat components

```kotlin
val component = chatComponent {

    text("You got a friend request! ") {
        color = col("#4FEA40")
        isBold = true
    }
    
    text("[Accept]") { 
        color = ChatColor.WHITE
        clickEvent(ClickEvent.Action.RUN_COMMAND, "friend accept Foo")
        hoverEventText { 
            text("Click here to accept the friend request!") { color = ChatColor.RED }
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

// delete data for a given key
nbt -= "keyToDelete"

// save data to the entity
entity.nbtData = nbt

// serialization support
val serializedString = nbt.serialize()
val deserializeMethod1 = NBTData(serializedString)
val deserializeMethod2 = NBTData.deserialize(serializedString)
```

### Simple extension methods / values (with kotlin getters)

```kotlin
livingEntity.isInWater // checks if both feet and head are in water
livingEntity.isHeadInWater // checks if the head (eye location) is in water
entity.isFeetInWater // checks if the feet are in water
entity.isGroundSolid // checks if the entity stands on a solid material
damageable.kill() // instantly kills the damageable
livingEntity.heal() // sets the health to the maximum health (if given - else throws Exception)
player.feed() // sets the players food level to 20
player.saturate() // sets the players saturation level to the current max value
player.feedSaturate() // sets the players food and saturation level to 20
player.disappear(plugin) // all other players won't be able to see the player anymore
player.appear(plugin) // show the player to every other player
vector.isFinite // checks if x, y and z of the vector are all finite
playerInteractEntityEvent.interactItem // gets the item the player interacted with
prepareItemCraftEvent.isCancelled // checks if the event isCancelled
prepareItemCraftEvent.cancel() // cancels the PrepareItemCraftEvent
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

### Flexible and chainable geometry syntax
Makes complex modification of locations and vectors more intuitive. Also, you can use any type of number (`Short`, `Int`, `Long`, `Float`, `Double`) you want. You do not have to mess with different data types.

```kotlin
loc increaseX 3 reduce vec(3.0, 1.5f, 3) increaseYZ 5.7
loc + vecXY(3, 7f) - vecZ(3)
loc - vec(x = 3, z = 5.6f) * 3 * vecXZ(7, 3.1)
```

### Listeners made easy
Kotlins' language design allows you to create listeners in a very short way.

```kotlin
kSpigot.listen<PlayerMoveEvent> {
    it.player.kick("Do not move!")
}
```
NOTE:
 - This method automatically registers the listener.
 - The `listen<Event>` method returns the listener instance.

The following extension methods can be used on any listener:
```kotlin
listener.register(plugin)
listener.unregister()
```

### Structures

A structure is a set of data defining what data is in a specific area.

#### LocationArea

A LocationArea is an area between to given Locations. The max and min locations will be calculated automatically.

```kotlin
val area = LocationArea(loc1, loc2) // loc1 and loc2 do not have to be min and max
area.minLoc
area.maxLoc
area.touchedChunks // get all chunks the LocationArea "lays" in
area.isInArea(loc3, check3d = true, tolerance = 0)

area.fillBlocks.forEach { /* execute task for each block in the area*/ }
area.entities.forEach { /* execute task for each entity in the area*/ }
```

#### Loading a structure

A structure can be loaded from any given LocationArea:
```kotlin
val structure = area.loadStructure(includeBlocks = true, includeEntities = false)
```

#### Using a structure

Structures can be transformed and placed in the world.

```kotlin
// rotate the structure (angle in degrees)
structure.rotateAroundX(angle)
structure.rotateAroundY(angle)
structure.rotateAroundZ(angle)

// place the structure at the given location
structure.buildAt(loc)
```

#### Default structures

##### Circle

There are different circle types, all inherting from `Circle`.

```kotlin
val circle = MaterialCircle(radius, Material.GRASS_BLOCK)
val circle = ParticleCircle(radius, particle(Particle.HEART) { amount = 5 })
val circle = EntityCircle(radius, EntityType.COW)
```

A circle can be filled or it can only consist of its border (edge).

```kotlin
// get all circle locations
circle.fillLocations
circle.edgeLocations

// get a structure from the circle
circle.filledStructure
circle.edgeStructure
```

### IP Address API

This API allows you to easily get some data about the IP address of a player. Please note that it is not promised that this api always returns some data: _After exceeding a certain amount of request per minute, the request will return null._

```kotlin
player.ipAddressData
// or (for results in another language)
player.ipAddressData(IPAddressDataLanguage.GERMAN)
```

What kind of data is available?
```kotlin
ipData ?: return

ipData.district
ipData.city
ipData.continent
ipData.country
// and more...
```

### Particles

This part of the API makes it more intuitive to deal with particles.

```kotlin
// define the particle
val particle = particle(Particle.BLOCK_CRACK) {
    amount = 10
    offset = vec(3, 3, 3)
    extra = 0.1
    force = true
}

// spawn the particle
particle.spawnAt(loc)
particle.spawnFor(player)
```

You can also access the builder as follows (and instantly spawn the particle).
```kotlin
loc.particle(Particle.HEART) { }
player.particle(Particle.HEART) { }
```

### GamePhase API

Game phases are commonly used with minigames. This is why KSpigot provides a general way to create such a game phase system quickly.

```kotlin
val game = buildGame {

    phase(length) {

        counterMessage("The game will start in", "seconds", "second", ">>", ".")

        end {
            broadcast("The game has started.")
        }
    }

    phase(length) {

        // alternative counterMessage implementation
        counterMessage {
            ">> The special event will start in $it seconds."
        }

        start { /* do something in the beginning of the gamephase */ }
        end { /* e.g. start the event */ }

    }

}

game.begin(kSpigot)
```
Idle phases are still in development.

<br>

> Any questions? Feel free to contact me!