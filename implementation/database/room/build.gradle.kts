import ua.railian.ajax.testapp.gradle.Module
import ua.railian.ajax.testapp.gradle.Version

plugins {
    id("com.android.library")
    kotlin(module = "android")
    kotlin(module = "kapt")
}

android {
    namespace = "ua.railian.ajax.testapp.impl.db.room"
    compileSdk = 32

    defaultConfig {
        minSdk = 23
        targetSdk = 32

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            }
        }

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

    api("androidx.room:room-runtime:${Version.room}")
    api("androidx.room:room-ktx:${Version.room}")
    kapt("androidx.room:room-compiler:${Version.room}")

    implementation("androidx.core:core-ktx:${Version.androidCore}")

    testImplementation(kotlin(module = "test"))
    testImplementation(kotlin(module = "test-junit"))
    testImplementation("androidx.room:room-testing:${Version.room}")

    androidTestImplementation("androidx.test.ext:junit:${Version.testExtJUnit}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Version.testEspresso}")
}