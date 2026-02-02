package me.utzcoz.robolectric.offline

/**
 * Represents a Robolectric SDK with its preinstrumented android.jar coordinates.
 *
 * @param apiLevel The Android API level (e.g., 21, 28, 29)
 * @param androidVersion The Android version string (e.g., "10", "11", "12")
 * @param robolectricVersion The Robolectric build version (e.g., "5803371", "6757853")
 * @param preinstrumentedVersion The preinstrumented jar version (e.g., "i7")
 */
data class RobolectricSdk(
    val apiLevel: Int,
    val androidVersion: String,
    val robolectricVersion: String,
    val preinstrumentedVersion: Int,
) {
    /** The group ID for the preinstrumented android-all jar in Maven Central. */
    val groupId: String = "org.robolectric"

    /** The artifact ID for the preinstrumented android-all jar in Maven Central. */
    val artifactId: String = "android-all-instrumented"

    /** The full version string for the preinstrumented android-all jar. */
    val version: String = "$androidVersion-robolectric-$robolectricVersion-i$preinstrumentedVersion"

    /** The Maven coordinates in the format "groupId:artifactId:version". */
    val coordinates: String = "$groupId:$artifactId:$version"

    @Suppress("MagicNumber")
    companion object {
        /**
         * Returns the SDK definitions supported by each Robolectric version.
         *
         * This uses explicit version mapping without complex version comparison logic. When a new
         * Robolectric version is released with SDK changes, a new entry should be added here.
         *
         * @param robolectricVersion The Robolectric library version (e.g., "4.14", "4.16.1")
         * @return A list of SDK definitions supported by the specified Robolectric version
         */
        @JvmStatic
        fun sdksForRobolectricVersion(robolectricVersion: String): List<RobolectricSdk> {
            return when (robolectricVersion) {
                // Robolectric 4.16.x - supports SDK 23-36, preinstrumented version i7
                "4.16",
                "4.16.1" -> sdksForRobolectric416()
                // Robolectric 4.14.x and 4.15.x - supports SDK 21-35, preinstrumented version i7
                "4.14",
                "4.14.1",
                "4.15",
                "4.15.1" -> sdksForRobolectric414()
                // Robolectric 4.12.x and 4.13.x - supports SDK 19-34, preinstrumented version i6
                "4.12",
                "4.12.1",
                "4.13",
                "4.13.1" -> sdksForRobolectric412()
                // Robolectric 4.10.x and 4.11.x - supports SDK 19-33, preinstrumented version i5
                "4.10",
                "4.10.1",
                "4.10.2",
                "4.10.3",
                "4.11",
                "4.11.1" -> sdksForRobolectric410()
                // Robolectric 4.8.x and 4.9.x - supports SDK 19-33, preinstrumented version i4
                "4.8",
                "4.8.1",
                "4.8.2",
                "4.9",
                "4.9.1",
                "4.9.2" -> sdksForRobolectric48()
                // Default to latest known version (4.16.1) for unknown versions
                else -> sdksForRobolectric416()
            }
        }

        /** SDKs for Robolectric 4.16.x - SDK 23-36, preinstrumented version i7. */
        private fun sdksForRobolectric416(): List<RobolectricSdk> =
            listOf(
                RobolectricSdk(23, "6.0.1_r3", "r1", 7),
                RobolectricSdk(24, "7.0.0_r1", "r1", 7),
                RobolectricSdk(25, "7.1.0_r7", "r1", 7),
                RobolectricSdk(26, "8.0.0_r4", "r1", 7),
                RobolectricSdk(27, "8.1.0", "4611349", 7),
                RobolectricSdk(28, "9", "4913185-2", 7),
                RobolectricSdk(29, "10", "5803371", 7),
                RobolectricSdk(30, "11", "6757853", 7),
                RobolectricSdk(31, "12", "7732740", 7),
                RobolectricSdk(32, "12.1", "8229987", 7),
                RobolectricSdk(33, "13", "9030017", 7),
                RobolectricSdk(34, "14", "10818077", 7),
                // Note: SDK 35 uses robolectricVersion 13954326 (updated from 12650502 in 4.14)
                // SDK 36 uses robolectricVersion 13921718
                // These versions come directly from Robolectric's DefaultSdkProvider
                RobolectricSdk(35, "15", "13954326", 7),
                RobolectricSdk(36, "16", "13921718", 7),
            )

        /** SDKs for Robolectric 4.14.x and 4.15.x - SDK 21-35, preinstrumented version i7. */
        private fun sdksForRobolectric414(): List<RobolectricSdk> =
            listOf(
                RobolectricSdk(21, "5.0.2_r3", "r0", 7),
                RobolectricSdk(22, "5.1.1_r9", "r2", 7),
                RobolectricSdk(23, "6.0.1_r3", "r1", 7),
                RobolectricSdk(24, "7.0.0_r1", "r1", 7),
                RobolectricSdk(25, "7.1.0_r7", "r1", 7),
                RobolectricSdk(26, "8.0.0_r4", "r1", 7),
                RobolectricSdk(27, "8.1.0", "4611349", 7),
                RobolectricSdk(28, "9", "4913185-2", 7),
                RobolectricSdk(29, "10", "5803371", 7),
                RobolectricSdk(30, "11", "6757853", 7),
                RobolectricSdk(31, "12", "7732740", 7),
                RobolectricSdk(32, "12.1", "8229987", 7),
                RobolectricSdk(33, "13", "9030017", 7),
                RobolectricSdk(34, "14", "10818077", 7),
                RobolectricSdk(35, "15", "12650502", 7),
            )

        /** SDKs for Robolectric 4.12.x and 4.13.x - SDK 19-34, preinstrumented version i6. */
        private fun sdksForRobolectric412(): List<RobolectricSdk> =
            listOf(
                RobolectricSdk(19, "4.4_r1", "r2", 6),
                RobolectricSdk(21, "5.0.2_r3", "r0", 6),
                RobolectricSdk(22, "5.1.1_r9", "r2", 6),
                RobolectricSdk(23, "6.0.1_r3", "r1", 6),
                RobolectricSdk(24, "7.0.0_r1", "r1", 6),
                RobolectricSdk(25, "7.1.0_r7", "r1", 6),
                RobolectricSdk(26, "8.0.0_r4", "r1", 6),
                RobolectricSdk(27, "8.1.0", "4611349", 6),
                RobolectricSdk(28, "9", "4913185-2", 6),
                RobolectricSdk(29, "10", "5803371", 6),
                RobolectricSdk(30, "11", "6757853", 6),
                RobolectricSdk(31, "12", "7732740", 6),
                RobolectricSdk(32, "12.1", "8229987", 6),
                RobolectricSdk(33, "13", "9030017", 6),
                RobolectricSdk(34, "14", "10818077", 6),
            )

        /** SDKs for Robolectric 4.10.x and 4.11.x - SDK 19-33, preinstrumented version i5. */
        private fun sdksForRobolectric410(): List<RobolectricSdk> =
            listOf(
                RobolectricSdk(19, "4.4_r1", "r2", 5),
                RobolectricSdk(21, "5.0.2_r3", "r0", 5),
                RobolectricSdk(22, "5.1.1_r9", "r2", 5),
                RobolectricSdk(23, "6.0.1_r3", "r1", 5),
                RobolectricSdk(24, "7.0.0_r1", "r1", 5),
                RobolectricSdk(25, "7.1.0_r7", "r1", 5),
                RobolectricSdk(26, "8.0.0_r4", "r1", 5),
                RobolectricSdk(27, "8.1.0", "4611349", 5),
                RobolectricSdk(28, "9", "4913185-2", 5),
                RobolectricSdk(29, "10", "5803371", 5),
                RobolectricSdk(30, "11", "6757853", 5),
                RobolectricSdk(31, "12", "7732740", 5),
                RobolectricSdk(32, "12.1", "8229987", 5),
                RobolectricSdk(33, "13", "9030017", 5),
            )

        /** SDKs for Robolectric 4.8.x and 4.9.x - SDK 19-33, preinstrumented version i4. */
        private fun sdksForRobolectric48(): List<RobolectricSdk> =
            listOf(
                RobolectricSdk(19, "4.4_r1", "r2", 4),
                RobolectricSdk(21, "5.0.2_r3", "r0", 4),
                RobolectricSdk(22, "5.1.1_r9", "r2", 4),
                RobolectricSdk(23, "6.0.1_r3", "r1", 4),
                RobolectricSdk(24, "7.0.0_r1", "r1", 4),
                RobolectricSdk(25, "7.1.0_r7", "r1", 4),
                RobolectricSdk(26, "8.0.0_r4", "r1", 4),
                RobolectricSdk(27, "8.1.0", "4611349", 4),
                RobolectricSdk(28, "9", "4913185-2", 4),
                RobolectricSdk(29, "10", "5803371", 4),
                RobolectricSdk(30, "11", "6757853", 4),
                RobolectricSdk(31, "12", "7732740", 4),
                RobolectricSdk(32, "12.1", "8229987", 4),
                RobolectricSdk(33, "13", "9030017", 4),
            )
    }
}
