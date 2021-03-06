package com.silasferreira.whatsapp.di.module

import androidx.appcompat.app.AppCompatActivity
import com.silasferreira.whatsapp.data.network.model.ConversationRequest
import com.silasferreira.whatsapp.data.network.model.UsuarioRequest
import com.silasferreira.whatsapp.data.network.repository.ConversationRepository
import com.silasferreira.whatsapp.data.network.repository.UsuarioRepository
import com.silasferreira.whatsapp.data.prefs.PreferencesHelper
import com.silasferreira.whatsapp.di.PerActivity
import com.silasferreira.whatsapp.ui.register.CadastroContract
import com.silasferreira.whatsapp.ui.register.CadastroInteractor
import com.silasferreira.whatsapp.ui.register.CadastroPresenter
import com.silasferreira.whatsapp.ui.chat.ChatContract
import com.silasferreira.whatsapp.ui.chat.ChatInteractor
import com.silasferreira.whatsapp.ui.chat.ChatPresenter
import com.silasferreira.whatsapp.ui.group.GroupContract
import com.silasferreira.whatsapp.ui.group.GroupInteractor
import com.silasferreira.whatsapp.ui.group.GroupPresenter
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
import com.silasferreira.whatsapp.ui.registergroup.RegisterGroupContract
import com.silasferreira.whatsapp.ui.registergroup.RegisterGroupInteractor
import com.silasferreira.whatsapp.ui.registergroup.RegisterGroupPresenter
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
    fun providerHomePresenter(interactor: HomeContract.Interactor,
                              presenterConversation: ConversationContract.Presenter<ConversationContract.View, ConversationContract.Integractor>) : HomeContract.Presenter<HomeContract.View, HomeContract.Interactor>{
        return HomePresenter(interactor, presenterConversation)
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
    fun provideConversationInteractor(repository: ConversationRepository) : ConversationContract.Integractor{
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

    //CHAT
    @Provides
    @PerActivity
    fun provideChatPresenter(interactor: ChatContract.Interactor): ChatContract.Presenter<ChatContract.View, ChatContract.Interactor>{
        return ChatPresenter(interactor)
    }

    @Provides
    @PerActivity
    fun provideChatInteractor(repositoryConversation: ConversationRepository, repositoryUsuario: UsuarioRepository) : ChatContract.Interactor {
        return ChatInteractor(repositoryConversation, repositoryUsuario)
    }

    //GROUP
    @Provides
    @PerActivity
    fun provideGroupPresenter(interactor: GroupContract.Interactor): GroupContract.Presenter<GroupContract.View, GroupContract.Interactor>{
        return GroupPresenter(interactor)
    }

    @Provides
    @PerActivity
    fun provideGroupInteractor(repositoryUsuario: UsuarioRepository) : GroupContract.Interactor {
        return GroupInteractor(repositoryUsuario)
    }

    //REGISTER GROUP
    @Provides
    @PerActivity
    fun provideRegisterGroupPresenter(interactor: RegisterGroupContract.Interactor): RegisterGroupContract.Presenter<RegisterGroupContract.View, RegisterGroupContract.Interactor>{
        return RegisterGroupPresenter(interactor)
    }

    @Provides
    @PerActivity
    fun provideRegisterGroupInteractor(repository: ConversationRepository, usuarioRepository: UsuarioRepository) : RegisterGroupContract.Interactor {
        return RegisterGroupInteractor(repository, usuarioRepository)
    }

    //Repository
    @Provides
    fun provideUsuarioRepository(prefHelter: PreferencesHelper): UsuarioRepository {
        return UsuarioRequest(prefHelter)
    }

    @Provides
    fun provideConversationRepository(): ConversationRepository {
        return ConversationRequest()
    }
}