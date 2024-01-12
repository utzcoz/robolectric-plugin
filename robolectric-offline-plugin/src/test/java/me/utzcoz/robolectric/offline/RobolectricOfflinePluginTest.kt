package me.utzcoz.robolectric.offline

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Assert.assertNotNull
import org.junit.Test

class RobolectricOfflinePluginTest {
    @Test
    fun pluginRegistersATask() {
        // Create a test project and apply the plugin
        val project: Project = ProjectBuilder.builder().build()
        project.getPlugins().apply("com.example.plugin.greeting")

        // Verify the result
        assertNotNull(project.getTasks().findByName("greet"))
    }
}
