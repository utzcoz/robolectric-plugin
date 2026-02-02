# robolectric-plugin

A Gradle plugin for downloading preinstrumented android.jar files for Robolectric offline mode.

## Features

- **Configurable Robolectric version**: Specify which Robolectric version's preinstrumented jars to download
- **Version-aware SDK selection**: Automatically selects the correct preinstrumented jar versions based on the Robolectric version
- **Uses preinstrumented jars only**: Downloads only preinstrumented android.jar files (android-all-instrumented artifacts)
- **CI-friendly**: Can run independently before tests to download and cache dependencies
- **Gradle cache integration**: Downloads are cached in Gradle's dependency cache for offline usage

## Usage

### Apply the Plugin

In your `build.gradle.kts`:

```kotlin
plugins {
    id("me.utzcoz.robolectric.offline") version "0.1-snapshot"
}

repositories {
    mavenCentral()
}

// Optional: Configure the Robolectric version (default is "4.16.1")
robolectricOffline {
    robolectricVersion.set("4.16.1")
}
```

Or in your `build.gradle`:

```groovy
plugins {
    id 'me.utzcoz.robolectric.offline' version '0.1-snapshot'
}

repositories {
    mavenCentral()
}

// Optional: Configure the Robolectric version (default is "4.16.1")
robolectricOffline {
    robolectricVersion = "4.16.1"
}
```

### Run the Download Task

```bash
./gradlew robolectricOffline
```

This will download all preinstrumented android.jar files for the configured Robolectric version to Gradle's dependency cache.

### CI Integration

In your CI pipeline, run the download task before running tests:

```bash
# Download preinstrumented jars
./gradlew robolectricOffline

# Run tests (jars are now cached)
./gradlew test
```

## Supported Robolectric Versions

| Robolectric Version | SDKs (API Levels)   | Preinstrumented Version |
|---------------------|---------------------|------------------------|
| 4.16, 4.16.1        | 23-36               | i7                     |
| 4.14, 4.14.1, 4.15, 4.15.1 | 21-35        | i7                     |
| 4.12, 4.12.1, 4.13, 4.13.1 | 19-34        | i6                     |
| 4.10, 4.10.x, 4.11, 4.11.1 | 19-33        | i5                     |
| 4.8, 4.8.x, 4.9, 4.9.x     | 19-33        | i4                     |

## Build

```
./gradlew build
```

## Test

```
./gradlew test
```

## Publish to mavenLocal

```
./gradlew publishPluginMavenPublicationToMavenLocal
```
