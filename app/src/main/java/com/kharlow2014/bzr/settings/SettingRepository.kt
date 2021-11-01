package com.kharlow2014.bzr.settings

import com.kharlow2014.bzr.room.dao.SettingDao
import com.kharlow2014.bzr.room.entity.Conversation
import com.kharlow2014.bzr.room.entity.Message
import com.kharlow2014.bzr.room.entity.User
import kotlinx.coroutines.delay
import javax.inject.Inject

class SettingRepository @Inject constructor(private val settingDao: SettingDao) {

    suspend fun getConversations(): List<Conversation> = settingDao.getConversations()
    suspend fun insertTestData() {
        if (settingDao.getConversations().isNotEmpty()) {
            return
        }
        val users = listOf(
            User(1, "guid1", "Keaton", "Harlow"),
            User(2, "guid2", "Wayne", "Harlow"),
            User(3, "guid3", "Glenda", "Harlow"),
            User(4, "guid4", "Kelsie", "Harlow")
        )

        val conversations = listOf(
            Conversation(1, "Okayest Family")
        )

        users.forEach { settingDao.insert(it) }
        conversations.forEach { settingDao.insert(it) }

        val messages = mutableListOf<Message>()
        messages.add(Message(1, 1, "Look at this Android project that I\'m working on"))
        delay(2000)
        messages.add(Message(1, 2, "Look at this thing I welded"))
        delay(2000)
        messages.add(Message(1, 3, "Can you believe these politicians?"))
        delay(2000)
        messages.add(Message(1, 4, "Watch this Tik Tok!"))

        messages.forEach {
            settingDao.insert(it)
            delay(2000)
        }
    }
}