package ua.railian.ajax.testapp.impl.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PictureResponse(
    @SerialName("large") val large: String?,
    @SerialName("medium") val medium: String?,
    @SerialName("thumbnail") val thumbnail: String?
)