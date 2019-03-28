package com.example.instagram.ui.login

import com.example.instagram.ui.base.BasePresenter
import javax.inject.Inject

class LoginPresenter<V: LoginContract.View, I: LoginContract.Interactor>
    @Inject constructor(var interactorLogin: I):
    BasePresenter<V, I>(interactorLogin), LoginContract.Presenter<V, I> {

    override fun signIn() {
        if(interactorLogin.signIn() != null){
            getMvpView().goToHome()
        }
    }
}