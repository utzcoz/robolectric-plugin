# robolectric-plugin

A Gradle plugin for downloading preinstrumented android.jar files for Robolectric offline mode.

## Features

- **Configurable Robolectric version**: Specify which Robolectric version's preinstrumented jars to download
- **Version-aware SDK selection**: Automatically selects the correct preinstrumented jar versions based on the Robolectric version (supports 4.8-4.14+)
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

// Optional: Configure the Robolectric version (default is "4.14")
robolectricOffline {
    robolectricVersion.set("4.14")
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

// Optional: Configure the Robolectric version (default is "4.14")
robolectricOffline {
    robolectricVersion = "4.14"
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

| Robolectric Version | Preinstrumented Version |
|---------------------|------------------------|
| 4.14+               | i7                     |
| 4.12-4.13           | i6                     |
| 4.10-4.11           | i5                     |
| 4.8-4.9             | i4                     |

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
