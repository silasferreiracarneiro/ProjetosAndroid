package com.example.instagram.di.module

import androidx.appcompat.app.AppCompatActivity
import com.example.instagram.data.firebase.ConfigFirebaseContract
import com.example.instagram.data.network.model.UserRequest
import com.example.instagram.data.network.repository.UserRepository
import com.example.instagram.data.prefs.PreferencesHelper
import com.example.instagram.di.PerActivity
import com.example.instagram.ui.editprofile.EditProfileContract
import com.example.instagram.ui.editprofile.EditProfileInteractor
import com.example.instagram.ui.editprofile.EditProfilePresenter
import com.example.instagram.ui.home.HomeContract
import com.example.instagram.ui.home.HomeInteractor
import com.example.instagram.ui.home.HomePresenter
import com.example.instagram.ui.login.LoginContract
import com.example.instagram.ui.login.LoginInteractor
import com.example.instagram.ui.login.LoginPresenter
import com.example.instagram.ui.register.RegisterContract
import com.example.instagram.ui.register.RegisterInteractor
import com.example.instagram.ui.register.RegisterPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(appCompatActivity: AppCompatActivity) {

    //REGISTER
    @Provides
    @PerActivity
    fun provideRegisterPresenter(interactorRegister: RegisterContract.Interactor): RegisterContract.Presenter<RegisterContract.View, RegisterContract.Interactor>{
        return RegisterPresenter(interactorRegister)

    }

    @Provides
    @PerActivity
    fun provideRegisterInteractor(userRepository: UserRepository): RegisterContract.Interactor {
        return RegisterInteractor(userRepository)
    }

    //LOGIN
    @Provides
    @PerActivity
    fun provideLoginPresenter(interactor: LoginContract.Interactor, prefHelter: PreferencesHelper): LoginContract.Presenter<LoginContract.View, LoginContract.Interactor>{
        return LoginPresenter(interactor, prefHelter)
    }

    @Provides
    @PerActivity
    fun provideLoginInteractor(userRepository: UserRepository): LoginContract.Interactor{
        return LoginInteractor(userRepository)
    }

    //HOME
    @Provides
    @PerActivity
    fun provideHomePresenter(interactor: HomeContract.Interactor): HomeContract.Presenter<HomeContract.View, HomeContract.Interactor>{
        return HomePresenter(interactor)
    }

    @Provides
    @PerActivity
    fun provideHomeInteractor(userRepository: UserRepository): HomeContract.Interactor{
        return HomeInteractor(userRepository)
    }

    //EDIT PROFILE
    @Provides
    @PerActivity
    fun provideEditProfilePresenter(interactor: EditProfileContract.Interactor): EditProfileContract.Presenter<EditProfileContract.View, EditProfileContract.Interactor>{
        return EditProfilePresenter(interactor)
    }

    @Provides
    @PerActivity
    fun provideEditProfileInteractor(repository: UserRepository): EditProfileContract.Interactor{
        return EditProfileInteractor(repository)
    }

    //REPOSITORY
    @Provides
    fun provideUserRepository(firebaseConfig: ConfigFirebaseContract): UserRepository{
        return UserRequest(firebaseConfig)
    }
}