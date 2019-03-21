package com.silasferreira.whatsapp.ui.cadastro

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface CadastroContract {

    interface Presenter<V : CadastroContract.View, I: CadastroContract.Interactor>: MvpPresenter<V, I> {
        fun createUser(user: Usuario)
        fun savedUser(user: Usuario)
        fun deleteUserInAuthentication()
    }

    interface View : MvpView

    interface Interactor: MvpInteractor {
        fun createUser(user: Usuario): Task<AuthResult>
        fun savedUser(user: Usuario)
        fun deleteUserInAuthentication(): Task<Void>?
    }
}