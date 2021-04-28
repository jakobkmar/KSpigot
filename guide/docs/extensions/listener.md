## Register an event listener

### Using the `listen` function

You can register an event listener by calling the following function anywhere you want.

```kotlin
listen<EventClass> {
    doSomething()
}
```

The event instance will be passed in as `it`, but you can change this:

```kotlin
listen<PlayerMoveEvent> { moveEvent ->
    moveEvent.player.kick("Do not move!")
    broadcast("${moveEvent.player} moved :/")
}
```

The `listen` function returns the `Listener` instance, which allows you to perform operations on it later.

For example you could listen to a specific event temporarily:

```kotlin
val moveEventListener = listen<PlayerMoveEvent> {
    it.player.kick("Do not move!")
}

// e.g. unregister the listener after some time
taskRunLater(20 * 5) {
    moveEventListener.unregister()
}
```

### Register an existing `Listener` instance

There is an extension function which registers a `Listener` instance:

```kotlin
listenerInstance.register()
```

## Unregister a `Listener`

Just call `listenerInstance.unregister()`
