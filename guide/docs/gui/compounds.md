## Create a new compound

In this example, let's create a simple gui which lists all existing materials. Also, we want to send a chat message with the name of the material when the user clicks on the icon.

You can create a compound inside a page builder scope. The recommended way of doing so is to use the `createRectCompound` function.

```kotlin
page(1) {
    val compound = createRectCompound<Material>(
        Slots.RowOneSlotOne, Slots.RowSixSlotEight,
        iconGenerator = {
            ItemStack(it)
        },
        onClick = { clickEvent, element ->
            clickEvent.player.sendMessage("You clicked on: ${element.name}")
        }
    )
}
```

Notice that the generic type of the compound is `Material`. The compound type defines the type of the elements which can be added to the compound.

The `iconGenerator` defines how each compound element should be displayed. You will get the element passed as `it` and you have to return an `ItemStack`, which will be used for the icon.

???+ info
    In our example, it is very easy to create the icon, as we can easily create an `ItemStack` from a `Material`. In your scenarios, don't be scared of putting more complex logic inside the `iconGenerator`.

## Modify the content of a compound

The compound from above will be empty at first, you have to add content to it. This can be done using the `compound.addContent` function.

The content you add has to be of the type you defined earlier (in our case this is `Material`).

```kotlin
compound.addContent(Material.values().toList())
```

In order to remove content, call the `compound.removeContent` function. Let's remove air from our example compound.

```kotlin
compound.removeContent(Material.AIR)
```

## Sort the compound

You can use the `compound.sortContentBy` function to define in which order the elements of a compound should be displayed.

In our example, we could sort by the name of the material like this:

```kotlin
compound.sortContentBy { it.name }
```

## Add scroll buttons

If the content of the compound won't fit on one page of the GUI, **do not** create multiple pages, even if this is what you might think of at first.

Instead, add scroll buttons to the same page!

### Smooth scrolling

To implement smooth scrolling, create scroll buttons like this:

```kotlin
compoundScroll(
    Slots.RowOneSlotNine,
    ItemStack(Material.PAPER), compound, scrollTimes = 6
)
compoundScroll(
    Slots.RowSixSlotNine,
    ItemStack(Material.PAPER), compound, scrollTimes = 6, reverse = true
)
```

Notice that you have to pass the `compound` as a parameter. Also, be careful to set `scrollTimes` to the height of your compound (in our example this is 6, because the inventory is 6 slots high, and we use the full height).

You can use the `reverse` parameter and set it to true to create a button that scrolls backwards.

### Instant (hard) scrolling

Instant (or hard) scrolling means that no scrolling animation will be displayed.

To implement instant scrolling, leave `scrollTimes` at its default value (1) and instead modify the `scrollLines` value.
Set `scrollLines` to the amount of lines you want to instantly scroll by pressing the button.
If you want to scroll "page by page", set `scrollLines` to the height of the compound.

In our example, we could create the following button to scroll "page by page" instantly (without any scrolling animation):

```kotlin
compoundScroll(
    Slots.RowSixSlotNine,
    ItemStack(Material.PAPER), compound, scrollLines = 6
)
```
