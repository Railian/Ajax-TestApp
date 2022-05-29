package ua.railian.ajax.testapp.domain.contract.entity

data class Contact(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val picture: Picture
)