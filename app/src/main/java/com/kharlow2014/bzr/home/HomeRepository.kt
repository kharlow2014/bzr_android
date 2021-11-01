package com.kharlow2014.bzr.home

import androidx.lifecycle.LiveData
import com.kharlow2014.bzr.room.dao.HomeDao
import com.kharlow2014.bzr.room.entity.Conversation
import com.kharlow2014.bzr.room.relation.ConversationWithMessageWithUser
import javax.inject.Inject

class HomeRepository @Inject constructor(private val homeDao: HomeDao) {

    fun getConversations(): LiveData<List<Conversation>> = homeDao.getConversations()
    fun getConversationsWithMessageWithUser(): LiveData<List<ConversationWithMessageWithUser>> = homeDao.getConversationsWithMessageWithUser()

}
