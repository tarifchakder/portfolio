# Tarif Chakder Portfolio

A personal portfolio application built with Kotlin Multiplatform and Compose Multiplatform.

The app shares one UI codebase across:
- Android
- iOS
- Desktop (JVM)
- Web (JS)
- WebAssembly (Wasm)

This project is under development. Contributions are welcome through pull requests.

## What the app includes

- Responsive portfolio layout for compact, medium, and expanded screens
- Sidebar profile card with contact and social actions
- Theme toggle with light/dark support
- Top navigation for `About`, `Resume`, `Work`, and `Blog`
- Resume section with experience, education, and skills
- Shared Compose UI in `commonMain`

Current state:
- `About` and `Resume` are implemented
- `Work` and `Blog` currently use placeholder content

## Tech stack

- Kotlin Multiplatform
- JetBrains Compose Multiplatform
- Material 3
- Gradle Kotlin DSL

## Project structure

- `composeApp/src/commonMain` shared UI, navigation, theme, and app logic
- `composeApp/src/androidMain` Android-specific entry code
- `composeApp/src/iosMain` iOS framework bindings
- `composeApp/src/jvmMain` desktop entry point
- `composeApp/src/jsMain` JavaScript web target
- `composeApp/src/wasmJsMain` WebAssembly target
- `composeApp/src/webMain/resources` static web resources
- `iosApp` Xcode host app for the shared iOS framework

## Requirements

- JDK 11 or newer
- Android Studio or IntelliJ IDEA
- Android SDK for Android builds
- Xcode for iOS builds

## Run locally

### Android

```bash
./gradlew :composeApp:assembleDebug
./gradlew :composeApp:installDebug
```

### Desktop

```bash
./gradlew :composeApp:run
```

### Web (JS)

```bash
./gradlew :composeApp:jsBrowserDevelopmentRun
```

### WebAssembly

```bash
./gradlew :composeApp:wasmJsBrowserDevelopmentRun
```

### iOS

Open [iosApp/iosApp.xcodeproj](/Users/tarif/workspace/portfolio/iosApp/iosApp.xcodeproj) in Xcode and run the `iosApp` scheme on a simulator or device.

## Build distributions

### Desktop installers

```bash
./gradlew :composeApp:createDistributable
```

Configured desktop formats:
- `dmg`
- `msi`
- `deb`

### Web production bundles

```bash
./gradlew :composeApp:jsBrowserDistribution
./gradlew :composeApp:wasmJsBrowserDistribution
```

## Notes

- The Android application ID is `com.tarifchakder`
- The desktop main class is `com.tarifchakder.MainKt`
- Local machine files such as `local.properties`, Xcode user data, and Gradle caches are intentionally ignored
- The resume download action in the app points to `https://raw.githubusercontent.com/tarifchakder/portfolio/main/composeApp/src/commonMain/composeResources/files/Md_Tarif_Chakder_Resume.pdf` (bundled in `composeApp/src/commonMain/composeResources/files/Md_Tarif_Chakder_Resume.pdf` and `composeApp/src/webMain/resources/Md_Tarif_Chakder_Resume.pdf`)

## License

This project is licensed under the MIT License. See [LICENSE](/Users/tarif/workspace/portfolio/LICENSE).
