package com.silasferreira.whatsapp.di.component

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.silasferreira.whatsapp.App
import com.silasferreira.whatsapp.data.prefs.PreferencesHelper
import com.silasferreira.whatsapp.di.ApplicationContext
import com.silasferreira.whatsapp.di.module.*
import com.silasferreira.whatsapp.ui.base.BaseActivity
import com.silasferreira.whatsapp.ui.cadastro.CadastroActivity
import com.silasferreira.whatsapp.ui.home.HomeActivity
import com.silasferreira.whatsapp.ui.home.conversation.ConversationFragment
import com.silasferreira.whatsapp.ui.login.LoginActivity
import com.silasferreira.whatsapp.ui.setting.SettingActivity
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

    fun preferencesHelper(): PreferencesHelper

}