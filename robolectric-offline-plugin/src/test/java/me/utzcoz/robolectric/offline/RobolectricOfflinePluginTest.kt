package me.utzcoz.robolectric.offline

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class RobolectricOfflinePluginTest {
    @Test
    fun pluginRegistersTask() {
        // Create a test project and apply the plugin
        val project: Project = ProjectBuilder.builder().build()
        project.plugins.apply("me.utzcoz.robolectric.offline")

        // Verify the task is registered
        val task = project.tasks.findByName("robolectricOffline")
        assertNotNull("robolectricOffline task should be registered", task)
        assertTrue(
            "Task should be DownloadRobolectricAndroidTask",
            task is DownloadRobolectricAndroidTask,
        )
    }

    @Test
    fun pluginCreatesExtension() {
        // Create a test project and apply the plugin
        val project: Project = ProjectBuilder.builder().build()
        project.plugins.apply("me.utzcoz.robolectric.offline")

        // Verify the extension is created
        val extension = project.extensions.findByType(RobolectricOfflineExtension::class.java)
        assertNotNull("robolectricOffline extension should be created", extension)
    }

    @Test
    fun extensionHasDefaultRobolectricVersion() {
        // Create a test project and apply the plugin
        val project: Project = ProjectBuilder.builder().build()
        project.plugins.apply("me.utzcoz.robolectric.offline")

        // Verify the default Robolectric version
        val extension = project.extensions.findByType(RobolectricOfflineExtension::class.java)
        assertNotNull(extension)
        assertEquals("4.16.1", extension!!.robolectricVersion.get())
    }

    @Test
    fun taskUsesExtensionRobolectricVersion() {
        // Create a test project and apply the plugin
        val project: Project = ProjectBuilder.builder().build()
        project.plugins.apply("me.utzcoz.robolectric.offline")

        // Configure the extension
        val extension = project.extensions.findByType(RobolectricOfflineExtension::class.java)
        extension!!.robolectricVersion.set("4.12")

        // Verify the task uses the configured version
        val task = project.tasks.findByName("robolectricOffline") as DownloadRobolectricAndroidTask
        assertEquals("4.12", task.robolectricVersion.get())
    }
}
