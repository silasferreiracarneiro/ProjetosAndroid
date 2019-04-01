package com.example.instagram.ui.login

import com.example.instagram.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface LoginContract {

    interface View: MvpView{
        fun goToHome()
        fun setVisibleGoneProgress()
        fun setVisibleProgress()
    }

    interface Interactor: MvpInteractor{
        fun loggedIn(): FirebaseUser?
        fun signInUser(user: User): Task<AuthResult>
    }

    interface Presenter<V: LoginContract.View, I: LoginContract.Interactor>: MvpPresenter<V, I>{
        fun loggedIn()
        fun signInUser(user: User)
    }
}