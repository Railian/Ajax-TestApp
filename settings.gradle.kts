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

include(":dependency-resolver")

include(":implementation:preferences:shared-preferences")
include(":implementation:database:room")
include(":implementation:network:ktor")

include(":presentation:classic-view")
include(":presentation:compose")