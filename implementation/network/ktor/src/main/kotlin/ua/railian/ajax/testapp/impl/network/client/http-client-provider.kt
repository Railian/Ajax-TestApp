package ua.railian.ajax.testapp.impl.network.client

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

fun buildHttpClient(
    debugMode: Boolean = false
) = HttpClient(CIO) {
    install(DefaultRequest) {
        url(urlString = "https://randomuser.me/")
    }
    install(ContentNegotiation) {
        json(json = Json {
            ignoreUnknownKeys = true
        })
    }
    if (debugMode) {
        developmentMode = true
        install(Logging) {
            logger = MessageLengthLimitingLogger(maxLength = 3500, minLength = 3000)
            level = LogLevel.ALL
        }
    }
}