The following code snippets can be used in your `build.gradle.kts` file.

An example of a final configuration file [is also available](gradle_example.md).

## Prerequisites

### Java version

Make sure that you have [configured the Java version](beginners/java_version.md) correctly. 

### Add paper (via paperweight)

Add the paperweight Gradle plugin. Have a look at [the example](gradle_example.md).

## Add KSpigot

### Add the dependency

Add the following repository to your `repositories` scope:

```kotlin
repositories {
    mavenCentral()
}
```

Add the KSpigot dependency to your `dependencies` scope:

```kotlin
dependencies {
    implementation("net.axay:kspigot:VERSION")
}
```

Replace `VERSION` with the version you want to use. 

Latest version (without the `v`): <br>
![latest KSpigot GitHub release](https://img.shields.io/github/v/release/bluefireoly/KSpigot?label=latest%20version)

### Add KSpigot to your plugins libraries

Add KSpigot as a library to the libraries list on your `plugin.yml`. Make sure that you are always using the same string
you where using when defining the dependency in Gradle.

```yaml
libraries:
  - "net.axay:kspigot:VERSION"
```

## Build your final plugin

Run the Gradle task called `build`. Using IntelliJ IDEA, this can be done using the Gradle sidebar, where you can find the task under `Tasks > build > build`. Alternatively, you can also run `./gradlew build` in your terminal.

The final .jar file of your plugin will then be at the following location: `build/libs/yourplugin.jar`
