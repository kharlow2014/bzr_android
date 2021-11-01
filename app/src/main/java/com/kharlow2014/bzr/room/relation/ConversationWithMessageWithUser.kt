package com.kharlow2014.bzr.room.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.kharlow2014.bzr.room.entity.Conversation
import com.kharlow2014.bzr.room.entity.Message

data class ConversationWithMessageWithUser(
    @Embedded val conversation: Conversation,
    @Relation(
        entity = Message::class,
        parentColumn = Conversation.ID,
        entityColumn = Message.CONVERSATION_ID
    )
    val messagesWithUser: List<MessageWithUser>
) {
    fun getLastMessage(): MessageWithUser? {
        if (messagesWithUser.isEmpty()) {
            return null
        }
        val sorted = messagesWithUser.sortedByDescending { it.message.timestamp }
        return sorted[0]
    }
}
