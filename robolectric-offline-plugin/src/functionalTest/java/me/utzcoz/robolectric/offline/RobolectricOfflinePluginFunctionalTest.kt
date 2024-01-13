package me.utzcoz.robolectric.offline

import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import junit.framework.TestCase.assertTrue
import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.junit.Test

class RobolectricOfflinePluginFunctionalTest {
    @Test
    @Throws(IOException::class)
    fun canRunTask() {
        // Setup the test build
        val projectDir = File("build/functionalTest")
        Files.createDirectories(projectDir.toPath())
        writeString(File(projectDir, "settings.gradle"), "")
        writeString(
            File(projectDir, "build.gradle"),
            "plugins {" + "  id('com.example.plugin.greeting')" + "}"
        )

        // Run the build
        val result: BuildResult =
            GradleRunner.create()
                .forwardOutput()
                .withPluginClasspath()
                .withArguments("greet")
                .withProjectDir(projectDir)
                .build()

        // Verify the result
        assertTrue(result.output.contains("Hello from plugin 'me.utzcoz.robolectric.offline'"))
    }

    @Throws(IOException::class)
    private fun writeString(file: File, string: String) {
        FileWriter(file).use { writer -> writer.write(string) }
    }
}
