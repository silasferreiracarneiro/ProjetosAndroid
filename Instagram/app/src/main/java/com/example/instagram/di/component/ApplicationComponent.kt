package com.example.instagram.di.component

import android.app.Application
import android.content.Context
import com.example.instagram.App
import com.example.instagram.data.firebase.ConfigFirebaseContract
import com.example.instagram.data.prefs.PreferencesHelper
import com.example.instagram.di.ApplicationContext
import com.example.instagram.di.module.ActivityModule
import com.example.instagram.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    ActivityModule::class
])
interface ApplicationComponent {
    fun inject(app: App)

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun preferencesHelper(): PreferencesHelper

    fun firebaseConfig(): ConfigFirebaseContract
}