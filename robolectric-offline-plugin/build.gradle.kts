plugins {
    // Apply the Java Gradle plugin development plugin to add support for developing Gradle plugins
    `java-gradle-plugin`
    `maven-publish`
    kotlin("jvm") version "2.1.10"
    id("com.diffplug.spotless") version "7.0.2"
    id("io.gitlab.arturbosch.detekt") version "1.23.8"
    id("com.gradle.plugin-publish") version "1.3.1"
}

repositories {
    // Use Maven Central for resolving dependencies
    mavenCentral()
}

spotless {
    kotlin {
        target("**/*.kt")
        targetExclude("**/build/*.kt")
        ktfmt().kotlinlangStyle()
    }

    kotlinGradle {
        target("*.gradle.kts", "**/*.gradle.kts")
        ktfmt().kotlinlangStyle()
    }
}

group = "me.utzcoz.robolectric"

version = "0.1-snapshot"

dependencies {
    // Use JUnit test framework for unit tests
    testImplementation("junit:junit:4.13.2")
}

gradlePlugin {
    website = "https://github.com/utzcoz/robolectric-offline-plugin"
    vcsUrl = "https://github.com/utzcoz/robolectric-offline-plugin"
    plugins {
        create("robolectricOffline") {
            id = "me.utzcoz.robolectric.offline"
            implementationClass = "me.utzcoz.robolectric.offline.RobolectricOfflinePlugin"
            displayName = "robolectricOffline"
            description =
                "A plugin to download and manage android-all jars for Robolectric offline mode."
            tags = listOf("robolectric", "Robolectric", "robolectric-offline")
        }
    }
}

// Add a source set and a task for a functional test suite
val functionalTest: SourceSet by sourceSets.creating

gradlePlugin.testSourceSets(functionalTest)

configurations[functionalTest.implementationConfigurationName].extendsFrom(
    configurations.testImplementation.get()
)

val functionalTestTask =
    tasks.register<Test>("functionalTest") {
        testClassesDirs = functionalTest.output.classesDirs
        classpath =
            configurations[functionalTest.runtimeClasspathConfigurationName] + functionalTest.output
    }

tasks.check {
    // Run the functional tests as part of `check`
    dependsOn(functionalTestTask)
}
