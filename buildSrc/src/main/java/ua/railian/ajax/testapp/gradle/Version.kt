package ua.railian.ajax.testapp.gradle

import org.gradle.api.JavaVersion

object Version {

    val java = JavaVersion.VERSION_1_8
    const val jvm = "1.8"

    const val kotlin = "1.6.10"
    const val kotlinCoroutines = "1.6.1"

    const val inject = "1"
    const val daggerHilt = "2.42"

    const val room = "2.4.2"

    const val ktor = "2.0.1"
    const val logback = "1.2.7"

    const val androidCore = "1.7.0"
    const val androidLifecycle = "2.4.1"
    const val androidActivity = "1.4.0"
    const val androidAppcompat = "1.4.1"
    const val androidMaterial = "1.6.0"

    const val composeUi = "1.1.1"
    const val composeMaterial = "1.1.1"
    const val composeCoil = "2.1.0"

    const val navigationCompose = "2.4.2"
    const val hiltNavigationCompose = "1.0.0"

    const val accompanist = "0.23.1"

    const val testExtJUnit = "1.1.3"
    const val testEspresso = "1.1.1"
}