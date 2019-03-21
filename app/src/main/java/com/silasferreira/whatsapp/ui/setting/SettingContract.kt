package com.silasferreira.whatsapp.ui.setting

import com.google.firebase.storage.UploadTask
import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface SettingContract {

    interface Presenter<V: SettingContract.View, I: SettingContract.Interactor>: MvpPresenter<V, I>{
        fun uploadImage(image: ByteArray)
    }

    interface View: MvpView{
        fun setImageUser()
    }

    interface Interactor: MvpInteractor{
        fun uploadImage(image: ByteArray): UploadTask
    }
}