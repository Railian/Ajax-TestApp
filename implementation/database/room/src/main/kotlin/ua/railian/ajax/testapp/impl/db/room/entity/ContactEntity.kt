package ua.railian.ajax.testapp.impl.db.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class ContactEntity(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "picture_large") val pictureLarge: String?,
    @ColumnInfo(name = "picture_medium") val pictureMedium: String?,
    @ColumnInfo(name = "picture_thumbnail") val pictureThumbnail: String?
)