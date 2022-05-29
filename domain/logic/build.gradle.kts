import ua.railian.ajax.testapp.gradle.Module
import ua.railian.ajax.testapp.gradle.Version

plugins {
    id("java-library")
    kotlin(module = "jvm")
    kotlin(module = "kapt")
}

java {
    sourceCompatibility = Version.java
    targetCompatibility = Version.java
}

dependencies {

    api(project(Module.domainContract))

    testImplementation(kotlin(module = "test"))
    testImplementation(kotlin(module = "test-junit"))
}