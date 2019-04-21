package com.silasferreira.youtube

import android.app.Application
import android.content.Context
import com.silasferreira.youtube.di.component.ApplicationComponent
import com.silasferreira.youtube.di.component.DaggerApplicationComponent
import com.silasferreira.youtube.di.module.ApplicationModule

class App: Application() {

    private lateinit var component: ApplicationComponent
    private lateinit var context: Context

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()

        component.inject(this)

        context = this
    }

    fun getComponent(): ApplicationComponent{
        return component
    }

    fun getContext(): Context{
        return context
    }
}