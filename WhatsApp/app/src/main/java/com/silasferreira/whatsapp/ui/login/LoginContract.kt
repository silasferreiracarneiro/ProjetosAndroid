package com.silasferreira.whatsapp.ui.login

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface LoginContract {

    interface View: MvpView {
        fun goHome()
    }

    interface Interactor : MvpInteractor{
        fun signIn(user: Usuario): Task<AuthResult>
        fun loggedIn() : FirebaseUser?
    }

    interface Presenter<V: LoginContract.View, I: LoginContract.Interactor>: MvpPresenter<V, I>{
        fun signIn(user: Usuario)
        fun loggedIn()
    }
}