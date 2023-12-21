# Brigadier support

???+ warning "Brigadier dependency for spigot-api users"
    (You only have to do the following if you are using the `spigot-api` instead of the `spigot` dependency!) <br>
    Whilst Spigot itself depends on [Brigadier](https://github.com/Mojang/brigadier#gradle) the Spigot API doesn't so in order for this feature to work you need to add Brigadier as a `compileOnly` dependency. More information on that can be found here: [https://github.com/Mojang/brigadier#gradle](https://github.com/Mojang/brigadier#gradle)

## Create a command

```kotlin
command("mycommand") {
    // the command body
}
```

???+ danger
    Do not add the command in your plugin.yml, this will interfere with Brigadier

## Register the command

The command will be automatically registered by default, **if you are initializing it before the end of the `startup` call
to your plugin.**

Otherwise, you can register it manually using the `command.register()` function (where command is the command instance
which the `command(name)` function returns).

## Command features

???+ danger "Where to call these? How to structure these?"
    All the following features can be used inside the command body. <br>
    You can infinitely nest all these functions, resulting in complex command structures. <br>

### Execution handler
You can define your execution logic using the `executes` (with a status code) or `runs` function.

#### Runs

Setting the status code explicitly:
```kotlin
runs { context ->
    context.bukkitSender.sendMessage("hey gamer ;)")
    return@runs 1
}
```

Alternatively you could use it as following:
```kotlin
runs { 
    this.sender.bukkitSender.sendMessage("hey gamer ;)")
}
```
Automatically returning the status code 1.

#### Executes

```kotlin
executes { context ->
    context.bukkitSender.sendMessage("hey gamer ;)")
    return@executes 1
}
```

???+ info 
    The execution and suggestion providers always do only apply to the location in the tree where they are
    defined.

#### The command context

You can use the command context in `executes` to get the command source. You can use the source for:

- `source.bukkitSender` to get the `CommandSender`
- `source.player` ensure that a player executed the command and get that `Player`
- `source.bukkitWorld` to get the world of the executor

If you are using `runs`, you can access these by using `sender` instead of `source`.

### Literals (subcommands)

```kotlin
literal("mysubcommand") {
    // the command body for this part of the command tree
}
```

### Arguments

```kotlin
argument("argumentname", StringArgumentType.string()) {
    // the command body (inside this body, the argument exists in the context)
}
```

The argument name will be displayed to the player. Also, it can be used to retrieve the value of the argument the
execution handler.

#### Argument type

The second parameter of the argument function is the argument type. There are a lot of pre-defined argument types by
brigadier. 

The argument types for all primitives can be accessed in the following pattern: `NameArgumentType.name()` (where name is the name of the primitive)

```kotlin
// examples:
StringArgumentType.string()
BoolArgumentType.bool()
IntegerArgumentType.integer()
```

#### Retrieve the value of an argument

The value of the argument can be retrieved from the command context.

```kotlin
argument("argumentname", StringArgumentType.string()) {
    runs {
        val argValue = this.getArgument<String>("argumentname")
        // or using reified you can omit the type sometimes
        mapWhereTheKeysAreStrings[this.getArgument("argumentname")]
    }
}
```

### Suggestions

You can provide argument suggestions using the `suggestList` function. It is **not** recommended using the default 
`suggests` function.

```kotlin
suggestList { Material.values().toList() }
```

If you want to do heavy operations inside the suggest functions, you should use `suggestListSuspending`
The body of the `suggestListSuspending` function is suspending, meaning you can use kotlinx.coroutines in it.
