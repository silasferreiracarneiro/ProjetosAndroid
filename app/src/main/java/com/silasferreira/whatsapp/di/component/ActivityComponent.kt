package com.silasferreira.whatsapp.di.component

import com.silasferreira.whatsapp.di.PerActivity
import com.silasferreira.whatsapp.di.module.ActivityModule
import com.silasferreira.whatsapp.ui.base.BaseActivity
import com.silasferreira.whatsapp.ui.cadastro.CadastroActivity
import com.silasferreira.whatsapp.ui.home.HomeActivity
import com.silasferreira.whatsapp.ui.home.conversation.ConversationFragment
import com.silasferreira.whatsapp.ui.login.LoginActivity
import com.silasferreira.whatsapp.ui.setting.SettingActivity
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: CadastroActivity)
    fun inject(activity: HomeActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: SettingActivity)

    fun inject(fragment: ConversationFragment)
}