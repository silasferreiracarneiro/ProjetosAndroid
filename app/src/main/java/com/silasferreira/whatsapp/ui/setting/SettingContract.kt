package com.silasferreira.whatsapp.ui.setting

import android.graphics.Bitmap
import com.google.firebase.database.DatabaseReference
import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface SettingContract {

    interface Presenter<V: SettingContract.View, I: SettingContract.Interactor>: MvpPresenter<V, I>{
        fun uploadImage(image: ByteArray)
        fun updateNameUser(name: String)
        fun searchUser()
    }

    interface View: MvpView{
        fun setImageUser(map: Bitmap)
        fun setNameUser(name: String)
    }

    interface Interactor: MvpInteractor{
        fun uploadImage(image: ByteArray)
        fun updateNameUser(name: String)
        fun searchUser(): DatabaseReference
    }
}