package com.silasferreira.whatsapp.di.module

import com.silasferreira.whatsapp.data.network.model.UsuarioRequest
import com.silasferreira.whatsapp.data.network.repository.UsuarioRepository
import com.silasferreira.whatsapp.data.prefs.PreferencesHelper
import com.silasferreira.whatsapp.di.AnnotationHelper
import com.silasferreira.whatsapp.ui.cadastro.CadastroContract
import com.silasferreira.whatsapp.ui.cadastro.CadastroInteractor
import com.silasferreira.whatsapp.ui.cadastro.CadastroPresenter
import dagger.Module
import dagger.Provides

@Module
class CadastroModule {

    @Provides
    fun provideCadastroPresenter(interactor: CadastroContract.Interactor): CadastroContract.Presenter<CadastroContract.View, CadastroContract.Interactor>{
        return CadastroPresenter(interactor)
    }

    @Provides
    fun provideCadastroInteractor(repository: UsuarioRepository): CadastroContract.Interactor{
        return CadastroInteractor(repository)
    }

    @Provides
    @AnnotationHelper.CadastroRepository
    fun provideCadastroRepository(prefHelter: PreferencesHelper): UsuarioRepository{
        return UsuarioRequest(prefHelter)
    }
}