package me.utzcoz.robolectric.offline

import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import junit.framework.TestCase.assertTrue
import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.Assert.assertEquals
import org.junit.Test

class RobolectricOfflinePluginFunctionalTest {
    @Test
    @Throws(IOException::class)
    fun canRunTaskWithDefaultVersion() {
        // Setup the test build
        val projectDir = File("build/functionalTest")
        Files.createDirectories(projectDir.toPath())
        writeString(File(projectDir, "settings.gradle"), "")
        writeString(
            File(projectDir, "build.gradle"),
            """
            plugins {
                id('me.utzcoz.robolectric.offline')
            }

            repositories {
                mavenCentral()
            }
            """
                .trimIndent(),
        )

        // Run the build
        val result: BuildResult =
            GradleRunner.create()
                .forwardOutput()
                .withPluginClasspath()
                .withArguments("robolectricOffline", "--info")
                .withProjectDir(projectDir)
                .build()

        // Verify the result
        assertTrue(
            "Output should contain download message",
            result.output.contains("Downloading preinstrumented android.jar files for Robolectric"),
        )
        assertEquals(
            "Task should succeed",
            TaskOutcome.SUCCESS,
            result.task(":robolectricOffline")?.outcome,
        )
    }

    @Test
    @Throws(IOException::class)
    fun canRunTaskWithCustomVersion() {
        // Setup the test build
        val projectDir = File("build/functionalTestCustomVersion")
        Files.createDirectories(projectDir.toPath())
        writeString(File(projectDir, "settings.gradle"), "")
        writeString(
            File(projectDir, "build.gradle"),
            """
            plugins {
                id('me.utzcoz.robolectric.offline')
            }

            repositories {
                mavenCentral()
            }

            robolectricOffline {
                robolectricVersion = "4.12"
            }
            """
                .trimIndent(),
        )

        // Run the build
        val result: BuildResult =
            GradleRunner.create()
                .forwardOutput()
                .withPluginClasspath()
                .withArguments("robolectricOffline", "--info")
                .withProjectDir(projectDir)
                .build()

        // Verify the result
        assertTrue(
            "Output should contain download message for 4.12",
            result.output.contains(
                "Downloading preinstrumented android.jar files for Robolectric 4.12"
            ),
        )
        // For 4.12, it should use i6 preinstrumented jars
        assertTrue("Output should reference i6 preinstrumented jars", result.output.contains("-i6"))
        assertEquals(
            "Task should succeed",
            TaskOutcome.SUCCESS,
            result.task(":robolectricOffline")?.outcome,
        )
    }

    @Throws(IOException::class)
    private fun writeString(file: File, string: String) {
        FileWriter(file).use { writer -> writer.write(string) }
    }
}
