package com.kharlow2014.bzr.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = Message.TABLE,
    foreignKeys = [ForeignKey(
        entity = Conversation::class,
        parentColumns = [Conversation.ID],
        childColumns = [Message.CONVERSATION_ID],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    ), ForeignKey(
        entity = User::class,
        parentColumns = [User.ID],
        childColumns = [Message.USER_ID],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )],
    indices = [Index(value = [Message.CONVERSATION_ID]), Index(value = [Message.USER_ID])]
)
data class Message(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = ID) val id: Long?,
    @ColumnInfo(name = CONVERSATION_ID) val conversationId: Long,
    @ColumnInfo(name = USER_ID) val userId: Long,
    @ColumnInfo(name = MESSAGE_TEXT) val messageText: String,
    @ColumnInfo(name = TIMESTAMP) val timestamp: Long
) {

    constructor(conversationId: Long, userId: Long, message: String) : this(
        null,
        conversationId,
        userId,
        message,
        System.currentTimeMillis()
    )

    companion object {
        const val TABLE = "message"
        const val ID = "id"
        const val CONVERSATION_ID = "conversation_id"
        const val USER_ID = "user_id"
        const val MESSAGE_TEXT = "message_text"
        const val TIMESTAMP = "timestamp"
    }
}
