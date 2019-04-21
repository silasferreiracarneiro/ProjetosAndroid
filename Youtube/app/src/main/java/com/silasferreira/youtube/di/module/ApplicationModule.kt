package com.silasferreira.youtube.di.module

import android.app.Application
import android.content.Context
import com.silasferreira.youtube.data.RetrofitConfig
import com.silasferreira.youtube.di.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(val application: Application)  {

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return application
    }

    @Provides
    fun provideApplication(): Application {
        return application
    }
}