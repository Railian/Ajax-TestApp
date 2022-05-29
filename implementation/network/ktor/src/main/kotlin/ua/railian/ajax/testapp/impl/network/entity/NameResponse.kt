package ua.railian.ajax.testapp.impl.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class NameResponse(
    @SerialName("first") val first: String,
    @SerialName("last") val last: String
)