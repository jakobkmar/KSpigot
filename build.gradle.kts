import io.papermc.paperweight.userdev.ReobfArtifactConfiguration
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val githubRepo = "jakobkmar/KSpigot"

group = "net.axay"
version = "1.21.4"

description = "A Kotlin API for Minecraft plugins using the Spigot or Paper toolchain"

plugins {
    kotlin("jvm") version libs.versions.kotlin
    kotlin("plugin.serialization") version libs.versions.kotlin

    id("org.jetbrains.dokka") version "2.0.0"

    `java-library`
    `maven-publish`
    signing

    id("io.papermc.paperweight.userdev") version "2.0.0-beta.8"
}

repositories {
    mavenCentral()
}

dependencies {
    paperweight.paperDevBundle("1.21.4-R0.1-SNAPSHOT")

    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.8.1")
}

paperweight {
    reobfArtifactConfiguration = ReobfArtifactConfiguration.MOJANG_PRODUCTION
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(21)
    }

    withType<KotlinCompile> {
        compilerOptions.jvmTarget.set(JvmTarget.JVM_21)
    }
}

dokka {
    dokkaPublications.html {
        outputDirectory.set(layout.buildDirectory.dir("docs"))
    }
}

java {
    withSourcesJar()
    withJavadocJar()
}

signing {
    sign(publishing.publications)
}

publishing {
    repositories {
        maven("https://oss.sonatype.org/service/local/staging/deploy/maven2") {
            name = "ossrh"
            credentials(PasswordCredentials::class)
        }
    }

    publications {
        register<MavenPublication>(project.name) {
            from(components["java"])

            this.groupId = project.group.toString()
            this.artifactId = project.name.lowercase()
            this.version = project.version.toString()

            pom {
                name.set(project.name)
                description.set(project.description)

                developers {
                    developer {
                        name.set("jakobkmar")
                    }
                }

                licenses {
                    license {
                        name.set("GNU General Public License, Version 3")
                        url.set("https://www.gnu.org/licenses/gpl-3.0.en.html")
                    }
                }

                url.set("https://github.com/${githubRepo}")

                scm {
                    connection.set("scm:git:git://github.com/${githubRepo}.git")
                    url.set("https://github.com/${githubRepo}/tree/main")
                }
            }
        }
    }
}
