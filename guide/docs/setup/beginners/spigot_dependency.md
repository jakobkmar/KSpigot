# Add the Spigot dependency

You have two options:

- **A** add just the Spigot API, if you wish to have a stable API which is built for users
- **B** use the regular Spigot dependency which contains the whole Minecraft server code (often called "nms" (net.minecraft.server)), as well as the underlying CraftBukkit code - this option gives you a lot more possibilities, but it can also be dangerous

### **A** Just the Spigot API

KSpigot is an extension for Spigot, you still need the regular Spigot dependency.

Add the Spigot Maven repository to your `repositories` scope:

```kotlin
repositories {
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots")
}
```

Add the Spigot API dependency to your `dependencies` scope:

```kotlin
dependencies {
    compileOnly("org.spigotmc:spigot-api:1.17-R0.1-SNAPSHOT")
}
```

Replace the given version at the end of the dependency notation with the version you want to use. [See all versions](https://hub.spigotmc.org/nexus/content/repositories/snapshots/org/spigotmc/spigot-api/) in the Spigot Maven repository.

### **B** The regular Spigot dependency

Download the [BuildTools](https://hub.spigotmc.org/jenkins/job/BuildTools/) to a separate directory, navigate to this directory using your terminal and run `java -jar BuildTools.jar --rev MINECRAFT_VERSION` in order to generate the .jar file of the Spigot server. This file will be installed to your Maven Local repository automatically, so you can add it as dependency to your project. Additionally, you can copy it to anywhere else and use it to create a server. (More information can be found in the [Spigot Wiki](https://www.spigotmc.org/wiki/buildtools/))

Add your Maven Local repository to your `repositories` scope:

```kotlin
repositories {
    mavenLocal()
}
```

Add the regular Spigot dependency to your `dependencies` scope:

```kotlin
dependencies {
    compileOnly("org.spigotmc:spigot:1.17-R0.1-SNAPSHOT")
}
```

Make sure that the version number before `-R0.1-SNAPSHOT` matches the version you have just built using the BuildTools.