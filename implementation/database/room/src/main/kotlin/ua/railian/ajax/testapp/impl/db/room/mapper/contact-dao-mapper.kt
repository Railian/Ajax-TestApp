package ua.railian.ajax.testapp.impl.db.room.mapper

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ua.railian.ajax.testapp.domain.contract.datasource.LocalContactDataSource
import ua.railian.ajax.testapp.domain.contract.entity.Contact
import ua.railian.ajax.testapp.impl.db.room.dao.RoomContactDao
import ua.railian.ajax.testapp.impl.db.room.entity.ContactEntity
import ua.railian.ajax.testapp.impl.db.room.preference.DatabasePreferences

fun RoomContactDao.adapt(
    databasePreferences: DatabasePreferences,
) = object : LocalContactDataSource {

    val dao = this@adapt

    override var isInitialized: Boolean
            by databasePreferences::isContactDatabaseInitialized

    override fun contactsFlow(): Flow<List<Contact>> =
        dao.contactsFlow().map { it.map(ContactEntity::adapt) }

    override fun contactFlow(id: Long): Flow<Contact?> =
        dao.contactFlow(id).map { it?.adapt() }

    override suspend fun saveContacts(contacts: List<Contact>) =
        dao.saveContacts(contacts.map(Contact::adapt))

    override suspend fun updateContacts(contacts: List<Contact>) =
        dao.saveContacts(contacts.map(Contact::adapt))

    override suspend fun removeContacts(contactIds: List<Long>) =
        dao.removeContacts(contactIds)

    override suspend fun removeAllContacts() =
        dao.removeAllContacts()

    override suspend fun resetContacts(contacts: List<Contact>) =
        dao.resetContacts(contacts.map(Contact::adapt))
}