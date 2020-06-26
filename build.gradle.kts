object BuildConstants {

    val JVM_VERSION = JavaVersion.VERSION_1_8
    const val JVM_VERSION_STRING = "1.8"

}

plugins {
    java
    kotlin("jvm") version "1.3.72"
    maven
}

group = "net.axay"
version = "1.16.1"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {

    // KOTLIN
    implementation(kotlin("stdlib-jdk8"))

    // SPIGOT
    compileOnly("org.spigotmc", "spigot", "1.16.1-R0.1-SNAPSHOT")

}

configure<JavaPluginConvention> {
    sourceCompatibility = BuildConstants.JVM_VERSION
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = BuildConstants.JVM_VERSION_STRING
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = BuildConstants.JVM_VERSION_STRING
    }
}