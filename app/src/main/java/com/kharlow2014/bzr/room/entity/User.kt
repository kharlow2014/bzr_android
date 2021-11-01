package com.kharlow2014.bzr.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = User.TABLE)
data class User(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = ID) val id: Long?,
    @ColumnInfo(name = GUID) val guid: String,
    @ColumnInfo(name = FIRST_NAME) val firstName: String,
    @ColumnInfo(name = LAST_NAME) val lastName: String
) {

    companion object {
        const val TABLE = "user"
        const val ID = "id"
        const val GUID = "guid"
        const val FIRST_NAME = "first_name"
        const val LAST_NAME = "last_name"
    }
}
