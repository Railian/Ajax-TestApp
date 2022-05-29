package ua.railian.ajax.testapp.impl.network.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import ua.railian.ajax.testapp.domain.contract.datasource.RemoteContactDataSource
import ua.railian.ajax.testapp.domain.contract.entity.Contact
import ua.railian.ajax.testapp.impl.network.entity.ContactsResponse
import ua.railian.ajax.testapp.impl.network.mapper.adapt
import javax.inject.Inject

class KtorContactApi @Inject constructor(
    private val httpClient: HttpClient
) : RemoteContactDataSource {

    override suspend fun getContacts(
        page: Int,
        limit: Int
    ): List<Contact> = httpClient
        .get("/api") {
            parameter("page", page)
            parameter("results", limit)
            parameter("inc", setOf("name", "email", "picture").joinToString(separator = ","))
        }
        .body<ContactsResponse>()
        .items.map { it.adapt() }
}