// build.gradle.kts (Module :app)

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // Plugin Kapt untuk Room (Commit 3)
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.example.lab_week_10"
    // Diperbaiki: Naikkan ke 36
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.lab_week_10"
        minSdk = 24
        // Diperbaiki: Target SDK sesuai compileSdk
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        // Ganti ke Java 8 (Versi 1.8) agar kompatibel dengan Room
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

// Definisikan versi untuk Room
val roomVersion = "2.6.0"

dependencies {
    // Commit 2: ViewModel dan LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    // Commit 3: Room Database
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")

    // Fragment KTX (Diperlukan untuk Commit 2)
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    // Dependencies yang sudah ada/diasumsikan
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}