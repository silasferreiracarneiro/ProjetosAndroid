package com.silasferreira.whatsapp

import android.app.Application
import com.silasferreira.whatsapp.di.component.ApplicationComponent
import com.silasferreira.whatsapp.di.component.DaggerApplicationComponent
import com.silasferreira.whatsapp.di.module.ApplicationModule
import com.silasferreira.whatsapp.di.module.CadastroModule
import com.silasferreira.whatsapp.di.module.HomeModule
import com.silasferreira.whatsapp.di.module.LoginModule

class App : Application() {

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .cadastroModule(CadastroModule())
            .loginModule(LoginModule())
            .homeModule(HomeModule())
            .build()

        //component.inject(this)
    }

    fun getComponent(): ApplicationComponent{
        return component
    }
}