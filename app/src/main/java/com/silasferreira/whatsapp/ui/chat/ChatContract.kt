package com.silasferreira.whatsapp.ui.chat

import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface ChatContract {

    interface View : MvpView{

    }

    interface Interactor : MvpInteractor{

    }

    interface Presenter<V: ChatContract.View, I: ChatContract.Interactor>: MvpPresenter<V, I>{

    }
}