package com.example.instagram.ui.register

import com.example.instagram.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface RegisterContract {

    interface View: MvpView{
        fun setVisibleGoneProgress()
        fun setVisibleProgress()
    }

    interface Interactor: MvpInteractor {
        fun createUser(user: User)
        fun createUserInAuthentication(user: User): Task<AuthResult>
    }

    interface Presenter<V: RegisterContract.View, I: RegisterContract.Interactor>: MvpPresenter<V, I>{
        fun createUser(user: User)
    }

}