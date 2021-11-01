package com.kharlow2014.bzr.conversation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.kharlow2014.bzr.room.entity.Conversation
import com.kharlow2014.bzr.room.entity.Message
import com.kharlow2014.bzr.room.relation.MessageWithUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConversationViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: ConversationRepository
) : ViewModel() {

    private val conversationId: MutableLiveData<Long> = MutableLiveData(1)

    fun getMessagesWithUser(): LiveData<List<MessageWithUser>> =
        Transformations.switchMap(conversationId) { repository.getMessagesWithUser(it) }

    fun getMessages(): LiveData<List<Message>> = Transformations.switchMap(conversationId) {
        repository.getMessages(it)
    }

    fun getConversation(): LiveData<Conversation> = Transformations.switchMap(conversationId) {
        repository.getConversation(it)
    }

    fun setConversationId(conversationId: Long) =
        apply { this.conversationId.value = conversationId }
}
