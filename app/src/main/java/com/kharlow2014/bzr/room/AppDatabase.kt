package com.kharlow2014.bzr.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kharlow2014.bzr.room.dao.ConversationDao
import com.kharlow2014.bzr.room.dao.HomeDao
import com.kharlow2014.bzr.room.dao.SettingDao
import com.kharlow2014.bzr.room.entity.Conversation
import com.kharlow2014.bzr.room.entity.Message
import com.kharlow2014.bzr.room.entity.User

@Database(entities = [Conversation::class, Message::class, User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun homeDao(): HomeDao
    abstract fun conversationDao(): ConversationDao
    abstract fun settingDao(): SettingDao
}
