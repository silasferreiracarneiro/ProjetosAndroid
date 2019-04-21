package com.silasferreira.youtube.di.component

import android.app.Application
import android.content.Context
import com.silasferreira.youtube.App
import com.silasferreira.youtube.di.ApplicationContext
import com.silasferreira.youtube.di.module.ActivityModule
import com.silasferreira.youtube.di.module.ApplicationModule
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
}