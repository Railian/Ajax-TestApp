package ua.railian.ajax.testapp.impl.db.room.preference

import android.content.Context
import androidx.core.content.edit
import javax.inject.Inject

class DatabasePreferences @Inject constructor(
    applicationContext: Context
) {

    companion object {
        private const val PREFERENCES_NAME = "database-preferences"
        private const val PREF_IS_CONTACT_DATABASE_INITIALIZED = "pref:isContactDatabaseInitialized"
    }

    private val sharedPreferences = applicationContext
        .getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    var isContactDatabaseInitialized: Boolean
        get() = sharedPreferences.getBoolean(PREF_IS_CONTACT_DATABASE_INITIALIZED, false)
        set(value) = sharedPreferences
            .edit { putBoolean(PREF_IS_CONTACT_DATABASE_INITIALIZED, value) }
}