The following command illustrates how to use commands, subcommands, arguments and the command context.

```kotlin
command("gaming") {
    literal("set") {
        argument("state", BoolArgumentType.bool()) {
            simpleSuggests { listOf(true, false) }
            simpleExecutes {
                if (it.getArgument("state"))
                    it.source.bukkitSender.sendMessage("yoo gaming has been activated")
                else {
                    it.source.player.kill()
                    it.source.player.sendText("gaming disabled") { color = KColors.INDIANRED }
                }
            }
        }
    }
}
```
