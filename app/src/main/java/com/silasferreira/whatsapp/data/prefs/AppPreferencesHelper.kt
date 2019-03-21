package com.silasferreira.whatsapp.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.silasferreira.whatsapp.di.ApplicationContext
import com.silasferreira.whatsapp.di.PreferenceInfo
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppPreferencesHelper @Inject constructor(@ApplicationContext context: Context,
                                               @PreferenceInfo prefFileName: String) : PreferencesHelper {

    private val PREF_KEY_USER_LOGGED = "PREF_KEY_USER_LOGGED"
    private val mPrefs: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    override fun setUserId(userId: String) {
        mPrefs.edit().putString(PREF_KEY_USER_LOGGED, userId).apply()
    }

    override fun getUserId() : String {
        return mPrefs.getString(PREF_KEY_USER_LOGGED, "")
    }
}