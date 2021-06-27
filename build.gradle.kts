import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val githubRepo = "bluefireoly/KSpigot"

group = "net.axay"
version = "1.17.2"

description = "A Kotlin API for the Minecraft Server Software \"Spigot\"."

plugins {
    kotlin("jvm") version "1.5.10"

    `java-library`
    `maven-publish`
    signing

    id("org.jetbrains.dokka") version "1.4.32"
    kotlin("plugin.serialization") version "1.5.10"
}

repositories {
    mavenCentral()
    mavenLocal() // to get the locally available binaries of spigot (use the BuildTools)
}

dependencies {
    compileOnly("org.spigotmc", "spigot", "1.17-R0.1-SNAPSHOT")
    testCompileOnly("org.spigotmc", "spigot", "1.17-R0.1-SNAPSHOT")

    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.1")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.5.0")
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(16)
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "16"
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
        maven("https://oss.sonatype.org/service/local/staging/deploy/maven2") {
            name = "ossrh"
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
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
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
