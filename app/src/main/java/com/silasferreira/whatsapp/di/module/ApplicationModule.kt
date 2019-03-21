package com.silasferreira.whatsapp.di.module

import android.app.Application
import android.content.Context
import com.silasferreira.whatsapp.data.prefs.AppPreferencesHelper
import com.silasferreira.whatsapp.data.prefs.PreferencesHelper
import com.silasferreira.whatsapp.di.ApplicationContext
import com.silasferreira.whatsapp.di.PreferenceInfo
import com.silasferreira.whatsapp.utils.AppConstants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return application
    }

    @Provides
    fun provideApplication(): Application {
        return application
    }

    @Provides
    @PreferenceInfo
    fun providePreferenceName(): String {
        return AppConstants.PREF_NAME
    }

    @Provides
    @Singleton
    fun providePreferencesHelper(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper {
        return appPreferencesHelper
    }
}