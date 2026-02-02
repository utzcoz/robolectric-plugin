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

    companion object {
        // Android API level constants
        private const val API_KITKAT = 19
        private const val API_LOLLIPOP = 21
        private const val API_LOLLIPOP_MR1 = 22
        private const val API_MARSHMALLOW = 23
        private const val API_NOUGAT = 24
        private const val API_NOUGAT_MR1 = 25
        private const val API_OREO = 26
        private const val API_OREO_MR1 = 27
        private const val API_PIE = 28
        private const val API_Q = 29
        private const val API_R = 30
        private const val API_S = 31
        private const val API_S_V2 = 32
        private const val API_TIRAMISU = 33
        private const val API_UPSIDE_DOWN_CAKE = 34
        private const val API_VANILLA_ICE_CREAM = 35

        // Preinstrumented jar version constants
        private const val PREINSTRUMENTED_V4 = 4
        private const val PREINSTRUMENTED_V5 = 5
        private const val PREINSTRUMENTED_V6 = 6
        private const val PREINSTRUMENTED_V7 = 7

        // Robolectric version thresholds
        private const val ROBOLECTRIC_MAJOR_V4 = 4
        private const val ROBOLECTRIC_MINOR_I7_START = 14
        private const val ROBOLECTRIC_MINOR_I6_START = 12
        private const val ROBOLECTRIC_MINOR_I6_END = 13
        private const val ROBOLECTRIC_MINOR_I5_START = 10
        private const val ROBOLECTRIC_MINOR_I5_END = 11
        private const val ROBOLECTRIC_MINOR_I4_START = 8
        private const val ROBOLECTRIC_MINOR_I4_END = 9

        /**
         * Returns the SDK definitions supported by each Robolectric version. The mapping is based
         * on DefaultSdkProvider from Robolectric.
         *
         * @param robolectricVersion The Robolectric library version (e.g., "4.11", "4.12", "4.14")
         * @return A list of SDK definitions supported by the specified Robolectric version
         */
        @JvmStatic
        fun sdksForRobolectricVersion(robolectricVersion: String): List<RobolectricSdk> {
            // Parse version like "4.11", "4.12", "4.14.1"
            val versionParts = robolectricVersion.split(".")
            val major = versionParts.getOrNull(0)?.toIntOrNull() ?: ROBOLECTRIC_MAJOR_V4
            val minor = versionParts.getOrNull(1)?.toIntOrNull() ?: 0

            return when {
                // Robolectric 4.14+ uses preinstrumented version i7
                major > ROBOLECTRIC_MAJOR_V4 ||
                    (major == ROBOLECTRIC_MAJOR_V4 && minor >= ROBOLECTRIC_MINOR_I7_START) ->
                    sdksForI7()
                // Robolectric 4.12-4.13 uses preinstrumented version i6
                major == ROBOLECTRIC_MAJOR_V4 &&
                    minor in ROBOLECTRIC_MINOR_I6_START..ROBOLECTRIC_MINOR_I6_END -> sdksForI6()
                // Robolectric 4.10-4.11 uses preinstrumented version i5
                major == ROBOLECTRIC_MAJOR_V4 &&
                    minor in ROBOLECTRIC_MINOR_I5_START..ROBOLECTRIC_MINOR_I5_END -> sdksForI5()
                // Robolectric 4.8-4.9 uses preinstrumented version i4
                major == ROBOLECTRIC_MAJOR_V4 &&
                    minor in ROBOLECTRIC_MINOR_I4_START..ROBOLECTRIC_MINOR_I4_END -> sdksForI4()
                // Default to i7 for unknown versions
                else -> sdksForI7()
            }
        }

        /** SDKs for Robolectric 4.14+ with preinstrumented version i7. */
        private fun sdksForI7(): List<RobolectricSdk> =
            listOf(
                RobolectricSdk(API_KITKAT, "4.4_r1", "r2", PREINSTRUMENTED_V7),
                RobolectricSdk(API_LOLLIPOP, "5.0.2_r3", "r0", PREINSTRUMENTED_V7),
                RobolectricSdk(API_LOLLIPOP_MR1, "5.1.1_r9", "r2", PREINSTRUMENTED_V7),
                RobolectricSdk(API_MARSHMALLOW, "6.0.1_r3", "r1", PREINSTRUMENTED_V7),
                RobolectricSdk(API_NOUGAT, "7.0.0_r1", "r1", PREINSTRUMENTED_V7),
                RobolectricSdk(API_NOUGAT_MR1, "7.1.0_r7", "r1", PREINSTRUMENTED_V7),
                RobolectricSdk(API_OREO, "8.0.0_r4", "r1", PREINSTRUMENTED_V7),
                RobolectricSdk(API_OREO_MR1, "8.1.0", "4611349", PREINSTRUMENTED_V7),
                RobolectricSdk(API_PIE, "9", "4913185-2", PREINSTRUMENTED_V7),
                RobolectricSdk(API_Q, "10", "5803371", PREINSTRUMENTED_V7),
                RobolectricSdk(API_R, "11", "6757853", PREINSTRUMENTED_V7),
                RobolectricSdk(API_S, "12", "7732740", PREINSTRUMENTED_V7),
                RobolectricSdk(API_S_V2, "12.1", "8229987", PREINSTRUMENTED_V7),
                RobolectricSdk(API_TIRAMISU, "13", "9030017", PREINSTRUMENTED_V7),
                RobolectricSdk(API_UPSIDE_DOWN_CAKE, "14", "10818077", PREINSTRUMENTED_V7),
                RobolectricSdk(API_VANILLA_ICE_CREAM, "15", "12650502", PREINSTRUMENTED_V7),
            )

        /** SDKs for Robolectric 4.12-4.13 with preinstrumented version i6. */
        private fun sdksForI6(): List<RobolectricSdk> =
            listOf(
                RobolectricSdk(API_KITKAT, "4.4_r1", "r2", PREINSTRUMENTED_V6),
                RobolectricSdk(API_LOLLIPOP, "5.0.2_r3", "r0", PREINSTRUMENTED_V6),
                RobolectricSdk(API_LOLLIPOP_MR1, "5.1.1_r9", "r2", PREINSTRUMENTED_V6),
                RobolectricSdk(API_MARSHMALLOW, "6.0.1_r3", "r1", PREINSTRUMENTED_V6),
                RobolectricSdk(API_NOUGAT, "7.0.0_r1", "r1", PREINSTRUMENTED_V6),
                RobolectricSdk(API_NOUGAT_MR1, "7.1.0_r7", "r1", PREINSTRUMENTED_V6),
                RobolectricSdk(API_OREO, "8.0.0_r4", "r1", PREINSTRUMENTED_V6),
                RobolectricSdk(API_OREO_MR1, "8.1.0", "4611349", PREINSTRUMENTED_V6),
                RobolectricSdk(API_PIE, "9", "4913185-2", PREINSTRUMENTED_V6),
                RobolectricSdk(API_Q, "10", "5803371", PREINSTRUMENTED_V6),
                RobolectricSdk(API_R, "11", "6757853", PREINSTRUMENTED_V6),
                RobolectricSdk(API_S, "12", "7732740", PREINSTRUMENTED_V6),
                RobolectricSdk(API_S_V2, "12.1", "8229987", PREINSTRUMENTED_V6),
                RobolectricSdk(API_TIRAMISU, "13", "9030017", PREINSTRUMENTED_V6),
                RobolectricSdk(API_UPSIDE_DOWN_CAKE, "14", "10818077", PREINSTRUMENTED_V6),
            )

        /** SDKs for Robolectric 4.10-4.11 with preinstrumented version i5. */
        private fun sdksForI5(): List<RobolectricSdk> =
            listOf(
                RobolectricSdk(API_KITKAT, "4.4_r1", "r2", PREINSTRUMENTED_V5),
                RobolectricSdk(API_LOLLIPOP, "5.0.2_r3", "r0", PREINSTRUMENTED_V5),
                RobolectricSdk(API_LOLLIPOP_MR1, "5.1.1_r9", "r2", PREINSTRUMENTED_V5),
                RobolectricSdk(API_MARSHMALLOW, "6.0.1_r3", "r1", PREINSTRUMENTED_V5),
                RobolectricSdk(API_NOUGAT, "7.0.0_r1", "r1", PREINSTRUMENTED_V5),
                RobolectricSdk(API_NOUGAT_MR1, "7.1.0_r7", "r1", PREINSTRUMENTED_V5),
                RobolectricSdk(API_OREO, "8.0.0_r4", "r1", PREINSTRUMENTED_V5),
                RobolectricSdk(API_OREO_MR1, "8.1.0", "4611349", PREINSTRUMENTED_V5),
                RobolectricSdk(API_PIE, "9", "4913185-2", PREINSTRUMENTED_V5),
                RobolectricSdk(API_Q, "10", "5803371", PREINSTRUMENTED_V5),
                RobolectricSdk(API_R, "11", "6757853", PREINSTRUMENTED_V5),
                RobolectricSdk(API_S, "12", "7732740", PREINSTRUMENTED_V5),
                RobolectricSdk(API_S_V2, "12.1", "8229987", PREINSTRUMENTED_V5),
                RobolectricSdk(API_TIRAMISU, "13", "9030017", PREINSTRUMENTED_V5),
            )

        /** SDKs for Robolectric 4.8-4.9 with preinstrumented version i4. */
        private fun sdksForI4(): List<RobolectricSdk> =
            listOf(
                RobolectricSdk(API_KITKAT, "4.4_r1", "r2", PREINSTRUMENTED_V4),
                RobolectricSdk(API_LOLLIPOP, "5.0.2_r3", "r0", PREINSTRUMENTED_V4),
                RobolectricSdk(API_LOLLIPOP_MR1, "5.1.1_r9", "r2", PREINSTRUMENTED_V4),
                RobolectricSdk(API_MARSHMALLOW, "6.0.1_r3", "r1", PREINSTRUMENTED_V4),
                RobolectricSdk(API_NOUGAT, "7.0.0_r1", "r1", PREINSTRUMENTED_V4),
                RobolectricSdk(API_NOUGAT_MR1, "7.1.0_r7", "r1", PREINSTRUMENTED_V4),
                RobolectricSdk(API_OREO, "8.0.0_r4", "r1", PREINSTRUMENTED_V4),
                RobolectricSdk(API_OREO_MR1, "8.1.0", "4611349", PREINSTRUMENTED_V4),
                RobolectricSdk(API_PIE, "9", "4913185-2", PREINSTRUMENTED_V4),
                RobolectricSdk(API_Q, "10", "5803371", PREINSTRUMENTED_V4),
                RobolectricSdk(API_R, "11", "6757853", PREINSTRUMENTED_V4),
                RobolectricSdk(API_S, "12", "7732740", PREINSTRUMENTED_V4),
                RobolectricSdk(API_S_V2, "12.1", "8229987", PREINSTRUMENTED_V4),
                RobolectricSdk(API_TIRAMISU, "13", "9030017", PREINSTRUMENTED_V4),
            )
    }
}
