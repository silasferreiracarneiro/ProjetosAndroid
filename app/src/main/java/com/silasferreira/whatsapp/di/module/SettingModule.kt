package com.silasferreira.whatsapp.di.module

import com.silasferreira.whatsapp.data.network.model.UsuarioRequest
import com.silasferreira.whatsapp.data.network.repository.UsuarioRepository
import com.silasferreira.whatsapp.data.prefs.PreferencesHelper
import com.silasferreira.whatsapp.di.AnnotationHelper
import com.silasferreira.whatsapp.ui.setting.SettingContract
import com.silasferreira.whatsapp.ui.setting.SettingInteractor
import com.silasferreira.whatsapp.ui.setting.SettingPresenter
import dagger.Module
import dagger.Provides

@Module
class SettingModule {

    @Provides
    fun provideSettingPresenter(interactor: SettingContract.Interactor): SettingContract.Presenter<SettingContract.View, SettingContract.Interactor>{
        return SettingPresenter(interactor)
    }

    @Provides
    fun provideSettingInteractor(repository: UsuarioRepository): SettingContract.Interactor{
        return SettingInteractor(repository)
    }

    @Provides @AnnotationHelper.SettingRepository
    fun provideSettingRepository(prefHelter: PreferencesHelper): UsuarioRepository{
        return UsuarioRequest(prefHelter)
    }

}