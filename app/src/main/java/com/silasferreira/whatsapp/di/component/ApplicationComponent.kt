package com.silasferreira.whatsapp.di.component

import com.silasferreira.whatsapp.di.module.ApplicationModule
import com.silasferreira.whatsapp.di.module.CadastroModule
import com.silasferreira.whatsapp.di.module.HomeModule
import com.silasferreira.whatsapp.di.module.LoginModule
import com.silasferreira.whatsapp.ui.base.BaseActivity
import com.silasferreira.whatsapp.ui.cadastro.CadastroActivity
import com.silasferreira.whatsapp.ui.home.HomeActivity
import com.silasferreira.whatsapp.ui.login.LoginActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    CadastroModule::class,
    HomeModule::class,
    LoginModule::class
])
interface ApplicationComponent {
    fun inject(activity: BaseActivity)
    fun inject(activity: CadastroActivity)
    fun inject(activity: HomeActivity)
    fun inject(activity: LoginActivity)
}