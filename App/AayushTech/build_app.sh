#!/bin/bash

echo "==================================="
echo "Building Aayush Tech Android App"
echo "==================================="

# Check if Android SDK is available
if [ -z "$ANDROID_HOME" ]; then
    echo "❌ ANDROID_HOME is not set. Please install Android SDK and set ANDROID_HOME."
    echo "   Example: export ANDROID_HOME=/path/to/android/sdk"
    exit 1
fi

# Check if Java is available
if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed. Please install Java 8 or higher."
    exit 1
fi

echo "✅ Environment check passed"
echo ""

# Clean previous builds
echo "🧹 Cleaning previous builds..."
if ./gradlew clean 2>/dev/null; then
    echo "✅ Clean completed successfully"
else
    echo "⚠️  Gradle wrapper not available. Trying alternative methods..."
    
    # Check if gradle is installed globally
    if command -v gradle &> /dev/null; then
        echo "📦 Using system Gradle installation"
        gradle clean
        
        # Build debug APK
        echo "🔨 Building debug APK..."
        gradle assembleDebug
        
        # Build release APK (optimized)
        echo "🚀 Building release APK..."
        gradle assembleRelease
    else
        echo "❌ Neither Gradle wrapper nor system Gradle found."
        echo "📋 To build this project:"
        echo "   1. Install Android Studio: https://developer.android.com/studio"
        echo "   2. Open this project in Android Studio"
        echo "   3. Build > Build Bundle(s)/APK(s) > Build APK(s)"
        echo "   4. Or install Gradle: https://gradle.org/install/"
        echo ""
        echo "🎯 Alternative: Manual APK creation"
        echo "   This project contains all necessary source files."
        echo "   Use any Android development environment to build."
        exit 1
    fi
fi

echo ""
echo "==================================="
echo "Build Complete!"
echo "==================================="
echo ""

# Check if APKs were created
if [ -f "app/build/outputs/apk/debug/app-debug.apk" ]; then
    echo "✅ Debug APK: app/build/outputs/apk/debug/app-debug.apk"
else
    echo "❌ Debug APK build failed"
fi

if [ -f "app/build/outputs/apk/release/app-release.apk" ]; then
    echo "✅ Release APK: app/build/outputs/apk/release/app-release.apk"
    echo ""
    echo "📱 Install on device:"
    echo "   adb install app/build/outputs/apk/release/app-release.apk"
else
    echo "❌ Release APK build failed"
fi

echo ""
echo "📋 App Information:"
echo "   Name: Aayush Tech"
echo "   Version: 1.0"
echo "   Package: com.aayushtech07.aayushtech"
echo "   Developer: Mr. Aayush Bhandari"
echo "   Company: Aayush-Tech07"
echo ""
echo "🎉 Your daily life companion app is ready!"
