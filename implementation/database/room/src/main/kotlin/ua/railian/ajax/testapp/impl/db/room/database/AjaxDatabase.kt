package ua.railian.ajax.testapp.impl.db.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.railian.ajax.testapp.impl.db.room.dao.RoomContactDao
import ua.railian.ajax.testapp.impl.db.room.entity.ContactEntity

@Database(
    version = 1,
    entities = [ContactEntity::class]
)
abstract class AjaxDatabase : RoomDatabase() {
    abstract val contactDao: RoomContactDao
}