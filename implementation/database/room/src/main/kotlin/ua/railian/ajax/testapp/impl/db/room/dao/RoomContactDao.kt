package ua.railian.ajax.testapp.impl.db.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ua.railian.ajax.testapp.impl.db.room.entity.ContactEntity

@Dao
interface RoomContactDao {

    @Query("SELECT * FROM contacts")
    fun contactsFlow(): Flow<List<ContactEntity>>

    @Query("SELECT * FROM contacts WHERE id = :id")
    fun contactFlow(id: Long): Flow<ContactEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveContacts(contacts: List<ContactEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateContact(contact: ContactEntity)

    @Query("DELETE FROM contacts WHERE id IN (:contactIds)")
    suspend fun removeContacts(contactIds: List<Long>)

    @Query("DELETE FROM contacts")
    suspend fun removeAllContacts()

    @Transaction
    suspend fun resetContacts(contacts: List<ContactEntity>) {
        removeAllContacts()
        saveContacts(contacts)
    }
}