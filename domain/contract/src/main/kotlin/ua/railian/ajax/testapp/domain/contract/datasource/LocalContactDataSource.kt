package ua.railian.ajax.testapp.domain.contract.datasource

import kotlinx.coroutines.flow.Flow
import ua.railian.ajax.testapp.domain.contract.entity.Contact

interface LocalContactDataSource {
    var isInitialized: Boolean
    fun contactsFlow(): Flow<List<Contact>>
    fun contactFlow(id: Long): Flow<Contact?>
    suspend fun saveContacts(contacts: List<Contact>)
    suspend fun updateContacts(contacts: List<Contact>)
    suspend fun removeContacts(contactIds: List<Long>)
    suspend fun removeAllContacts()
    suspend fun resetContacts(contacts: List<Contact>)
}