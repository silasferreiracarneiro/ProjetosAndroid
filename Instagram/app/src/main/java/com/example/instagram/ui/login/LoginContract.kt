package com.example.instagram.ui.login

import com.google.firebase.auth.FirebaseUser
import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface LoginContract {

    interface View: MvpView{
        fun goToHome()
    }

    interface Interactor: MvpInteractor{
        fun signIn(): FirebaseUser?
    }

    interface Presenter<V: LoginContract.View, I: LoginContract.Interactor>: MvpPresenter<V, I>{
        fun signIn()
    }
}