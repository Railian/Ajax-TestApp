package ua.railian.ajax.testapp.presentation.compose.ui.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import ua.railian.ajax.testapp.domain.contract.entity.DayNightMode

abstract class MainViewModel : ViewModel() {
    abstract val dayNightMode: StateFlow<DayNightMode>
    abstract fun changeDayNightMode(mode: DayNightMode)
}