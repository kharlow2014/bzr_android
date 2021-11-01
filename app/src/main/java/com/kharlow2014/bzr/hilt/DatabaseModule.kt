package com.kharlow2014.bzr.hilt

import android.content.Context
import androidx.room.Room
import com.kharlow2014.bzr.room.AppDatabase
import com.kharlow2014.bzr.room.dao.ConversationDao
import com.kharlow2014.bzr.room.dao.HomeDao
import com.kharlow2014.bzr.room.dao.SettingDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideHomeDao(appDatabase: AppDatabase): HomeDao {
        return appDatabase.homeDao()
    }

    @Provides
    fun provideConversationDao(appDatabase: AppDatabase): ConversationDao {
        return appDatabase.conversationDao()
    }

    @Provides
    fun provideSettingDao(appDatabase: AppDatabase): SettingDao {
        return appDatabase.settingDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "bzr"
        ).build()
    }
}
