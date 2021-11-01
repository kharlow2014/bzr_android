package com.kharlow2014.bzr.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kharlow2014.bzr.room.entity.Conversation
import com.kharlow2014.bzr.room.entity.Message
import com.kharlow2014.bzr.room.entity.User

@Dao
interface SettingDao {

    @Query("SELECT * FROM ${Conversation.TABLE}")
    suspend fun getConversations(): List<Conversation>

    @Insert(entity = User::class)
    suspend fun insert(user: User)

    @Insert(entity = Conversation::class)
    suspend fun insert(conversation: Conversation)

    @Insert(entity = Message::class)
    suspend fun insert(message: Message)
}
