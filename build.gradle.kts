import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val githubRepo = "nzsttn/KSpigot"

group = "dev.thelecrafter"
version = "1.20.5-fork"

description = "A Kotlin API for Minecraft plugins using the Spigot or Paper toolchain"

plugins {
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.serialization") version "1.9.24"

    `java-library`
    `maven-publish`
    signing

    id("org.jetbrains.dokka") version "1.9.20"

    id("io.papermc.paperweight.userdev") version "1.7.1"
}

repositories {
    mavenCentral()
}

dependencies {
    paperweight.paperDevBundle("1.20.6-R0.1-SNAPSHOT")

    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.8.1")
}

tasks {
    assemble {
        dependsOn(reobfJar)
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(21)
    }

    withType<KotlinCompile> {
        compilerOptions.jvmTarget.set(JvmTarget.JVM_21)
    }

    dokkaHtml.configure {
        outputDirectory.set(projectDir.resolve("docs"))
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
        maven("https://maven.pkg.github.com/nzsttn/KSpigot") {
            name = "GitHubPackages"
            credentials(PasswordCredentials::class)
        }
    }

    publications {
        register<MavenPublication>(project.name) {
            from(components["java"])
            artifact(tasks.jar.get().outputs.files.single())

            this.groupId = project.group.toString()
            this.artifactId = project.name.lowercase()
            this.version = project.version.toString()

            pom {
                name.set(project.name)
                description.set(project.description)

                developers {
                    developer {
                        name.set("jakobkmar")
                        roles.add("Original creator")
                    }
                    developer {
                        name.set("Megan")
                        url.set("https://github.com/TheLeCrafter")
                        organization.set("nzsttn")
                        organizationUrl.set("https://github.com/nzsttn")
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
