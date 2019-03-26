package com.silasferreira.whatsapp.ui.registergroup

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.silasferreira.whatsapp.model.Group
import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface RegisterGroupContract {

    interface View: MvpView{
    }

    interface Interactor: MvpInteractor{
        fun savedGroup(group: Group): DatabaseReference
        fun getUser(): DatabaseReference
    }

    interface Presenter<V: RegisterGroupContract.View, I: RegisterGroupContract.Interactor> : MvpPresenter<V, I>{
        fun savedGroup(group: Group)
    }
}