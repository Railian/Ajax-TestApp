package ua.railian.ajax.testapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.railian.ajax.testapp.domain.contract.datasource.preference.AppPreferences
import ua.railian.ajax.testapp.impl.preferences.sharedprefs.AppPreferencesImpl

@Module
@InstallIn(SingletonComponent::class)
interface PreferencesModule {

    @Binds
    fun bindAppPreferences(
        impl: AppPreferencesImpl
    ): AppPreferences
}