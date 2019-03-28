package com.example.instagram.ui.register

import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface RegisterContract {

    interface View: MvpView
    interface Interactor: MvpInteractor
    interface Presenter<V: RegisterContract.View, I: RegisterContract.Interactor>: MvpPresenter<V, I>
}