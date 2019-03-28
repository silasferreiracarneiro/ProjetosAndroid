package com.example.instagram.di.module

import androidx.appcompat.app.AppCompatActivity
import com.example.instagram.data.firebase.ConfigFirebaseContract
import com.example.instagram.data.network.model.UserRequest
import com.example.instagram.data.network.repository.UserRepository
import com.example.instagram.di.PerActivity
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

    //REPOSITORY
    @Provides
    fun provideUserRepository(firebaseConfig: ConfigFirebaseContract): UserRepository{
        return UserRequest(firebaseConfig)
    }
}