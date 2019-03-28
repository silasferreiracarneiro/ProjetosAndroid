package com.silasferreira.whatsapp.data.prefs

import javax.inject.Singleton

@Singleton
interface PreferencesHelper {

    fun setUserId(userId: String)
    fun getUserId(): String
    fun setNameUser(name: String)
    fun getNameUser(): String
}