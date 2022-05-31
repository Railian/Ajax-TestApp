package ua.railian.ajax.testapp.presentation.compose.extension

import ua.railian.ajax.testapp.domain.contract.entity.DayNightMode

val DayNightMode.toggle: DayNightMode
    get() = DayNightMode.values().let { it[(ordinal + 1) % it.size] }