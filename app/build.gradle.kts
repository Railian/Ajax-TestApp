import ua.railian.ajax.testapp.gradle.Module
import ua.railian.ajax.testapp.gradle.Version

plugins {
    id("com.android.application")
    kotlin(module = "android")
    kotlin(module = "kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "ua.railian.ajax.testapp"
    compileSdk = 32

    defaultConfig {
        applicationId = "ua.railian.ajax.testapp"
        minSdk = 23
        targetSdk = 32
        versionCode = 1
        versionName = "0.0.1"
        vectorDrawables { useSupportLibrary = true }
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
        sourceCompatibility = Version.java
        targetCompatibility = Version.java
    }
    kotlinOptions {
        jvmTarget = Version.jvm
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

// Allow references to generated code
kapt { correctErrorTypes = true }

dependencies {

    implementation(project(Module.dependencyResolver))

    implementation(project(Module.presentationClassicView))
    implementation(project(Module.presentationCompose))

    implementation("com.google.dagger:hilt-android:${Version.daggerHilt}")
    kapt("com.google.dagger:hilt-android-compiler:${Version.daggerHilt}")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.kotlinCoroutines}")
}