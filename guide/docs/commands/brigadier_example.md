The following command illustrates how to use commands, subcommands, arguments and the command context.

```kotlin
command("gaming") {
    literal("set") {
        argument("state", BoolArgumentType.bool()) {
            suggestList { listOf(true, false) }
            runs {
                if (this.getArgument("state"))
                    this.player.sendMessage("yoo gaming has been activated")
                else {
                    this.player.kill()
                    this.player.sendText("gaming disabled"){ color = KColors.INDIANRED }
                }
            }
        }
    }
}
```
