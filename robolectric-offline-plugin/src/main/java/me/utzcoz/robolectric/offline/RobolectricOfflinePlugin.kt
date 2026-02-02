package me.utzcoz.robolectric.offline

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * A Gradle plugin for downloading preinstrumented android.jar files for Robolectric offline mode.
 *
 * This plugin allows you to download all the preinstrumented android.jar files needed by
 * Robolectric before running tests. This is useful for CI environments where you want to cache the
 * dependencies and avoid downloading them during test execution.
 *
 * Usage in build.gradle.kts:
 * ```
 * plugins {
 *     id("me.utzcoz.robolectric.offline")
 * }
 *
 * robolectricOffline {
 *     robolectricVersion.set("4.14")
 * }
 * ```
 *
 * Then run the task:
 * ```
 * ./gradlew robolectricOffline
 * ```
 *
 * The plugin will download all preinstrumented android.jar files for the specified Robolectric
 * version to Gradle's dependency cache.
 */
class RobolectricOfflinePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        // Create the extension
        val extension =
            target.extensions.create("robolectricOffline", RobolectricOfflineExtension::class.java)
        extension.robolectricVersion.convention("4.14")

        // Register the download task
        target.tasks.register("robolectricOffline", DownloadRobolectricAndroidTask::class.java) {
            task ->
            task.robolectricVersion.set(extension.robolectricVersion)
        }
    }
}
