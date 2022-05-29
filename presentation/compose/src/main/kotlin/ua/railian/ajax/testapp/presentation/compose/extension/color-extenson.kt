package ua.railian.ajax.testapp.presentation.compose.extension

import androidx.compose.ui.graphics.Color

fun Color.Companion.random(
    red: IntRange = 0..0xFF,
    green: IntRange = 0..0xFF,
    blue: IntRange = 0..0xFF,
    alpha: IntRange = 0xFF..0xFF
): Color = Color(
    red = red.random(),
    green = green.random(),
    blue = blue.random(),
    alpha = alpha.random()
)

fun Color.Companion.random(
    string: String
): Color = Color(
    color = string.hashCode() or 0xFF000000.toInt()
)
