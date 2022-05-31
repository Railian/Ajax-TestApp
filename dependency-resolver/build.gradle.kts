import ua.railian.ajax.testapp.gradle.Module
import ua.railian.ajax.testapp.gradle.Version

plugins {
    id("com.android.library")
    kotlin(module = "android")
    kotlin(module = "kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "ua.railian.ajax.testapp.di"
    compileSdk = 32

    defaultConfig {
        minSdk = 23
        targetSdk = 32
        consumerProguardFiles("consumer-rules.pro")
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
}

// Allow references to generated code
kapt { correctErrorTypes = true }

dependencies {

    api(project(Module.domainContract))

    implementation(project(Module.domainLogic))
    implementation(project(Module.implementationPreferencesSharedPreferences))
    implementation(project(Module.implementationDatabaseRoom))
    implementation(project(Module.implementationNetworkKtor))

    api("com.google.dagger:hilt-android:${Version.daggerHilt}")
    kapt("com.google.dagger:hilt-android-compiler:${Version.daggerHilt}")
}