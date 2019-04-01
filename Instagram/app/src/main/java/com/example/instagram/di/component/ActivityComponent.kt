package com.example.instagram.di.component

import com.example.instagram.di.PerActivity
import com.example.instagram.di.module.ActivityModule
import com.example.instagram.ui.editprofile.EditProfileActivity
import com.example.instagram.ui.home.HomeActivity
import com.example.instagram.ui.login.LoginActivity
import com.example.instagram.ui.register.RegisterActivity

import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: RegisterActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: HomeActivity)
    fun inject(activity: EditProfileActivity)
}