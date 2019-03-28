package com.example.instagram.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.example.instagram.di.ApplicationContext
import com.example.instagram.di.PreferenceInfo
import javax.inject.Inject

class AppPreferencesHelper @Inject constructor(@ApplicationContext context: Context,
                                               @PreferenceInfo prefFileName: String) : PreferencesHelper {

    private val mPrefs: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

}