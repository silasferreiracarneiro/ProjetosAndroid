package com.example.instagram.data.prefs

interface PreferencesHelper {
    fun setEmailUser(email: String)
    fun getEmailUser(): String
}