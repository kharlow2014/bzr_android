package com.kharlow2014.bzr.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.kharlow2014.bzr.room.entity.Conversation
import com.kharlow2014.bzr.room.relation.ConversationWithMessageWithUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: HomeRepository
) : ViewModel() {

    fun getConversations(): LiveData<List<Conversation>> = repository.getConversations()
    fun getConversationsWithMessageWithUser(): LiveData<List<ConversationWithMessageWithUser>> = repository.getConversationsWithMessageWithUser()
}
