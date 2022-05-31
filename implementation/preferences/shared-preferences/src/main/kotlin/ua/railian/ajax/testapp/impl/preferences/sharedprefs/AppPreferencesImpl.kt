package ua.railian.ajax.testapp.impl.preferences.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import ua.railian.ajax.testapp.domain.contract.datasource.preference.AppPreferences
import ua.railian.ajax.testapp.domain.contract.entity.DayNightMode
import ua.railian.ajax.testapp.impl.preferences.sharedprefs.extension.getEnum
import ua.railian.ajax.testapp.impl.preferences.sharedprefs.extension.putEnum
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class AppPreferencesImpl @Inject constructor(
    @ApplicationContext applicationContext: Context
) : AppPreferences {

    companion object {
        private const val PREFERENCES_NAME = "app-preferences"
        private const val PREF_DAY_NIGHT_MODE = "pref:dayNightMode"
        private const val PREF_IS_LOCAL_CONTACT_DATA_SOURCE_INITIALIZED = "pref:isLocalContactDataSourceInitialized"
    }

    private val sharedPreferences = applicationContext
        .getSharedPreferences(
            /* name = */ PREFERENCES_NAME,
            /* mode = */ Context.MODE_PRIVATE
        )

    override var dayNightMode: DayNightMode
        get() = sharedPreferences.getEnum(PREF_DAY_NIGHT_MODE, DayNightMode.System)
        set(value) = run { sharedPreferences.edit { putEnum(PREF_DAY_NIGHT_MODE, value) } }

    override val dayNightModeFlow: Flow<DayNightMode>
        get() = callbackFlow {
            val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
                if (key == "$PREF_DAY_NIGHT_MODE:ordinal") trySend(dayNightMode)
            }
            sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
            awaitClose { sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener) }
        }

    override fun dayNightModeFlow(scope: CoroutineScope): StateFlow<DayNightMode> =
        dayNightModeFlow.stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(stopTimeout = 5.seconds),
            initialValue = dayNightMode
        )

    override var isLocalContactDataSourceInitialized: Boolean
        get() = sharedPreferences.getBoolean(PREF_IS_LOCAL_CONTACT_DATA_SOURCE_INITIALIZED, false)
        set(value) = sharedPreferences
            .edit { putBoolean(PREF_IS_LOCAL_CONTACT_DATA_SOURCE_INITIALIZED, value) }
}
