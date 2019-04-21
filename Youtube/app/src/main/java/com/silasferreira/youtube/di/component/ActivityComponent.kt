package com.silasferreira.youtube.di.component

import com.silasferreira.youtube.di.PerActivity
import com.silasferreira.youtube.di.module.ActivityModule
import com.silasferreira.youtube.ui.home.HomeActivity
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: HomeActivity)
}