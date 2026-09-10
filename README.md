# Aayush Tech — Daily Life Companion

> An Android app that gathers the apps and tools you reach for every day: a
> quick launcher for popular apps plus built-in utilities like a flashlight, QR
> scanner, unit converter, notes, and password generator.

## Features

- 🚀 **App shortcut launcher** — one-tap launch for 12+ popular apps (with
  Play Store fallback when an app isn't installed)
- 🔦 **Flashlight** toggle
- 📷 **QR / barcode scanner** (ZXing)
- 📏 **Unit converter**
- 📝 **Quick notes** — save & load
- 🎨 **Random color generator** with hex codes
- 🔑 **Password generator** with copy-to-clipboard
- 📊 **In-app survey / feedback**
- ℹ️ **About** section with developer info

## Getting Started

```bash
cd App/AayushTech
./gradlew assembleDebug
# APK → app/build/outputs/apk/
```

Or open the project in **Android Studio**, sync Gradle, and build/run on a
device or emulator (`minSdk 21`, `targetSdk 34`).

## Tech Stack

- **Java**, **Android SDK**, **Gradle** (Android Gradle Plugin 4.2.2)
- **Material Components**, **AndroidX**
- **ZXing** for QR scanning

## Project Structure

```
└── App/AayushTech/
    ├── app/               # application module (src/)
    ├── build.gradle, settings.gradle
    └── README.md          # detailed build instructions
```

See [App/AayushTech/README.md](App/AayushTech/README.md) for full build
instructions, permissions, and roadmap.

## License

See the LICENSE file in this repository.