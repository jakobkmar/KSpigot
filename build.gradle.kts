@file:Suppress("PropertyName")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val GITHUB_REPO = "bluefireoly/KSpigot"

val jvmVersion = JavaVersion.VERSION_1_8
val jvmVersionString = jvmVersion.majorVersion.let {
    val version = it.toInt()
    if (version <= 10) "1.$it" else it
}

group = "net.axay"
version = "1.16.25"

description = "A Kotlin API for the Minecraft Server Software \"Spigot\"."

plugins {
    kotlin("jvm") version "1.4.31"

    `java-library`
    `maven-publish`
    signing

    id("org.jetbrains.dokka") version "1.4.30"
    kotlin("plugin.serialization") version "1.4.31"
}

repositories {
    jcenter()
    maven("https://jitpack.io")

    mavenLocal() // to get the locally available binaries of spigot (use the BuildTools)
}

dependencies {
    compileOnly("org.spigotmc", "spigot", "1.16.5-R0.1-SNAPSHOT")
    testCompileOnly("org.spigotmc", "spigot", "1.16.5-R0.1-SNAPSHOT")

    api("com.github.WesJD.AnvilGUI", "anvilgui", "master-SNAPSHOT")
    api("org.jetbrains.kotlinx", "kotlinx-serialization-json", "1.1.0")
    api("org.json", "json", "20210307")
}

java.sourceCompatibility = jvmVersion
java.targetCompatibility = jvmVersion

tasks {
    compileKotlin.configureJvmVersion()
    compileTestKotlin.configureJvmVersion()
}

fun TaskProvider<KotlinCompile>.configureJvmVersion() { get().kotlinOptions.jvmTarget = jvmVersionString }

java {
    withSourcesJar()
    withJavadocJar()
}

tasks.dokkaHtml.configure {
    outputDirectory.set(projectDir.resolve("docs"))
}

publishing {
    repositories {
        maven("https://oss.sonatype.org/service/local/staging/deploy/maven2") {
            credentials(PasswordCredentials::class)
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
