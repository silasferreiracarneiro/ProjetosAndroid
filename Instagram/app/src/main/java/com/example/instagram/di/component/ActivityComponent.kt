package com.example.instagram.di.component

import com.example.instagram.di.PerActivity
import com.example.instagram.di.module.ActivityModule
import com.example.instagram.ui.login.LoginActivity
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: LoginActivity)
}