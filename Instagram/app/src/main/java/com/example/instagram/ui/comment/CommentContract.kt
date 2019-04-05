package com.example.instagram.ui.comment

import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface CommentContract {
    interface View: MvpView{

    }

    interface Presenter<V: CommentContract.View, I: CommentContract.Interactor>: MvpPresenter<V, I>{

    }

    interface Interactor: MvpInteractor{

    }
}