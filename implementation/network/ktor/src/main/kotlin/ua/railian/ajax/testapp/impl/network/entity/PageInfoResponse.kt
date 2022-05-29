package ua.railian.ajax.testapp.impl.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PageInfoResponse(
    @SerialName("page") val pageNumber: Int,
    @SerialName("results") val itemsCount: Int
)