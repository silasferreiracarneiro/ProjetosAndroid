package com.silasferreira.whatsapp.data.prefs

import javax.inject.Singleton

@Singleton
interface PreferencesHelper {

    fun setUserId(userId: String)
    fun getUserId(): String
}