# Tarif Chakder — Portfolio (Kotlin Multiplatform)

A personal portfolio application built with Kotlin Multiplatform and Compose Multiplatform, targeting:
- Android
- iOS
- Desktop (JVM)
- Web (JS)
- WebAssembly (Wasm)

Single codebase, native UI with Compose across platforms.

## Tech stack
- Kotlin Multiplatform
- JetBrains Compose Multiplatform (Material 3)
- Android, iOS (Xcode entry app), Desktop (JVM), Web (JS), Wasm
- Gradle Kotlin DSL

## Project structure
- composeApp/src/commonMain — shared UI and logic (e.g., App.kt, Greeting.kt)
- composeApp/src/androidMain — Android-specific code and resources
- composeApp/src/iosMain — iOS framework bindings used by the Xcode app
- composeApp/src/jvmMain — Desktop (JVM) entry point
- composeApp/src/jsMain — Web (JS) target
- composeApp/src/wasmJsMain — WebAssembly (Wasm) target
- composeApp/src/webMain — Static web resources (index.html, styles.css)
- iosApp — Xcode project that embeds the shared KMP framework

## Prerequisites
- Android Studio (or IntelliJ IDEA) with Kotlin/Compose support
- JDK 17 or 11 installed (project compiles with Java 11 target)
- Android SDK for Android builds
- Xcode (15+) for iOS builds
- Recent Chrome/Safari/Firefox for Web/Wasm

## Build and run

### Android
Use IDE run configuration or build from terminal:
- macOS/Linux:
  ```bash
  ./gradlew :composeApp:assembleDebug
  ```
- Windows:
  ```bash
  .\gradlew.bat :composeApp:assembleDebug
  ```
To install on a connected device/emulator:
- macOS/Linux: `./gradlew :composeApp:installDebug`
- Windows: `.\gradlew.bat :composeApp:installDebug`

### Desktop (JVM)
Run directly:
- macOS/Linux:
  ```bash
  ./gradlew :composeApp:run
  ```
- Windows:
  ```bash
  .\gradlew.bat :composeApp:run
  ```

### Web
Development servers:
- Wasm (faster; modern browsers):
  - macOS/Linux:
    ```bash
    ./gradlew :composeApp:wasmJsBrowserDevelopmentRun
    ```
  - Windows:
    ```bash
    .\gradlew.bat :composeApp:wasmJsBrowserDevelopmentRun
    ```
- JS (older browser support):
  - macOS/Linux:
    ```bash
    ./gradlew :composeApp:jsBrowserDevelopmentRun
    ```
  - Windows:
    ```bash
    .\gradlew.bat :composeApp:jsBrowserDevelopmentRun
    ```
Production distributions (output in build dirs):
- Wasm: `./gradlew :composeApp:wasmJsBrowserDistribution`
- JS: `./gradlew :composeApp:jsBrowserDistribution`

### iOS
Open the Xcode project and run on a simulator or device:
- Path: [iosApp/iosApp.xcodeproj](./iosApp/iosApp.xcodeproj)
- The shared KMP framework is produced by the Gradle iOS targets (iosArm64/iosSimulatorArm64).

## Package desktop installers (optional)
Compose Desktop native distributions are configured. To build for your OS:
```bash
./gradlew :composeApp:createDistributable
```
See compose.desktop config in composeApp/build.gradle.kts for formats.

## App ID and package
- Android applicationId: `com.tarifchakder`
- Common package: `com.tarifchakder`

## Notes
- This project uses Compose hot reload in supported IDEs.
- Web resources live in composeApp/src/webMain/resources.

## License
No license specified.