package com.silasferreira.whatsapp.ui.home.contact

import com.google.firebase.database.DatabaseReference
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface ContactContract {
    interface View: MvpView {
        fun setNewListUser(list: ArrayList<Usuario>)
    }

    interface Presenter<V: ContactContract.View, I: ContactContract.Integractor> : MvpPresenter<V, I> {
        fun onViewPrepared()
    }

    interface Integractor : MvpInteractor {
        fun getListUser(): DatabaseReference
    }
}