Configuring the Java version is nothing specific to KSpigot, it should always be done. It is listed in this guide
anyways, because a lot of beginners forget to do this - and then get confused about not being able to use inline
functions.

**Java 16** is the minimum required Java version by Minecraft, therefore KSpigot requires it as well.

You can configure the Java version using Gradle:

```kotlin
// set the Java version you are using, Java 16 is the minimum required version for Minecraft

tasks {
    compileJava {
        options.release.set(16)
    }
    compileKotlin {
        kotlinOptions.jvmTarget = "16"
    }
}
```
