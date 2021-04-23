## The general task function

The task function is very powerful. It connects almost all kinds of Spigot tasks and even adds additional features on top.

You define its behaviour by providing the following parameters (all are optional and have default values):

| Parameter        | Description   |
| ---------------- | ------------- |
| sync | if the runnable should run sync (true) or async (false) |
| delay | the delay (in ticks) until the first execution of the task |
| period | at which interval (in ticks) the task should be repeated |
| howOften | how many times the task should be executed - null for infinite execution |
| endCallback | code that should always be executed when the runnable ends |
| safe | if the endCallback of the runnable should always be executed, even if the server shuts down or the runnable ends prematurely |
| runnable | the runnable which should be executed each repetition |

The body of task function is the `runnable` parameter. This runnable provides an instance of `KSpigotRunnable`, which inherits from `BukkitRunnable`, but adds counters on top. These counters are:

- `counterUp`
- `counterDownToOne`
- `counterDownToZero`

An example would be:

```kotlin
task(
    sync = false,
    delay = 25,
    period = 20,
    howOften = 5
) {
    println(it.counterUp) // starting from zero
    println(it.counterDownToOne) // starting from howOften
    println(it.counterDownToZero) // starting from howOften - 1
}
```
