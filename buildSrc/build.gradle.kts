plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin(module = "test"))
    testImplementation(kotlin(module = "test-junit"))
}