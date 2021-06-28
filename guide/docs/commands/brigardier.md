# Brigardier support

## Create a command

```kotlin
command("mycommand") {
    // the command body
}
```

## Register the command

The command will be automatically register by default, **if you are initializing it before the end of the `startup` call
to your plugin.**

Otherwise, you can register it manually using the `command.register()` function (where command is the command instance
which the `command(name)` function returns).

## Command features

???+ danger "Where to call these? How to structure these?"
    All the following features can be used inside the command body. <br>
    You can infinitely nest all these functions, resulting in complex command structures. <br>

### Execution handler

You provide your execution logic using the `executes` (with status code) or `simpleExecutes` (status code is always 1)
function.

```kotlin
simpleExecutes { context ->
    context.source.bukkitSender.sendMessage("hey gamer ;)")
}
```

???+ info 
    The execution and suggestion providers always do only apply to the location in the tree where they are
    defined.

#### The command context

You can use the command context to get the command source. You can use the source for:

- `source.bukkitSender` to get the `CommandSender`
- `source.player` ensure that a player executed the command and get that `Player`
- `source.bukkitWorld` to get the world of the executor

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

The second paramter of the argument function is the argument type. There are a lot of pre defined argument types by
brigardier. 

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
    simpleExecutes {
        val argValue = it.getArgument<String>("argumentname")
        // or using reified you can omit the type sometimes
        mapWhereTheKeysAreStrings[it.getArgument("argumentname")]
    }
}
```

### Suggestions

You can provide argument suggestions using the `simpleSuggests` function. It is **not** recommended using the default 
`suggests` function.

```kotlin
simpleSuggests { Material.values().toList() }
```

It is okay to do heavy operations inside this function. Suggestions are asynchronous. The body of the `simpleSuggests`
function is suspending, meaning you can use kotlinx.coroutines in it.
