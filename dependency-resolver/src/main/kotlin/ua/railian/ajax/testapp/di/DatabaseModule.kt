package ua.railian.ajax.testapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ua.railian.ajax.testapp.domain.contract.datasource.LocalContactDataSource
import ua.railian.ajax.testapp.impl.db.room.database.AjaxDatabase
import ua.railian.ajax.testapp.impl.db.room.database.buildDatabase
import ua.railian.ajax.testapp.impl.db.room.mapper.adapt
import ua.railian.ajax.testapp.impl.db.room.preference.DatabasePreferences

@Module
@InstallIn(SingletonComponent::class)
interface DatabaseModule {

    companion object {

        @Provides
        fun provideAjaxDatabase(
            @ApplicationContext applicationContext: Context
        ): AjaxDatabase = buildDatabase(applicationContext)

        @Provides
        fun provideDatabasePreferences(
            @ApplicationContext applicationContext: Context
        ): DatabasePreferences = DatabasePreferences(applicationContext)

        @Provides
        fun provideLocalPostDataSource(
            database: AjaxDatabase,
            databasePreferences: DatabasePreferences,
        ): LocalContactDataSource = database.contactDao.adapt(databasePreferences)
    }

}