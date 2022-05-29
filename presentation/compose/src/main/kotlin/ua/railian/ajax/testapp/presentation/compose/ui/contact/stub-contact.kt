package ua.railian.ajax.testapp.presentation.compose.ui.contact.list

import ua.railian.ajax.testapp.domain.contract.entity.Contact
import ua.railian.ajax.testapp.domain.contract.entity.Picture

internal val stubContact = Contact(
    id = 1,
    firstName = "Jon",
    lastName = "Doe",
    email = "jon.doe@mail.com",
    picture = Picture(
        large = null,
        medium = null,
        thumbnail = null
    )
)