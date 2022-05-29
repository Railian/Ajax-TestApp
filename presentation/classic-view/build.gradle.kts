import ua.railian.ajax.testapp.gradle.Module
import ua.railian.ajax.testapp.gradle.Version

plugins {
    id("com.android.library")
    kotlin(module = "android")
    kotlin(module = "kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "ua.railian.ajax.testapp.presentation.classicview"
    compileSdk = 32

    defaultConfig {
        minSdk = 23
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures {
        viewBinding = true
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

    implementation("com.google.dagger:hilt-android:${Version.daggerHilt}")
    kapt("com.google.dagger:hilt-android-compiler:${Version.daggerHilt}")

    implementation("androidx.core:core-ktx:${Version.androidCore}")
    implementation("androidx.appcompat:appcompat:${Version.androidAppcompat}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Version.androidLifecycle}")
    implementation("com.google.android.material:material:${Version.androidMaterial}")

    testImplementation(kotlin(module = "test"))
    testImplementation(kotlin(module = "test-junit"))

    androidTestImplementation("androidx.test.ext:junit:${Version.testExtJUnit}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Version.testEspresso}")
}