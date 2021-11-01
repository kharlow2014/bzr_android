package com.kharlow2014.bzr.room.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.kharlow2014.bzr.room.entity.Message
import com.kharlow2014.bzr.room.entity.User

data class MessageWithUser(
    @Embedded val message: Message,
    @Relation(
        parentColumn = Message.USER_ID,
        entityColumn = User.ID
    )
    val user: User
)
