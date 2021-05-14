## Create an itemStack

The default way of building complex `ItemStack`s using just Spigot is often painful to use and confusing for beginners. 

With KSpigot's item builder you have an easy to use builder DSL. Additionally, it provides an easy way to update the `ItemMeta` of an `ItemStack`, which is often needed.

### Example use of the item builder

```kotlin
val sword = itemStack(Material.GOLDEN_SWORD) {
    amount = 3
    addEnchantment(Enchantment.KNOCKBACK, 2)
    meta {
        name = "${KColors.GOLD}Magic Sword"
        isUnbreakable = true
        addLore {
            +"This sword is truly special."
            +"Try it!"
        }
        customModel = 1001
        flag(ItemFlag.HIDE_UNBREAKABLE)
    }
} 
```
### This creates the following item

![](img/sword.png)
