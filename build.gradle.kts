@file:Suppress("PropertyName")

import java.util.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/*
 * BUILD CONSTANTS
 */

val GITHUB_URL = "https://github.com/bluefireoly/KSpigot"

val JVM_VERSION = JavaVersion.VERSION_1_8
val JVM_VERSION_STRING = JVM_VERSION.versionString

/*
 * PROJECT
 */

group = "net.axay"
version = "1.16.3_R14"

description = "A Kotlin API for the Minecraft Server Software \"Spigot\"."

/*
 * PLUGINS
 */

plugins {

    java

    kotlin("jvm") version "1.4.10"

    maven
    `maven-publish`

    id("com.jfrog.bintray") version "1.8.5"

    id("org.jetbrains.dokka") version "1.4.10"

}

/*
 * DEPENDENCY MANAGEMENT
 */

repositories {
    mavenCentral()
    jcenter()

    mavenLocal() // to get the locally available binaries of spigot (use the BuildTools)
}

dependencies {

    // SPIGOT
    compileOnly("org.spigotmc", "spigot", "1.16.3-R0.1-SNAPSHOT")
    testCompileOnly("org.spigotmc", "spigot", "1.16.3-R0.1-SNAPSHOT")

    // KHTTP
    api("khttp", "khttp", "1.0.0")

}

/*
 * BUILD
 */

// JVM VERSION

java.sourceCompatibility = JVM_VERSION

tasks {
    compileKotlin.configureJvmVersion()
    compileTestKotlin.configureJvmVersion()
}

// SOURCE CODE

val sourcesJar by tasks.creating(Jar::class) {
    dependsOn(JavaPlugin.CLASSES_TASK_NAME)
    archiveClassifier.set("sources")
    from(sourceSets["main"].allSource)
}

artifacts {
    add("archives", sourcesJar)
}

/*
 * DOCUMENTATION
 */

tasks.dokkaHtml.configure {
    outputDirectory.set(projectDir.resolve("docs"))
}

/*
 * PUBLISHING
 */

bintray {

    user = project.findProperty("bintray.username") as String
    key = project.findProperty("bintray.api_key") as String

    setPublications("KSpigot")

    pkg.apply {

        version.apply {
            name = project.version.toString()
            released = Date().toString()
        }

        repo = project.name
        name = project.name

        setLicenses("Apache-2.0")

        vcsUrl = GITHUB_URL

    }

}

publishing {

    publications {
        create<MavenPublication>("KSpigot") {

            from(components["java"])

            artifact(sourcesJar)

            this.groupId = project.group.toString()
            this.artifactId = project.name
            this.version = project.version.toString()

            pom {

                name.set(project.name)
                description.set(project.description)

                developers {
                    developer {
                        name.set("bluefireoly")
                    }
                }

                url.set(GITHUB_URL)
                scm { url.set(GITHUB_URL) }

            }

        }
    }

}

/*
 * EXTENSIONS
 */

val JavaVersion.versionString get() = majorVersion.let {
    val version = it.toInt()
    if (version <= 10) "1.$it" else it
}

fun TaskProvider<KotlinCompile>.configureJvmVersion() { get().kotlinOptions.jvmTarget = JVM_VERSION_STRING }