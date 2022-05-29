package ua.railian.ajax.testapp.domain.logic

import kotlinx.coroutines.flow.*
import ua.railian.ajax.testapp.domain.contract.datasource.LocalContactDataSource
import ua.railian.ajax.testapp.domain.contract.datasource.RemoteContactDataSource
import ua.railian.ajax.testapp.domain.contract.entity.Contact
import ua.railian.ajax.testapp.domain.contract.extension.launchOnCollect
import ua.railian.ajax.testapp.domain.contract.repository.ContactRepository
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private val localDataSource: LocalContactDataSource,
    private val remoteDataSource: RemoteContactDataSource
) : ContactRepository {

    override val contacts: Flow<List<Contact>> =
        localDataSource.contactsFlow()
            .launchOnCollect {
                if (!localDataSource.isInitialized) {
                    localDataSource.saveContacts(remoteDataSource.getContacts())
                    localDataSource.isInitialized = true
                }
            }

    override fun contact(id: Long): Flow<Contact?> =
        localDataSource.contactFlow(id)

    override suspend fun refreshContacts() {
        localDataSource.resetContacts(remoteDataSource.getContacts())
    }

    override suspend fun deleteContacts(vararg contactIds: Long) {
        localDataSource.removeContacts(contactIds.toList())
    }

    override suspend fun updateContacts(vararg contacts: Contact) {
        localDataSource.updateContacts(contacts.toList())
    }
}