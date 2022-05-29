package ua.railian.ajax.testapp.presentation.compose.ui.contact

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

fun Colors.accentSurface(accentColor: Color): Color = if (isLight) accentColor else surface
