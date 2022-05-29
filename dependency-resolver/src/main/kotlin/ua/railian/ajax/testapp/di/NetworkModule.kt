package ua.railian.ajax.testapp.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import ua.railian.ajax.testapp.domain.contract.datasource.RemoteContactDataSource
import ua.railian.ajax.testapp.impl.network.api.KtorContactApi
import ua.railian.ajax.testapp.impl.network.client.buildHttpClient

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    companion object {
        @Provides
        fun provideHttpClient(
        ): HttpClient = buildHttpClient(
            debugMode = BuildConfig.DEBUG
        )
    }

    @Binds
    fun bindRemoteContactDataSource(
        impl: KtorContactApi
    ): RemoteContactDataSource
}