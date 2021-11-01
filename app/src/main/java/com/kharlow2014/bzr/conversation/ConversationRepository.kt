package com.kharlow2014.bzr.conversation

import androidx.lifecycle.LiveData
import com.kharlow2014.bzr.room.dao.ConversationDao
import com.kharlow2014.bzr.room.entity.Conversation
import com.kharlow2014.bzr.room.entity.Message
import com.kharlow2014.bzr.room.relation.MessageWithUser
import javax.inject.Inject

class ConversationRepository @Inject constructor(private val conversationDao: ConversationDao) {

    fun getMessagesWithUser(conversationId: Long): LiveData<List<MessageWithUser>> = conversationDao.getMessagesWithUser(conversationId)
    fun getMessages(conversationId: Long): LiveData<List<Message>> = conversationDao.getMessages(conversationId)
    fun getConversation(conversationId: Long): LiveData<Conversation> = conversationDao.getConversation(conversationId)
}
