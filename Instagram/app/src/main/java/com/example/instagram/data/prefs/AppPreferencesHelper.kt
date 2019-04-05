package com.example.instagram.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.example.instagram.di.ApplicationContext
import com.example.instagram.di.PreferenceInfo
import com.example.instagram.utils.AppConstants
import com.example.instagram.utils.AppConstants.Companion.PREF_KEY_CURRENT_USER_ID
import javax.inject.Inject

class AppPreferencesHelper @Inject constructor(@ApplicationContext context: Context,
                                               @PreferenceInfo prefFileName: String) : PreferencesHelper {

    private val mPrefs: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    override fun setEmailUser(email: String) {
        val id = if (email == null) AppConstants.NULL_STRING else email
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_ID, id).apply()
    }

    override fun getEmailUser(): String {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_ID, null)
    }

}