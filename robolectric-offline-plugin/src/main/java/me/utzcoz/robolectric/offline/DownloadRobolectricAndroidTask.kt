package me.utzcoz.robolectric.offline

import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.ResolveException
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

/**
 * Task to download preinstrumented android.jar files for Robolectric offline mode.
 *
 * This task resolves the preinstrumented android-all-instrumented dependencies from Maven Central
 * and downloads them to Gradle's dependency cache. The files can then be used by Robolectric in
 * offline mode.
 *
 * The task uses Gradle's dependency resolution mechanism to download the files, which means they
 * will be cached in the Gradle dependency cache (~/.gradle/caches/modules-2/files-2.1/).
 */
abstract class DownloadRobolectricAndroidTask : DefaultTask() {
    /**
     * The Robolectric version to use for determining which SDK jars to download. Default: "4.14"
     */
    @get:Input abstract val robolectricVersion: Property<String>

    init {
        group = "robolectric"
        description = "Downloads preinstrumented android.jar files for Robolectric offline mode"
        robolectricVersion.convention("4.14")
    }

    @TaskAction
    fun download() {
        val version = robolectricVersion.get()
        val sdks = RobolectricSdk.sdksForRobolectricVersion(version)

        logger.lifecycle("Downloading preinstrumented android.jar files for Robolectric $version")
        logger.lifecycle("SDKs to download: ${sdks.size}")

        val configuration: Configuration =
            project.configurations.create("robolectricAndroidAll_${System.currentTimeMillis()}")
        configuration.isTransitive = false

        sdks.forEach { sdk ->
            logger.lifecycle("  Adding dependency: ${sdk.coordinates}")
            project.dependencies.add(configuration.name, sdk.coordinates)
        }

        try {
            val files = configuration.resolve()
            logger.lifecycle(
                "Successfully downloaded ${files.size} preinstrumented android.jar files:"
            )
            files.forEach { file -> logger.lifecycle("  - ${file.name}") }
        } catch (e: ResolveException) {
            logger.error("Failed to download preinstrumented android.jar files: ${e.message}")
            throw e
        } finally {
            project.configurations.remove(configuration)
        }
    }
}
