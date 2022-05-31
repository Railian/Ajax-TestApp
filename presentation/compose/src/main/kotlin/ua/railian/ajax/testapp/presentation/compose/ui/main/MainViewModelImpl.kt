package ua.railian.ajax.testapp.presentation.compose.ui.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import ua.railian.ajax.testapp.domain.contract.datasource.preference.AppPreferences
import ua.railian.ajax.testapp.domain.contract.entity.DayNightMode
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val appPreferences: AppPreferences
) : MainViewModel() {

    override val dayNightMode: StateFlow<DayNightMode>
        get() = appPreferences.dayNightModeFlow(viewModelScope)

    override fun changeDayNightMode(mode: DayNightMode) {
        appPreferences.dayNightMode = mode
    }
}