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
    color = col(0xFF7463)
    color = col("#FF7463")
    color = KColors.INDIANRED
}
```

## Events

### Hover event

#### General hover event

```kotlin
literalText("base text") {
    hoverEvent = HoverEvent(action, content)
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

### Click event

#### General click event

```kotlin
literalText("base text") {
    clickEvent = ClickEvent(action, value)
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

## Special

```kotlin
literalText("base text") {
    // line break
    newLine()
    // an empty line (two line breaks)
    emptyLine()
}
```

## Add bungee components

```kotlin
literalText("base text") {
    // e.g. add a TranslatableComponent
    text(TranslatableComponent("translation.key")) {
        // optional text body
    }
}
```
