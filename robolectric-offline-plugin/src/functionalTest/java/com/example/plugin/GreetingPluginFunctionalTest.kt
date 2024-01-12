package com.example.plugin

import org.gradle.testkit.runner.BuildResult

class GreetingPluginFunctionalTest {
    @Test
    @Throws(IOException::class)
    fun canRunTask() {
        // Setup the test build
        val projectDir: File = File("build/functionalTest")
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
        assertTrue(result.getOutput().contains("Hello from plugin 'com.example.plugin.greeting'"))
    }

    @Throws(IOException::class)
    private fun writeString(file: File, string: String) {
        FileWriter(file).use { writer -> writer.write(string) }
    }
}
