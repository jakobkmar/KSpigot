/**
 * PROJECT
 */

group = "net.axay"
version = "1.16.1"

plugins {
    java
    kotlin("jvm") version "1.3.72"
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
    implementation(kotlin("stdlib"))

    // SPIGOT
    compileOnly("org.spigotmc", "spigot", "1.16.1-R0.1-SNAPSHOT")
    testCompileOnly("org.spigotmc", "spigot", "1.16.1-R0.1-SNAPSHOT")

}

/**
 * BUILD
 */

object BuildConstants {

    val JVM_VERSION = JavaVersion.VERSION_1_8
    const val JVM_VERSION_STRING = "1.8"

}

java.sourceCompatibility = BuildConstants.JVM_VERSION

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = BuildConstants.JVM_VERSION_STRING
    }
}