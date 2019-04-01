package com.example.instagram.ui.home.profile

import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface ProfileContract {

    interface View : MvpView{

    }

    interface Presenter<V: ProfileContract.View, I: ProfileContract.Interactor>: MvpPresenter<V, I>{

    }

    interface Interactor: MvpInteractor{

    }
}