package com.example.instagram.ui.editprofile

import com.example.instagram.model.User
import com.google.firebase.database.DatabaseReference
import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface EditProfileContract {

    interface Presenter<V: EditProfileContract.View, I: EditProfileContract.Interactor> : MvpPresenter<V, I>{
        fun getUser()
        fun updateUser(user: User)
    }

    interface Interactor : MvpInteractor{
        fun getUser(): DatabaseReference
        fun updateUser(user: User)
    }

    interface View: MvpView{
        fun setInformationUser(user: User)
    }
}