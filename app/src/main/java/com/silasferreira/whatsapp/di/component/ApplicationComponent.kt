package com.silasferreira.whatsapp.di.component

import com.silasferreira.whatsapp.App
import com.silasferreira.whatsapp.di.module.*
import com.silasferreira.whatsapp.ui.base.BaseActivity
import com.silasferreira.whatsapp.ui.cadastro.CadastroActivity
import com.silasferreira.whatsapp.ui.home.HomeActivity
import com.silasferreira.whatsapp.ui.login.LoginActivity
import com.silasferreira.whatsapp.ui.setting.SettingActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    CadastroModule::class,
    HomeModule::class,
    LoginModule::class,
    SettingModule::class
])
interface ApplicationComponent {
    fun inject(app: App)
    fun inject(activity: BaseActivity)
    fun inject(activity: CadastroActivity)
    fun inject(activity: HomeActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: SettingActivity)
}