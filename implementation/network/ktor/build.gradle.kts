import ua.railian.ajax.testapp.gradle.Module
import ua.railian.ajax.testapp.gradle.Version

plugins {
    id("com.android.library")
    kotlin(module = "android")
    kotlin(module = "kapt")
    kotlin(module = "plugin.serialization")
}

android {
    namespace = "ua.railian.ajax.testapp.impl.network.ktor"
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
    compileOptions {
        sourceCompatibility = Version.java
        targetCompatibility = Version.java
    }
    kotlinOptions {
        jvmTarget = Version.jvm
    }
}

dependencies {

    api(project(Module.domainContract))

    api("io.ktor:ktor-client-core:${Version.ktor}")
    implementation("io.ktor:ktor-client-cio:${Version.ktor}")
    implementation("io.ktor:ktor-client-okhttp:${Version.ktor}")
    implementation("io.ktor:ktor-client-logging:${Version.ktor}")
    implementation("io.ktor:ktor-client-content-negotiation:${Version.ktor}")
    implementation("io.ktor:ktor-serialization-kotlinx-json:${Version.ktor}")
    implementation("ch.qos.logback:logback-classic:${Version.logback}") // required for ktor-client-logging

    testImplementation(kotlin(module = "test"))
    testImplementation(kotlin(module = "test-junit"))

    androidTestImplementation("androidx.test.ext:junit:${Version.testExtJUnit}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Version.testEspresso}")
}