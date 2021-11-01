package com.kharlow2014.bzr.conversation

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kharlow2014.bzr.databinding.ActivityConversationBinding
import com.kharlow2014.bzr.databinding.ItemMessageBinding
import com.kharlow2014.bzr.databinding.ItemMyMessageBinding
import com.kharlow2014.bzr.room.relation.MessageWithUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConversationActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CONVERSATION_ID = "extra_conversation_id"
    }

    private lateinit var adapter: MessageAdapter
    private lateinit var binding: ActivityConversationBinding
    private val viewModel: ConversationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConversationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        viewModel.setConversationId(intent.getLongExtra(EXTRA_CONVERSATION_ID, 1))
        viewModel.getConversation().observe(this) {
            binding.toolbar.title = it.conversationName
        }

        adapter = MessageAdapter(this)
        binding.messages.adapter = adapter
        binding.messages.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, true)
        viewModel.getMessagesWithUser().observe(this) {
            adapter.submitList(it)
        }
    }

    class MessageAdapter(private val context: Context) :
        ListAdapter<MessageWithUser, RecyclerView.ViewHolder>(DIFF_UTIL) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            if (viewType == ItemType.MESSAGE.type) {
                val binding = ItemMessageBinding.inflate(LayoutInflater.from(context), parent, false)
                return MessageViewHolder(binding)
            } else {
                val binding = ItemMyMessageBinding.inflate(LayoutInflater.from(context), parent, false)
                return MyMessageViewHolder(binding)
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (getItemViewType(position) == ItemType.MESSAGE.type) {
                (holder as MessageViewHolder).bind(getItem(position))
            } else {
                (holder as MyMessageViewHolder).bind(getItem(position))
            }
        }

        override fun getItemViewType(position: Int): Int {
            // TODO: Need to check to see if the user matches the current user instead of string matching
            return if (getItem(position).user.firstName == "Keaton") {
                ItemType.MY_MESSAGE.type
            } else {
                ItemType.MESSAGE.type
            }
        }

        companion object {
            val DIFF_UTIL = object : DiffUtil.ItemCallback<MessageWithUser>() {
                override fun areItemsTheSame(
                    oldItem: MessageWithUser,
                    newItem: MessageWithUser
                ): Boolean =
                    oldItem.message.id == newItem.message.id

                override fun areContentsTheSame(
                    oldItem: MessageWithUser,
                    newItem: MessageWithUser
                ): Boolean =
                    oldItem == newItem

            }
        }

        enum class ItemType(val type: Int) {
            MESSAGE(1),
            MY_MESSAGE(2)
        }

        class MessageViewHolder(private val binding: ItemMessageBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(messageWithUser: MessageWithUser) {
                binding.name.text = messageWithUser.user.firstName
                binding.message.text = messageWithUser.message.messageText
            }
        }

        class MyMessageViewHolder(private val binding: ItemMyMessageBinding) :
            RecyclerView.ViewHolder(binding.root) {
                fun bind(messageWithUser: MessageWithUser) {
                    binding.name.text = "me"
                    binding.message.text = messageWithUser.message.messageText
                }
        }
    }
}
