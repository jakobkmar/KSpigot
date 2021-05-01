## Create an itemStack

Spigots ItemBuilder is often painful to use and confusing, especially for beginners. 

With KSpigot itemBuilders you have an easy to use ItemBuilder and you don´t have to worry about things like itemMeta again.


### Create a simple item
```kotlin
val sword = itemStack(Material.GOLDEN_SWORD) {
    amount = 3
    addEnchantment(Enchantment.KNOCKBACK, 2)
    meta {
        name = "${KColors.GOLD}Magic Sword"
        isUnbreakable = true
        addLore {
            + "This sword is truly special."
            + "Try it!"
        }
        customModel = 1001
        flag(ItemFlag.HIDE_UNBREAKABLE)
    }
} 
```
### This results in following item

![](img/sword.png)
