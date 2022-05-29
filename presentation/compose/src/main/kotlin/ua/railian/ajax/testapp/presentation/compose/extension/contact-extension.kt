package ua.railian.ajax.testapp.presentation.compose.extension

import androidx.compose.ui.graphics.Color
import ua.railian.ajax.testapp.domain.contract.entity.Contact
import ua.railian.ajax.testapp.domain.contract.extension.fullName

val Contact.color: Color
    get() = Color.random(fullName)