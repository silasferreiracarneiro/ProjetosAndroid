package com.silasferreira.whatsapp.di.module

import com.silasferreira.whatsapp.data.network.model.UsuarioRequest
import com.silasferreira.whatsapp.data.network.repository.UsuarioRepository
import com.silasferreira.whatsapp.data.prefs.PreferencesHelper
import com.silasferreira.whatsapp.di.AnnotationHelper
import com.silasferreira.whatsapp.ui.login.LoginContract
import com.silasferreira.whatsapp.ui.login.LoginInteractor
import com.silasferreira.whatsapp.ui.login.LoginPresenter
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    fun provideLoginPresenter(interactor: LoginContract.Interactor, prefHelter: PreferencesHelper) : LoginContract.Presenter<LoginContract.View, LoginContract.Interactor> {
        return LoginPresenter(interactor, prefHelter)
    }

    @Provides
    fun provideLoginInteractor(repository: UsuarioRepository) : LoginContract.Interactor{
        return LoginInteractor(repository)
    }

    @Provides @AnnotationHelper.LoginRepository
    fun provideLoginRepository(prefHelter: PreferencesHelper) : UsuarioRepository{
        return UsuarioRequest(prefHelter)
    }
}