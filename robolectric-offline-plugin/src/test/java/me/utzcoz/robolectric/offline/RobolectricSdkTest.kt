package me.utzcoz.robolectric.offline

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class RobolectricSdkTest {
    @Test
    fun sdkCoordinatesAreCorrect() {
        val sdk = RobolectricSdk(29, "10", "5803371", 7)
        assertEquals("org.robolectric", sdk.groupId)
        assertEquals("android-all-instrumented", sdk.artifactId)
        assertEquals("10-robolectric-5803371-i7", sdk.version)
        assertEquals(
            "org.robolectric:android-all-instrumented:10-robolectric-5803371-i7",
            sdk.coordinates,
        )
    }

    @Test
    fun sdksForRobolectric414HasCorrectVersions() {
        val sdks = RobolectricSdk.sdksForRobolectricVersion("4.14")
        // 4.14 supports SDK 21-35 (15 SDKs)
        assertEquals("Should have 15 SDKs for 4.14", 15, sdks.size)
        // All SDKs should have preinstrumented version 7
        sdks.forEach { sdk ->
            assertEquals(
                "SDK $sdk should have preinstrumented version 7",
                7,
                sdk.preinstrumentedVersion,
            )
        }
        // Should include Android 10 (API 29)
        val android10 = sdks.find { it.apiLevel == 29 }
        assertTrue("Should include Android 10 (API 29)", android10 != null)
        assertEquals("10", android10!!.androidVersion)
        // Should include Android 15 (API 35)
        val android15 = sdks.find { it.apiLevel == 35 }
        assertTrue("Should include Android 15 (API 35)", android15 != null)
        assertEquals("15", android15!!.androidVersion)
        assertEquals("12650502", android15.robolectricVersion)
        // API range should be 21-35
        assertEquals(21, sdks.minOf { it.apiLevel })
        assertEquals(35, sdks.maxOf { it.apiLevel })
    }

    @Test
    fun sdksForRobolectric415HasCorrectVersions() {
        val sdks = RobolectricSdk.sdksForRobolectricVersion("4.15")
        // 4.15 has same SDK list as 4.14: SDK 21-35 (15 SDKs)
        assertEquals("Should have 15 SDKs for 4.15", 15, sdks.size)
        // All SDKs should have preinstrumented version 7
        sdks.forEach { sdk ->
            assertEquals(
                "SDK $sdk should have preinstrumented version 7",
                7,
                sdk.preinstrumentedVersion,
            )
        }
        // API range should be 21-35
        assertEquals(21, sdks.minOf { it.apiLevel })
        assertEquals(35, sdks.maxOf { it.apiLevel })
    }

    @Test
    fun sdksForRobolectric416HasCorrectVersions() {
        val sdks = RobolectricSdk.sdksForRobolectricVersion("4.16")
        // 4.16 supports SDK 23-36 (14 SDKs)
        assertEquals("Should have 14 SDKs for 4.16", 14, sdks.size)
        // All SDKs should have preinstrumented version 7
        sdks.forEach { sdk ->
            assertEquals(
                "SDK $sdk should have preinstrumented version 7",
                7,
                sdk.preinstrumentedVersion,
            )
        }
        // Should include Android 16 (API 36)
        val android16 = sdks.find { it.apiLevel == 36 }
        assertTrue("Should include Android 16 (API 36)", android16 != null)
        assertEquals("16", android16!!.androidVersion)
        assertEquals("13921718", android16.robolectricVersion)
        // Should NOT include API 21 and 22 (dropped in 4.16)
        val api21 = sdks.find { it.apiLevel == 21 }
        val api22 = sdks.find { it.apiLevel == 22 }
        assertTrue("Should NOT include API 21", api21 == null)
        assertTrue("Should NOT include API 22", api22 == null)
        // API range should be 23-36
        assertEquals(23, sdks.minOf { it.apiLevel })
        assertEquals(36, sdks.maxOf { it.apiLevel })
        // Verify SDK 15 has the updated robolectric version for 4.16
        val android15 = sdks.find { it.apiLevel == 35 }
        assertTrue("Should include Android 15 (API 35)", android15 != null)
        assertEquals("13954326", android15!!.robolectricVersion)
    }

    @Test
    fun sdksForRobolectric4161HasCorrectVersions() {
        val sdks = RobolectricSdk.sdksForRobolectricVersion("4.16.1")
        // 4.16.1 has same SDK list as 4.16: SDK 23-36 (14 SDKs)
        assertEquals("Should have 14 SDKs for 4.16.1", 14, sdks.size)
        // All SDKs should have preinstrumented version 7
        sdks.forEach { sdk ->
            assertEquals(
                "SDK $sdk should have preinstrumented version 7",
                7,
                sdk.preinstrumentedVersion,
            )
        }
        // Should include Android 16 (API 36)
        val android16 = sdks.find { it.apiLevel == 36 }
        assertTrue("Should include Android 16 (API 36)", android16 != null)
        // API range should be 23-36
        assertEquals(23, sdks.minOf { it.apiLevel })
        assertEquals(36, sdks.maxOf { it.apiLevel })
    }

    @Test
    fun sdksForRobolectric412HasCorrectVersions() {
        val sdks = RobolectricSdk.sdksForRobolectricVersion("4.12")
        // 4.12 supports SDK 19-34 (15 SDKs)
        assertEquals("Should have 15 SDKs for 4.12", 15, sdks.size)
        // All SDKs should have preinstrumented version 6
        sdks.forEach { sdk ->
            assertEquals(
                "SDK $sdk should have preinstrumented version 6",
                6,
                sdk.preinstrumentedVersion,
            )
        }
        // API range should be 19-34
        assertEquals(19, sdks.minOf { it.apiLevel })
        assertEquals(34, sdks.maxOf { it.apiLevel })
    }

    @Test
    fun sdksForRobolectric410HasCorrectVersions() {
        val sdks = RobolectricSdk.sdksForRobolectricVersion("4.10")
        // 4.10 supports SDK 19-33 (14 SDKs)
        assertEquals("Should have 14 SDKs for 4.10", 14, sdks.size)
        // All SDKs should have preinstrumented version 5
        sdks.forEach { sdk ->
            assertEquals(
                "SDK $sdk should have preinstrumented version 5",
                5,
                sdk.preinstrumentedVersion,
            )
        }
        // API range should be 19-33
        assertEquals(19, sdks.minOf { it.apiLevel })
        assertEquals(33, sdks.maxOf { it.apiLevel })
    }

    @Test
    fun sdksForRobolectric48HasCorrectVersions() {
        val sdks = RobolectricSdk.sdksForRobolectricVersion("4.8")
        // 4.8 supports SDK 19-33 (14 SDKs)
        assertEquals("Should have 14 SDKs for 4.8", 14, sdks.size)
        // All SDKs should have preinstrumented version 4
        sdks.forEach { sdk ->
            assertEquals(
                "SDK $sdk should have preinstrumented version 4",
                4,
                sdk.preinstrumentedVersion,
            )
        }
        // API range should be 19-33
        assertEquals(19, sdks.minOf { it.apiLevel })
        assertEquals(33, sdks.maxOf { it.apiLevel })
    }

    @Test
    fun sdksForUnknownVersionDefaultsToLatest() {
        val sdks = RobolectricSdk.sdksForRobolectricVersion("5.0")
        // Unknown version defaults to 4.16.1 SDK list
        assertEquals("Should have 14 SDKs for unknown version", 14, sdks.size)
        // All SDKs should have preinstrumented version 7
        sdks.forEach { sdk ->
            assertEquals(
                "SDK $sdk should have preinstrumented version 7",
                7,
                sdk.preinstrumentedVersion,
            )
        }
        // API range should be 23-36 (same as 4.16)
        assertEquals(23, sdks.minOf { it.apiLevel })
        assertEquals(36, sdks.maxOf { it.apiLevel })
    }

    @Test
    fun sdkVersionFormattingIsCorrect() {
        // Test various SDK versions to ensure formatting is correct
        val testCases =
            listOf(
                Triple(19, "4.4_r1", "r2"),
                Triple(29, "10", "5803371"),
                Triple(32, "12.1", "8229987"),
            )

        testCases.forEach { (apiLevel, androidVersion, robolectricVersion) ->
            val sdk = RobolectricSdk(apiLevel, androidVersion, robolectricVersion, 7)
            assertEquals("$androidVersion-robolectric-$robolectricVersion-i7", sdk.version)
        }
    }
}
