package com.example.instagram

import android.app.Application
import android.content.Context
import com.example.instagram.di.component.ApplicationComponent
import com.example.instagram.di.component.DaggerApplicationComponent
import com.example.instagram.di.module.ApplicationModule

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