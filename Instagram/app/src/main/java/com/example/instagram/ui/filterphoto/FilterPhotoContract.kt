package com.example.instagram.ui.filterphoto

import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface FilterPhotoContract {

    interface View: MvpView {

    }

    interface Presenter<V: FilterPhotoContract.View, I: FilterPhotoContract.Interactor>: MvpPresenter<V, I>{

    }

    interface Interactor: MvpInteractor{

    }
}