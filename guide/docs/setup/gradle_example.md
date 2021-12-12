An example for a Gradle build script of a project using KSpigot would be:

*(please note that the version in the following examples might be outdated)*

`build.gradle.kts`
```kotlin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.0"
    id("io.papermc.paperweight.userdev") version "1.3.1"
}

group = "your.group"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    paperDevBundle("1.18.1-R0.1-SNAPSHOT")
    implementation("net.axay:kspigot:1.18.0")
}

tasks {
    build {
        dependsOn(reobfJar)
    }
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(17)
    }
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }
}
```

`settings.gradle.kts`
```kotlin
rootProject.name = "projectname"

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://papermc.io/repo/repository/maven-public/")
    }
}
```
