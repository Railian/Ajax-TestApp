package ua.railian.ajax.testapp.impl.db.room.database

import android.content.Context
import androidx.room.Room

fun buildDatabase(
    applicationContext: Context,
    databaseName: String = "ajax-database"
) = Room.databaseBuilder(
    /* context = */ applicationContext,
    /* klass = */ AjaxDatabase::class.java,
    /* name = */ databaseName
).build()
