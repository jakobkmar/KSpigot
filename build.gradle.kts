import Build_gradle.BuildConstants.CONSISTENT_VERSION_STRING
import Build_gradle.BuildConstants.JVM_VERSION
import Build_gradle.BuildConstants.JVM_VERSION_STRING

object BuildConstants {
    val JVM_VERSION = JavaVersion.VERSION_1_8
    const val JVM_VERSION_STRING = "1.8"
    const val CONSISTENT_VERSION_STRING = "8"
}

/**
 * PROJECT
 */

group = "net.axay"
version = "1.16.2"

plugins {
    java
    kotlin("jvm") version "1.4.0"
    maven
}

/**
 * DEPENDENCY MANAGEMENT
 */

repositories {
    mavenCentral()
    mavenLocal() // for retrieving the local available binaries of spigot (use the BuildTools)
}

dependencies {

    // KOTLIN
    implementation(kotlin("stdlib-jdk$CONSISTENT_VERSION_STRING"))

    // SPIGOT
    compileOnly("org.spigotmc", "spigot", "1.16.2-R0.1-SNAPSHOT")
    testCompileOnly("org.spigotmc", "spigot", "1.16.2-R0.1-SNAPSHOT")

}

/**
 * BUILD
 */

java.sourceCompatibility = JVM_VERSION

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = JVM_VERSION_STRING
    }
}