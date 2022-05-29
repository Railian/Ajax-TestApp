import ua.railian.ajax.testapp.gradle.Version

plugins {
    id("java-library")
    kotlin(module = "jvm")
}

java {
    sourceCompatibility = Version.java
    targetCompatibility = Version.java
}

dependencies {
    api("javax.inject:javax.inject:${Version.inject}")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.kotlinCoroutines}")
}