package ua.railian.ajax.testapp.impl.network.mapper

import ua.railian.ajax.testapp.domain.contract.entity.Contact
import ua.railian.ajax.testapp.impl.network.entity.ContactResponse

internal fun ContactResponse.adapt(): Contact = Contact(
    id = 0,
    firstName = name.first,
    lastName = name.last,
    email = email,
    picture = picture.adapt()
)