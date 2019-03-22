package com.silasferreira.whatsapp.di.module

import com.silasferreira.whatsapp.data.network.model.UsuarioRequest
import com.silasferreira.whatsapp.data.network.repository.UsuarioRepository
import com.silasferreira.whatsapp.data.prefs.PreferencesHelper
import com.silasferreira.whatsapp.ui.home.HomeContract
import com.silasferreira.whatsapp.ui.home.HomeInteractor
import com.silasferreira.whatsapp.ui.home.HomePresenter
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    fun providerHomePresenter(interactor: HomeContract.Interactor) : HomeContract.Presenter<HomeContract.View, HomeContract.Interactor>{
        return HomePresenter(interactor)
    }

    @Provides
    fun providerHomeInteractor(repository: UsuarioRepository) : HomeContract.Interactor{
        return HomeInteractor(repository)
    }

    @Provides
    fun providerHomeRepository(prefHelter: PreferencesHelper) : UsuarioRepository {
        return UsuarioRequest(prefHelter)
    }
}