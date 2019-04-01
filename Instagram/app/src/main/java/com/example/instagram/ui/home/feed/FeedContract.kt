package com.example.instagram.ui.home.feed

import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface FeedContract {

    interface View: MvpView{

    }

    interface Presenter<V: FeedContract.View, I: FeedContract.Interactor>: MvpPresenter<V, I>{

    }

    interface Interactor: MvpInteractor{

    }
}