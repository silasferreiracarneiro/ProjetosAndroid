package com.example.instagram.ui.friendprofile

import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface FriendProfileContract {

    interface View: MvpView{

    }

    interface Presenter<V: FriendProfileContract.View, I: FriendProfileContract.Interactor>: MvpPresenter<V, I>{

    }

    interface Interactor: MvpInteractor{

    }
}