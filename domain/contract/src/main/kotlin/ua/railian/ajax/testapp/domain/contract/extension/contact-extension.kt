package ua.railian.ajax.testapp.domain.contract.extension

import ua.railian.ajax.testapp.domain.contract.entity.Contact

val Contact.fullName: String
    get() = setOf(firstName, lastName)
        .joinToString(separator = " ")