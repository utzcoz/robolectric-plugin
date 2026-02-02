package me.utzcoz.robolectric.offline

import org.gradle.api.provider.Property

/**
 * Extension for configuring the Robolectric offline plugin.
 *
 * This extension allows users to customize the Robolectric version and the download directory for
 * preinstrumented android.jar files.
 *
 * Usage in build.gradle.kts:
 * ```
 * robolectricOffline {
 *     robolectricVersion.set("4.16.1")
 * }
 * ```
 */
interface RobolectricOfflineExtension {
    /**
     * The Robolectric version to use for determining which preinstrumented SDK jars to download.
     *
     * Default: "4.16.1"
     *
     * Supported versions: 4.8, 4.9, 4.10, 4.11, 4.12, 4.13, 4.14, 4.15, 4.16, 4.16.1
     */
    val robolectricVersion: Property<String>
}
