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
        assertTrue("Should have SDKs for 4.14", sdks.isNotEmpty())
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
    }

    @Test
    fun sdksForRobolectric412HasCorrectVersions() {
        val sdks = RobolectricSdk.sdksForRobolectricVersion("4.12")
        assertTrue("Should have SDKs for 4.12", sdks.isNotEmpty())
        // All SDKs should have preinstrumented version 6
        sdks.forEach { sdk ->
            assertEquals(
                "SDK $sdk should have preinstrumented version 6",
                6,
                sdk.preinstrumentedVersion,
            )
        }
    }

    @Test
    fun sdksForRobolectric410HasCorrectVersions() {
        val sdks = RobolectricSdk.sdksForRobolectricVersion("4.10")
        assertTrue("Should have SDKs for 4.10", sdks.isNotEmpty())
        // All SDKs should have preinstrumented version 5
        sdks.forEach { sdk ->
            assertEquals(
                "SDK $sdk should have preinstrumented version 5",
                5,
                sdk.preinstrumentedVersion,
            )
        }
    }

    @Test
    fun sdksForRobolectric48HasCorrectVersions() {
        val sdks = RobolectricSdk.sdksForRobolectricVersion("4.8")
        assertTrue("Should have SDKs for 4.8", sdks.isNotEmpty())
        // All SDKs should have preinstrumented version 4
        sdks.forEach { sdk ->
            assertEquals(
                "SDK $sdk should have preinstrumented version 4",
                4,
                sdk.preinstrumentedVersion,
            )
        }
    }

    @Test
    fun sdksForFutureVersionDefaultsToI7() {
        val sdks = RobolectricSdk.sdksForRobolectricVersion("5.0")
        assertTrue("Should have SDKs for 5.0", sdks.isNotEmpty())
        // All SDKs should have preinstrumented version 7
        sdks.forEach { sdk ->
            assertEquals(
                "SDK $sdk should have preinstrumented version 7",
                7,
                sdk.preinstrumentedVersion,
            )
        }
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
