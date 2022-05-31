package ua.railian.ajax.testapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ua.railian.ajax.testapp.domain.contract.datasource.LocalContactDataSource
import ua.railian.ajax.testapp.domain.contract.datasource.preference.AppPreferences
import ua.railian.ajax.testapp.impl.db.room.database.AjaxDatabase
import ua.railian.ajax.testapp.impl.db.room.database.buildDatabase
import ua.railian.ajax.testapp.impl.db.room.mapper.adapt

@Module
@InstallIn(SingletonComponent::class)
interface DatabaseModule {

    companion object {

        @Provides
        fun provideAjaxDatabase(
            @ApplicationContext applicationContext: Context
        ): AjaxDatabase = buildDatabase(applicationContext)

        @Provides
        fun provideLocalPostDataSource(
            database: AjaxDatabase,
            appPreferences: AppPreferences
        ): LocalContactDataSource = database.contactDao.adapt(appPreferences)
    }
}