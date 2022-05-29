package ua.railian.ajax.testapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.railian.ajax.testapp.domain.contract.repository.ContactRepository
import ua.railian.ajax.testapp.domain.logic.ContactRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindContactRepository(
        impl: ContactRepositoryImpl
    ): ContactRepository
}