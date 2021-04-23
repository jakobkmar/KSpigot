The entrypoint in KSpigot is a class which represents the main class of your plugin.

???+ warning "Type of the main class"
    Please note that due to a limitation of Spigot, your main class has to be a `class`, it cannot be an `object` or anything else.

## Create the main class

Create a new file at `src/main/kotlin/your/package/structure/` and call it (for example) `Manager`.

Add the following class to the file:

```kotlin
class InternalMainClass : KSpigot() {
    override fun load() { }
    override fun startup() { }
    override fun shutdown() { }
}
```

??? info "Coming from spigot?"
    When using KSpigot **do not inherit from `JavaPlugin`**, inherit from `KSpigot` instead.

    Also, please note that:
    
    - `onLoad` becomes `load`
    - `onEnable` becomes `startup`
    - `onDisable` becomes `shutdown`

### Make it globally available

As noted above you cannot use `object` for the main class. Fortunately, due to the fact that there should always only exist one instance of your main class, you can provide it globally by building the main class as follows:

```kotlin
class InternalMainClass : KSpigot() {
    companion object {
        lateinit var INSTANCE: InternalMainClass; private set
    }

    override fun load() {
        INSTANCE = this
    }

    override fun startup() { }
    
    override fun shutdown() { }
}

val Manager by lazy { InternalMainClass.INSTANCE }
```
