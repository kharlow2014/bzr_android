package com.kharlow2014.bzr.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kharlow2014.bzr.BaseActivity
import com.kharlow2014.bzr.R
import com.kharlow2014.bzr.conversation.ConversationActivity
import com.kharlow2014.bzr.databinding.ActivityHomeBinding
import com.kharlow2014.bzr.databinding.ItemConversationBinding
import com.kharlow2014.bzr.room.relation.ConversationWithMessageWithUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private lateinit var adapter: ConversationAdapter
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavigationDrawer()

        binding.conversations.layoutManager = LinearLayoutManager(this)
        binding.conversations.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
        adapter = ConversationAdapter(this)
        binding.conversations.adapter = adapter
        viewModel.getConversationsWithMessageWithUser().observe(this) { updateUI(it) }
    }

    override fun setUpNavigationDrawer() {
        binding.navigationView.addHeaderView(getNavigationHeaderView())

        binding.toolbar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_contacts -> onContactOpened(this@HomeActivity)
                R.id.menu_settings -> onSettingOpened(this@HomeActivity)
            }
            binding.drawerLayout.close()
            true
        }
    }

    private fun updateUI(data: List<ConversationWithMessageWithUser>) {
        if (data.isEmpty()) {
            binding.conversations.visibility = View.GONE
            binding.noConversations.visibility = View.VISIBLE
        } else {
            binding.conversations.visibility = View.VISIBLE
            binding.noConversations.visibility = View.GONE

            adapter.submitList(data)
        }
    }

    private class ConversationAdapter(private val context: Context) :
        ListAdapter<ConversationWithMessageWithUser, ConversationAdapter.ConversationViewHolder>(DIFF_UTIL) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationViewHolder {
            val itemBinding =
                ItemConversationBinding.inflate(LayoutInflater.from(context), parent, false)
            return ConversationViewHolder(itemBinding, context)
        }

        override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {
            holder.bind(getItem(position))
        }

        companion object {
            val DIFF_UTIL = object : DiffUtil.ItemCallback<ConversationWithMessageWithUser>() {
                override fun areItemsTheSame(
                    oldItem: ConversationWithMessageWithUser,
                    newItem: ConversationWithMessageWithUser
                ): Boolean =
                    oldItem.conversation.id == newItem.conversation.id

                override fun areContentsTheSame(
                    oldItem: ConversationWithMessageWithUser,
                    newItem: ConversationWithMessageWithUser
                ): Boolean =
                    oldItem == newItem

            }
        }

        private class ConversationViewHolder(
            private val binding: ItemConversationBinding,
            private val context: Context
        ) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(conversationWithMessageWithUser: ConversationWithMessageWithUser) {
                val lastMessage = conversationWithMessageWithUser.getLastMessage()
                binding.primaryText.text = conversationWithMessageWithUser.conversation.conversationName
                if (lastMessage != null) {
                    binding.secondaryText.text = context.getString(R.string.conversation_message_preview, lastMessage.user.firstName, lastMessage.message.messageText)
                }

                binding.root.setOnClickListener {
                    val intent = Intent(context, ConversationActivity::class.java)
                    intent.putExtra(ConversationActivity.EXTRA_CONVERSATION_ID, conversationWithMessageWithUser.conversation.id)
                    context.startActivity(intent)
                }
            }
        }
    }
}
