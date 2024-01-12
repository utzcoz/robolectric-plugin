package com.example.plugin

import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.Project
import org.junit.Test
import org.junit.Assert.assertNotNull

class GreetingPluginTest {
    @Test
    fun pluginRegistersATask() {
        // Create a test project and apply the plugin
        val project: Project = ProjectBuilder.builder().build()
        project.getPlugins().apply("com.example.plugin.greeting")

        // Verify the result
        assertNotNull(project.getTasks().findByName("greet"))
    }
}
