package ua.railian.ajax.testapp.gradle

object Module {

    const val domainContract = ":domain:contract"
    const val domainLogic = ":domain:logic"

    const val dependencyResolver = ":dependency-resolver"

    const val implementationDatabaseRoom = ":implementation:database:room"
    const val implementationNetworkKtor = ":implementation:network:ktor"

    const val presentationClassicView = ":presentation:classic-view"
    const val presentationCompose = ":presentation:compose"
}