package com.silasferreira.whatsapp.di.component

import com.silasferreira.whatsapp.di.module.ApplicationModule
import com.silasferreira.whatsapp.di.module.CadastroModule
import com.silasferreira.whatsapp.ui.base.BaseActivity
import com.silasferreira.whatsapp.ui.cadastro.CadastroActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    CadastroModule::class
])
interface ApplicationComponent {
    fun inject(activity: BaseActivity)
    fun inject(activity: CadastroActivity)
}