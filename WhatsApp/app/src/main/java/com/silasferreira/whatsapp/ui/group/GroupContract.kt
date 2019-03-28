package com.silasferreira.whatsapp.ui.group

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface GroupContract {

    interface View : MvpView{
        fun setNewListUser(listUser: ArrayList<Usuario>)
        fun setNewListUserSelect(listUser: ArrayList<Usuario>)
        fun updateToolbar(userSelect: Int, userTotal: Int)
    }

    interface Presenter<V: GroupContract.View, I: GroupContract.Interactor> : MvpPresenter<V, I>{
        fun getListUser()
        fun getUserSelect(position: Int): Usuario
        fun userSelect(position: Int)
        fun userDeselect(position: Int)
        fun updateToolbar()
        fun getListUserSelects(): ArrayList<Usuario>
    }

    interface Interactor: MvpInteractor{
        fun getListUser(): DatabaseReference
        fun getCurrencyUser(): FirebaseUser?
    }
}