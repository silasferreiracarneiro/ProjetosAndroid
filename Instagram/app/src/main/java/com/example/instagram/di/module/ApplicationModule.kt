package com.example.instagram.di.module

import android.app.Application
import android.content.Context
import com.example.instagram.data.firebase.ConfigFirebase
import com.example.instagram.data.firebase.ConfigFirebaseContract
import com.example.instagram.data.prefs.AppPreferencesHelper
import com.example.instagram.data.prefs.PreferencesHelper
import com.example.instagram.di.ApplicationContext
import com.example.instagram.di.PreferenceInfo
import com.example.instagram.utils.AppConstants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

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

    @Provides
    @Singleton
    fun provideFirebaseInstace(): ConfigFirebaseContract {
        return ConfigFirebase()
    }
}