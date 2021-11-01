package com.kharlow2014.bzr.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.kharlow2014.bzr.room.entity.Conversation
import com.kharlow2014.bzr.room.entity.Message
import com.kharlow2014.bzr.room.relation.MessageWithUser

@Dao
interface
ConversationDao {

    @Query("SELECT * FROM ${Message.TABLE} WHERE ${Message.CONVERSATION_ID}=:conversationId ORDER BY ${Message.TIMESTAMP} DESC")
    fun getMessages(conversationId: Long): LiveData<List<Message>>

    @Query("SELECT * FROM ${Conversation.TABLE} WHERE ${Conversation.ID}=:conversationId LIMIT 1")
    fun getConversation(conversationId: Long): LiveData<Conversation>

    @Transaction
    @Query("SELECT * FROM ${Message.TABLE} WHERE ${Message.CONVERSATION_ID}=:conversationId ORDER BY ${Message.TIMESTAMP} DESC")
    fun getMessagesWithUser(conversationId: Long): LiveData<List<MessageWithUser>>
}
