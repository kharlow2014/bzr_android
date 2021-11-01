package com.kharlow2014.bzr.hilt

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BrzPreferences @Inject constructor(@ApplicationContext context: Context) {

    companion object {
        const val PREFERENCE_FILE_KEY = "brz_preferences"
        const val PREFERENCE_COLOR_SCHEME = "brz_color_scheme"
    }

    val prefs = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)

    fun getColorScheme(): ColorScheme {
        return ColorScheme.valueOf(prefs.getString(PREFERENCE_COLOR_SCHEME, ColorScheme.RED.name) ?: ColorScheme.RED.name)
    }

    fun setColorScheme(colorScheme: ColorScheme) {
        prefs.edit().putString(PREFERENCE_COLOR_SCHEME, colorScheme.name).apply()
    }

    enum class ColorScheme {
        RED, BLUE, GREEN, PURPLE
    }
}
