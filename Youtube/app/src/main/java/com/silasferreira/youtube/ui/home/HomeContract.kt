package com.silasferreira.youtube.ui.home

import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface HomeContract {

    interface Interactor: MvpInteractor{

    }

    interface Presenter<V: HomeContract.View, I: HomeContract.Interactor> : MvpPresenter<V, I>{

    }

    interface View : MvpView{

    }
}