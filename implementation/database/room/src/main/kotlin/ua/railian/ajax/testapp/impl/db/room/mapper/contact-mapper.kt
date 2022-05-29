package ua.railian.ajax.testapp.impl.db.room.mapper

import ua.railian.ajax.testapp.domain.contract.entity.Contact
import ua.railian.ajax.testapp.domain.contract.entity.Picture
import ua.railian.ajax.testapp.impl.db.room.entity.ContactEntity

internal fun ContactEntity.adapt(): Contact = Contact(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    picture = Picture(
        large = pictureLarge,
        medium = pictureMedium,
        thumbnail = pictureThumbnail
    )
)

internal fun Contact.adapt(): ContactEntity = ContactEntity(
    id = id.takeIf { it > 0 } ?: 0,
    firstName = firstName,
    lastName = lastName,
    email = email,
    pictureLarge = picture.large,
    pictureMedium = picture.medium,
    pictureThumbnail = picture.thumbnail
)