@file:Suppress("PropertyName")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/*
 * BUILD CONSTANTS
 */

val GITHUB_REPO = "bluefireoly/KSpigot"

val JVM_VERSION = JavaVersion.VERSION_1_8
val JVM_VERSION_STRING = JVM_VERSION.versionString

/*
 * PROJECT
 */

group = "net.axay"
version = "1.16.24"

description = "A Kotlin API for the Minecraft Server Software \"Spigot\"."

/*
 * PLUGINS
 */

plugins {

    `java-library`

    kotlin("jvm") version "1.4.30"

    `maven-publish`
    signing

    id("org.jetbrains.dokka") version "1.4.20"

    kotlin("plugin.serialization") version "1.4.21"

}

/*
 * DEPENDENCY MANAGEMENT
 */

repositories {
    jcenter()
    maven("https://jitpack.io")

    mavenLocal() // to get the locally available binaries of spigot (use the BuildTools)
}

dependencies {

    // SPIGOT
    compileOnly("org.spigotmc", "spigot", "1.16.5-R0.1-SNAPSHOT")
    testCompileOnly("org.spigotmc", "spigot", "1.16.5-R0.1-SNAPSHOT")

    // KHTTP
    api("khttp", "khttp", "1.0.0")

    // ANVIL GUI
    api("com.github.WesJD.AnvilGUI", "anvilgui", "master-SNAPSHOT")

    // KOTLINX
    // serialization
    api("org.jetbrains.kotlinx", "kotlinx-serialization-json", "1.0.1")

}

/*
 * BUILD
 */

java.sourceCompatibility = JVM_VERSION
java.targetCompatibility = JVM_VERSION

tasks {
    compileKotlin.configureJvmVersion()
    compileTestKotlin.configureJvmVersion()
}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks.dokkaHtml.configure {
    outputDirectory.set(projectDir.resolve("docs"))
}

/*
 * PUBLISHING
 */

publishing {

    repositories {
        maven("https://oss.sonatype.org/service/local/staging/deploy/maven2") {
            credentials {
                username = property("ossrh.username") as String
                password = property("ossrh.password") as String
            }
        }
    }

    publications {
        create<MavenPublication>(project.name) {

            from(components["java"])

            this.groupId = project.group.toString()
            this.artifactId = project.name.toLowerCase()
            this.version = project.version.toString()

            pom {

                name.set(project.name)
                description.set(project.description)

                developers {
                    developer {
                        name.set("bluefireoly")
                    }
                }

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                url.set("https://github.com/${GITHUB_REPO}")

                scm {
                    connection.set("scm:git:git://github.com/${GITHUB_REPO}.git")
                    url.set("https://github.com/${GITHUB_REPO}/tree/main")
                }

            }

        }
    }

}

signing {
    sign(publishing.publications)
}

/*
 * EXTENSIONS
 */

val JavaVersion.versionString get() = majorVersion.let {
    val version = it.toInt()
    if (version <= 10) "1.$it" else it
}

fun TaskProvider<KotlinCompile>.configureJvmVersion() { get().kotlinOptions.jvmTarget = JVM_VERSION_STRING }
