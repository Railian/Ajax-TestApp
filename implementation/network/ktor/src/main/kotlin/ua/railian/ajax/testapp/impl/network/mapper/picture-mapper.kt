package ua.railian.ajax.testapp.impl.network.mapper

import ua.railian.ajax.testapp.domain.contract.entity.Picture
import ua.railian.ajax.testapp.impl.network.entity.PictureResponse

internal fun PictureResponse.adapt(): Picture = Picture(
    large = large,
    medium = medium,
    thumbnail = thumbnail
)