package com.example.instagram.ui.register

import com.example.instagram.R
import com.example.instagram.model.User
import com.example.instagram.ui.base.BasePresenter
import javax.inject.Inject

class RegisterPresenter<V: RegisterContract.View, I: RegisterContract.Interactor>
    @Inject constructor(var interactorRegister: I):
    BasePresenter<V, I>(interactorRegister), RegisterContract.Presenter<V, I> {

    override fun createUser(user: User) {
        if(!(user.name == "" || user.email == "" || user.password == "")){
            getMvpView().setVisibleProgress()
            interactorRegister.createUserInAuthentication(user).addOnCompleteListener{
                interactorRegister.createUser(user)
                getMvpView().onFinish()
            }.addOnFailureListener{
                getMvpView().onError(getMvpView().onGetString(R.string.error_create_user))
            }
        }else{
            getMvpView().onError(getMvpView().onGetString(R.string.enter_the_data))
        }
    }
}