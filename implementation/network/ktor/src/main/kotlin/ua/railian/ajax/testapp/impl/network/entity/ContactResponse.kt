package ua.railian.ajax.testapp.impl.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ContactResponse(
    @SerialName("name") val name: NameResponse,
    @SerialName("email") val email: String,
    @SerialName("picture") val picture: PictureResponse
)