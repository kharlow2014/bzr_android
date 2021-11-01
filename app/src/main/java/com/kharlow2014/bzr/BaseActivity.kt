package com.kharlow2014.bzr

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.kharlow2014.bzr.conversation.ConversationActivity
import com.kharlow2014.bzr.databinding.HeaderNavigationDrawerBinding
import com.kharlow2014.bzr.home.HomeActivity
import com.kharlow2014.bzr.settings.SettingActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract fun setUpNavigationDrawer()

    fun getNavigationHeaderView(): View {
        val navigationHeaderBinding = HeaderNavigationDrawerBinding.inflate(layoutInflater)
        navigationHeaderBinding.profilePicture.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_people_24))
        navigationHeaderBinding.name.text = "Keaton Harlow"
        return navigationHeaderBinding.root
    }

    fun onHomeOpened(context: Context) {
        val intent = Intent(context, HomeActivity::class.java)
        startActivity(intent)
    }

    fun onContactOpened(context: Context) {
        // Implement
    }

    fun onSettingOpened(context: Context) {
        val intent = Intent(context, SettingActivity::class.java)
        startActivity(intent)
    }
}
