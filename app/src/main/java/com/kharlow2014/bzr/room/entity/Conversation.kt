package com.kharlow2014.bzr.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Conversation.TABLE)
data class Conversation(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = ID) val id: Long?,
    @ColumnInfo(name = CONVERSATION_NAME) val conversationName: String
) {

    companion object {
        const val TABLE = "conversation"
        const val ID = "id"
        const val CONVERSATION_NAME = "conversation_name"
    }
}
