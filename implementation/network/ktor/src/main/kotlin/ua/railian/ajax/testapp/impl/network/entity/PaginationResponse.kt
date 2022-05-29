package ua.railian.ajax.testapp.impl.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ContactsResponse(
    @SerialName("results") val items: List<ContactResponse>,
    @SerialName("info") val pageInfo: PageInfoResponse
)