package com.silasferreira.whatsapp.ui.home

import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface HomeContract {

    interface View : MvpView{

    }

    interface Interactor : MvpInteractor{
        fun logout()
    }

    interface Presenter<V: HomeContract.View, I: HomeContract.Interactor>: MvpPresenter<V, I>{
        fun logout()
        fun searchListConversation(text: String)
    }
}