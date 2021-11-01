package com.kharlow2014.bzr.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kharlow2014.bzr.BaseActivity
import com.kharlow2014.bzr.R
import com.kharlow2014.bzr.databinding.ActivitySettingBinding
import com.kharlow2014.bzr.databinding.ItemSingleLineSettingBinding
import com.kharlow2014.bzr.databinding.ItemTwoLineSettingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingActivity : BaseActivity() {

    private val viewModel: SettingViewModel by viewModels()
    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavigationDrawer()

        binding.settings.layoutManager = LinearLayoutManager(this)
        binding.settings.adapter = SettingAdapter(this)
    }

    override fun setUpNavigationDrawer() {
        binding.navigationView.addHeaderView(getNavigationHeaderView())

        binding.toolbar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> onHomeOpened(this@SettingActivity)
                R.id.menu_contacts -> onContactOpened(this@SettingActivity)
            }
            binding.drawerLayout.close()
            true
        }
    }

    fun insertData() {
        viewModel.generateData()
    }

    class SettingAdapter(private val context: Context) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private val list = Setting.values()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return if (viewType == ViewType.SINGLE.value) {
                val binding = ItemSingleLineSettingBinding.inflate(
                    LayoutInflater.from(context),
                    parent,
                    false
                )
                SingleLineSettingViewHolder(context, binding)
            } else {
                val binding =
                    ItemTwoLineSettingBinding.inflate(LayoutInflater.from(context), parent, false)
                TwoLineSettingViewHolder(binding)
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (getItemViewType(position) == ViewType.SINGLE.value) {
                (holder as SingleLineSettingViewHolder).bind(list[position])
            } else {
                (holder as TwoLineSettingViewHolder).bind(list[position])
            }
        }

        override fun getItemCount(): Int = list.size

        override fun getItemViewType(position: Int): Int {
            return if (list[position].descriptionRes == null) {
                ViewType.SINGLE.value
            } else {
                ViewType.DOUBLE.value
            }
        }

        enum class Setting(@StringRes val settingRes: Int, @StringRes val descriptionRes: Int?) {
            GENERATE_DATA(R.string.generate_data, null)
        }

        enum class ViewType(val value: Int) {
            SINGLE(1),
            DOUBLE(2)
        }

        class TwoLineSettingViewHolder(private val binding: ItemTwoLineSettingBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(setting: Setting) {
                binding.setting.setText(setting.settingRes)
                binding.description.setText(setting.descriptionRes!!)
            }
        }

        class SingleLineSettingViewHolder(
            private val context: Context,
            private val binding: ItemSingleLineSettingBinding
        ) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(setting: Setting) {
                binding.setting.setText(setting.settingRes)

                binding.root.setOnClickListener {
                    when (setting) {
                        Setting.GENERATE_DATA -> {
                            (context as SettingActivity).insertData()
                        }
                    }
                }
            }
        }
    }
}
