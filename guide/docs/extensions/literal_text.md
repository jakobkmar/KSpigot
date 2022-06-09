# Literal text API

## Create a text object

```kotlin
val text = literalText("base text") {
    // the text builder
}

// or

val text = literalText {
    // the text builder
}
```

## Add children text objects

```kotlin
literalText("base text") {
    text("children text") {
        // children text builder
    }
}
```

## Format the text

Text formatting:

```kotlin
literalText("base text") {
    bold = true
    italic = true
    underline = true
    strikethrough = true
    obfuscate = true
}
```

Color:

```kotlin
literalText("base text") {
    color = TextColor.color(255, 116, 99)
    color = KColors.INDIANRED
}
```

## Events

### Hover event

#### General hover event

```kotlin
literalText("base text") {
    hoverEvent = HoverEvent.hoverEvent(action, value)
}
```

#### Show hover text

```kotlin
literalText("base text") {
    hoverText("you hovered me") {
        // hover text builder
    }
}
```

#### Show hover Item

```kotlin
literalText("base text") {
    hoverItem(item)
}
```

#### Show hover Entity

```kotlin
literalText("base text") {
    hoverEntity(entity)
}
```

### Click event

#### General click event

```kotlin
literalText("base text") {
    clickEvent = ClickEvent.clickEvent(action, value)
}
```

#### Execute command

```kotlin
literalText("base text") {
    onClickCommand("/me hoho", onlySuggest = true)
}
```

#### Copy a String

```kotlin
literalText("base text") {
    onClickCopy("https://github.com/bluefireoly/KSpigot")
}
```

#### Open a URL

```kotlin
literalText("base text") {
    onClickOpenURL("https://github.com/bluefireoly/KSpigot")
}
```

## Special

```kotlin
literalText("base text") {
    // line break
    newLine()
    // an empty line (two line breaks)
    emptyLine()
}
```

## Add paper components

```kotlin
literalText("base text") {
    // e.g. add a TranslatableComponent
    component(Component.translatable("translation.key")) {
        // optional text body
    }
}
```

## Add legacy chat components

You can add legacy text if you want to use the old color codes for some reason.

```kotlin
literalText("base text") {
    legacyText("§cthis text is red") {
        // optional text body to format the legacy text
    }
}
```
