import ua.railian.ajax.testapp.gradle.Module
import ua.railian.ajax.testapp.gradle.Version

plugins {
    id("com.android.library")
    kotlin(module = "android")
    kotlin(module = "kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "ua.railian.ajax.testapp.presentation.compose"
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.composeUi
    }
}

// Allow references to generated code
kapt { correctErrorTypes = true }

dependencies {

    api(project(Module.domainContract))

    implementation("com.google.dagger:hilt-android:${Version.daggerHilt}")
    kapt("com.google.dagger:hilt-android-compiler:${Version.daggerHilt}")

    implementation("androidx.core:core-ktx:${Version.androidCore}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Version.androidLifecycle}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Version.androidLifecycle}")
    implementation("androidx.activity:activity-compose:${Version.androidActivity}")
    implementation("androidx.compose.ui:ui:${Version.composeUi}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Version.composeUi}")
    implementation("androidx.compose.material:material:${Version.composeMaterial}")
    implementation("androidx.compose.material:material-icons-extended:${Version.composeMaterial}")
    implementation("androidx.navigation:navigation-compose:${Version.navigationCompose}")
    implementation("androidx.hilt:hilt-navigation-compose:${Version.hiltNavigationCompose}")

    implementation("com.google.accompanist:accompanist-systemuicontroller:${Version.accompanist}")
    implementation("com.google.accompanist:accompanist-swiperefresh:${Version.accompanist}")


    implementation("io.coil-kt:coil-compose:${Version.composeCoil}")

    testImplementation(kotlin(module = "test"))
    testImplementation(kotlin(module = "test-junit"))

    androidTestImplementation("androidx.test.ext:junit:${Version.testExtJUnit}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Version.testEspresso}")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Version.composeUi}")

    debugImplementation("androidx.compose.ui:ui-tooling:${Version.composeUi}")
    debugImplementation("androidx.compose.ui:ui-test-manifest:${Version.composeUi}")
}