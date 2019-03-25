package com.silasferreira.whatsapp.ui.chat

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import com.silasferreira.whatsapp.model.MessageUser
import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface ChatContract {

    interface View : MvpView{

    }

    interface Interactor : MvpInteractor{
        fun sendMessage(message: MessageUser)
        fun getCurrencyUser(): FirebaseUser?
    }

    interface Presenter<V: ChatContract.View, I: ChatContract.Interactor>: MvpPresenter<V, I>{
        fun sendMessage(message: MessageUser)
    }
}