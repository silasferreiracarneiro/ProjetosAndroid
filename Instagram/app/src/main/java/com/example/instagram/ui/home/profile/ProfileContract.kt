package com.example.instagram.ui.home.profile

import com.example.instagram.model.Posting
import com.example.instagram.model.User
import com.google.firebase.database.DatabaseReference
import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface ProfileContract {

    interface View : MvpView{
        fun setUser(user: User)
        fun setPosting(listPosting: ArrayList<Posting>)
    }

    interface Presenter<V: ProfileContract.View, I: ProfileContract.Interactor>: MvpPresenter<V, I>{
        fun getUser()
        fun loadPosting()
    }

    interface Interactor: MvpInteractor{
        fun getUser(): DatabaseReference
        fun loadPosting(identity: String): DatabaseReference
    }
}