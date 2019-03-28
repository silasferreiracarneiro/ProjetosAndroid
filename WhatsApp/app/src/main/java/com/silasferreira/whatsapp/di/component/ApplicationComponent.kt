package com.silasferreira.whatsapp.di.component

import android.app.Application
import android.content.Context
import com.silasferreira.whatsapp.App
import com.silasferreira.whatsapp.data.prefs.PreferencesHelper
import com.silasferreira.whatsapp.di.ApplicationContext
import com.silasferreira.whatsapp.di.module.*
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

}