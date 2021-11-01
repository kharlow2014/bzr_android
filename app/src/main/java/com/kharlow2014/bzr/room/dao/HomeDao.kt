package com.kharlow2014.bzr.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.kharlow2014.bzr.room.entity.Conversation
import com.kharlow2014.bzr.room.relation.ConversationWithMessageWithUser

@Dao
interface HomeDao {

    @Query("SELECT * FROM ${Conversation.TABLE}")
    fun getConversations(): LiveData<List<Conversation>>

    @Transaction
    @Query("SELECT * FROM ${Conversation.TABLE}")
    fun getConversationsWithMessageWithUser(): LiveData<List<ConversationWithMessageWithUser>>
}
