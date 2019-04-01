package com.example.instagram.ui.home.search

import com.example.instagram.model.User
import com.google.firebase.database.Query
import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface SearchContract {

    interface View: MvpView{
        fun setListUser(list: ArrayList<User>)
    }

    interface Interactor: MvpInteractor{
        fun searchUser(caracter: String): Query
    }

    interface Presenter<V: SearchContract.View, I: SearchContract.Interactor>: MvpPresenter<V, I>{
        fun searchUser(caracter: String?)
    }
}