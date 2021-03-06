package com.silasferreira.whatsapp.di.component

import com.silasferreira.whatsapp.di.PerActivity
import com.silasferreira.whatsapp.di.module.ActivityModule
import com.silasferreira.whatsapp.ui.register.CadastroActivity
import com.silasferreira.whatsapp.ui.chat.ChatActivity
import com.silasferreira.whatsapp.ui.group.GroupActivity
import com.silasferreira.whatsapp.ui.home.HomeActivity
import com.silasferreira.whatsapp.ui.home.contact.ContactFragment
import com.silasferreira.whatsapp.ui.home.conversation.ConversationFragment
import com.silasferreira.whatsapp.ui.login.LoginActivity
import com.silasferreira.whatsapp.ui.registergroup.RegisterGroupActivity
import com.silasferreira.whatsapp.ui.setting.SettingActivity
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: CadastroActivity)
    fun inject(activity: HomeActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: SettingActivity)
    fun inject(activity: ChatActivity)
    fun inject(activity: GroupActivity)
    fun inject(activity: RegisterGroupActivity)

    fun inject(fragment: ConversationFragment)
    fun inject(fragment: ContactFragment)
}