include(":dependency-resolver")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Ajax TestApp"

include(":app")

include(":domain:contract")
include(":domain:logic")

include(":implementation:database:room")
include(":implementation:network:ktor")

include(":presentation:classic-view")
include(":presentation:compose")