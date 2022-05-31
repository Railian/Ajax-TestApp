package ua.railian.ajax.testapp.domain.contract.datasource.preference

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import ua.railian.ajax.testapp.domain.contract.entity.DayNightMode

interface AppPreferences {
    var dayNightMode: DayNightMode
    val dayNightModeFlow: Flow<DayNightMode>
    fun dayNightModeFlow(scope: CoroutineScope): StateFlow<DayNightMode>
    var isLocalContactDataSourceInitialized: Boolean
}