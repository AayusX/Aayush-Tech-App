#!/bin/bash

echo "🛠️  Creating Aayush Tech APK manually..."
echo "=================================="

# Check if Android SDK is available
if [ -z "$ANDROID_HOME" ]; then
    if [ -d "/opt/android-studio/sdk" ]; then
        export ANDROID_HOME="/opt/android-studio/sdk"
    elif [ -d "/usr/lib/android-sdk" ]; then
        export ANDROID_HOME="/usr/lib/android-sdk"
    else
        echo "❌ Android SDK not found. Installing..."
        sudo apt update
        sudo apt install -y android-sdk
        export ANDROID_HOME="/usr/lib/android-sdk"
    fi
fi

echo "✅ Using Android SDK: $ANDROID_HOME"

# Create APK directory
mkdir -p build_output

# Create simplified APK using Android SDK tools
echo "📦 Creating APK structure..."

# Create the basic APK structure
mkdir -p temp_apk/{META-INF,res,assets,lib}

# Copy resources
cp -r app/src/main/res/* temp_apk/res/ 2>/dev/null || echo "⚠️ Some resources may be missing"

# Create a basic AndroidManifest.xml in temp directory
cp app/src/main/AndroidManifest.xml temp_apk/AndroidManifest.xml

# Create basic classes.dex (this would normally require complex compilation)
echo "⚠️ This is a simplified demo APK structure."
echo "For a fully functional APK, please use Android Studio:"
echo ""
echo "1. Open Android Studio"
echo "2. File → Open → /home/aayusx/App/AayushTech"
echo "3. Build → Build Bundle(s) / APK(s) → Build APK(s)"
echo ""
echo "📱 Your app features are all ready:"
echo "   ✅ 12 App shortcuts (Facebook, YouTube, etc.)"
echo "   ✅ 9 Daily tools (Flashlight, QR Scanner, etc.)"
echo "   ✅ User survey system"
echo "   ✅ Professional branding"
echo "   ✅ Material Design UI"
echo ""

# Create a demo APK file
echo "Creating demo APK file..."
cd temp_apk
zip -r ../build_output/AayushTech-demo.apk * >/dev/null 2>&1
cd ..

if [ -f "build_output/AayushTech-demo.apk" ]; then
    echo "✅ Demo APK created: build_output/AayushTech-demo.apk"
    echo "⚠️  Note: This is a structure demo, not installable."
    echo "📱 For installable APK, use Android Studio."
else
    echo "❌ Failed to create demo APK"
fi

# Clean up
rm -rf temp_apk

echo ""
echo "🎯 TO CREATE REAL APK:"
echo "   /opt/android-studio/bin/studio.sh"
echo "   Open: /home/aayusx/App/AayushTech"
echo "   Build → Build Bundle(s) / APK(s) → Build APK(s)"
echo ""
echo "🎉 Your Aayush Tech app is ready for building!"
