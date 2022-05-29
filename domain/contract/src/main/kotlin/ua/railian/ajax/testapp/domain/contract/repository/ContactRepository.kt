package ua.railian.ajax.testapp.domain.contract.repository

import kotlinx.coroutines.flow.Flow
import ua.railian.ajax.testapp.domain.contract.entity.Contact

interface ContactRepository {
    val contacts: Flow<List<Contact>>
    fun contact(id: Long): Flow<Contact?>
    suspend fun refreshContacts()
    suspend fun deleteContacts(vararg contactIds: Long)
    suspend fun updateContacts(vararg contacts: Contact)
}