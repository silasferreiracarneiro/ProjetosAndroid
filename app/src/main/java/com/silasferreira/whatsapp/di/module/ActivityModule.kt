package com.silasferreira.whatsapp.di.module

import androidx.appcompat.app.AppCompatActivity
import com.silasferreira.whatsapp.data.network.model.UsuarioRequest
import com.silasferreira.whatsapp.data.network.repository.UsuarioRepository
import com.silasferreira.whatsapp.data.prefs.PreferencesHelper
import com.silasferreira.whatsapp.di.PerActivity
import com.silasferreira.whatsapp.ui.cadastro.CadastroContract
import com.silasferreira.whatsapp.ui.cadastro.CadastroInteractor
import com.silasferreira.whatsapp.ui.cadastro.CadastroPresenter
import com.silasferreira.whatsapp.ui.home.HomeContract
import com.silasferreira.whatsapp.ui.home.HomeInteractor
import com.silasferreira.whatsapp.ui.home.HomePresenter
import com.silasferreira.whatsapp.ui.home.contact.ContactContract
import com.silasferreira.whatsapp.ui.home.contact.ContactInteractor
import com.silasferreira.whatsapp.ui.home.contact.ContactPresenter
import com.silasferreira.whatsapp.ui.home.conversation.ConversationContract
import com.silasferreira.whatsapp.ui.home.conversation.ConversationInteractor
import com.silasferreira.whatsapp.ui.home.conversation.ConversationPresenter
import com.silasferreira.whatsapp.ui.login.LoginContract
import com.silasferreira.whatsapp.ui.login.LoginInteractor
import com.silasferreira.whatsapp.ui.login.LoginPresenter
import com.silasferreira.whatsapp.ui.setting.SettingContract
import com.silasferreira.whatsapp.ui.setting.SettingInteractor
import com.silasferreira.whatsapp.ui.setting.SettingPresenter

import dagger.Module
import dagger.Provides

@Module
class ActivityModule(appCompatActivity: AppCompatActivity) {

    //CADASTRO
    @Provides
    @PerActivity
    fun provideCadastroPresenter(interactor: CadastroContract.Interactor): CadastroContract.Presenter<CadastroContract.View, CadastroContract.Interactor>{
        return CadastroPresenter(interactor)
    }

    @Provides
    @PerActivity
    fun provideCadastroInteractor(repository: UsuarioRepository): CadastroContract.Interactor{
        return CadastroInteractor(repository)
    }


    //HOME
    @Provides
    @PerActivity
    fun providerHomePresenter(interactor: HomeContract.Interactor) : HomeContract.Presenter<HomeContract.View, HomeContract.Interactor>{
        return HomePresenter(interactor)
    }

    @Provides
    @PerActivity
    fun providerHomeInteractor(repository: UsuarioRepository) : HomeContract.Interactor{
        return HomeInteractor(repository)
    }

    //LOGIN
    @Provides
    @PerActivity
    fun provideLoginPresenter(interactor: LoginContract.Interactor, prefHelter: PreferencesHelper) : LoginContract.Presenter<LoginContract.View, LoginContract.Interactor> {
        return LoginPresenter(interactor, prefHelter)
    }

    @Provides
    @PerActivity
    fun provideLoginInteractor(repository: UsuarioRepository) : LoginContract.Interactor{
        return LoginInteractor(repository)
    }

    //SETTING
    @Provides
    @PerActivity
    fun provideSettingPresenter(interactor: SettingContract.Interactor): SettingContract.Presenter<SettingContract.View, SettingContract.Interactor>{
        return SettingPresenter(interactor)
    }

    @Provides
    @PerActivity
    fun provideSettingInteractor(repository: UsuarioRepository): SettingContract.Interactor{
        return SettingInteractor(repository)
    }

    //CONVERSATION
    @Provides
    @PerActivity
    fun provideConversationPresenter(interactor: ConversationContract.Integractor): ConversationContract.Presenter<ConversationContract.View, ConversationContract.Integractor>{
        return ConversationPresenter(interactor)
    }

    @Provides
    @PerActivity
    fun provideConversationInteractor(repository: UsuarioRepository) : ConversationContract.Integractor{
        return ConversationInteractor(repository)
    }

    //CONTACT
    @Provides
    @PerActivity
    fun provideContactPresenter(interactor: ContactContract.Integractor): ContactContract.Presenter<ContactContract.View, ContactContract.Integractor>{
        return ContactPresenter(interactor)
    }

    @Provides
    @PerActivity
    fun provideContactInteractor(repository: UsuarioRepository) : ContactContract.Integractor{
        return ContactInteractor(repository)
    }

    //Repository
    @Provides
    fun provideUsuarioRepository(prefHelter: PreferencesHelper): UsuarioRepository {
        return UsuarioRequest(prefHelter)
    }
}