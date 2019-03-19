package com.silasferreira.whatsapp

import android.app.Application
import com.silasferreira.whatsapp.di.component.ApplicationComponent
import com.silasferreira.whatsapp.di.component.DaggerApplicationComponent
import com.silasferreira.whatsapp.di.module.ApplicationModule
import com.silasferreira.whatsapp.di.module.CadastroModule

class App : Application() {

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .cadastroModule(CadastroModule())
            .build()

        //component.inject(this)
    }

    fun getComponent(): ApplicationComponent{
        return component
    }
}