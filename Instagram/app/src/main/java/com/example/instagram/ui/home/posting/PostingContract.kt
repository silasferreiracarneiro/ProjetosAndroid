package com.example.instagram.ui.home.posting

import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface PostingContract {

    interface Presenter<V: PostingContract.View, I: PostingContract.Interactor>: MvpPresenter<V, I> {

    }

    interface View: MvpView{

    }

    interface Interactor: MvpInteractor{

    }
}