In this guide we will assume that you are using or want to use Gradle.

## Install Java

You will need to have the JDK (Java Development Kit) installed.

If you do not already have a JDK installed, you have several options:

- [Windows](https://adoptopenjdk.net/)
- Arch `pacman -S jdk-openjdk` (refer to the [archwiki](https://wiki.archlinux.org/index.php/Java#OpenJDK) for other versions)
- Debian `apt install default-jdk` (refer to the [Debian Wiki](https://wiki.debian.org/Java/) for other versions)

Please make sure that you know which version of Java you have just installed. If you just have one installation of Java, run `java -version` to get the current Java version on your system.

## Create a project

In IntelliJ IDEA, click on `File > New > Project` and choose `Gradle` on the sidebar in the popup window.

Now choose the JDK which you have just installed.

If you want to write your gradle build script in Kotlin, make sure to tick the `Kotlin DSL build script` checkbox. _(recommended)_

Now select `Java` and `Kotlin/JVM` as your frameworks.

After that, you can create your project.

You may now continue with the [Setup using Gradle](gradle.md) guide.

## Add the Spigot `plugin.yml`

Create a new file called `plugin.yml` in the `resources` directory of your project.

[Read the official documentation](https://www.spigotmc.org/wiki/plugin-yml/) for the Spigot plugin.yml file.
