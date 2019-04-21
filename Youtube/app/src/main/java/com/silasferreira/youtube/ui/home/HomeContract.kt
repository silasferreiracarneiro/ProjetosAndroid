package com.silasferreira.youtube.ui.home

import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView
import com.silasferreira.youtube.data.network.model.VideoResponse
import com.silasferreira.youtube.model.Items

interface HomeContract {

    interface Interactor: MvpInteractor{
        suspend fun getVideo(search: String): VideoResponse?
    }

    interface Presenter<V: HomeContract.View, I: HomeContract.Interactor> : MvpPresenter<V, I>{
        fun getVideo(search: String)
    }

    interface View : MvpView{
        fun setVideo(items: ArrayList<Items>?)
    }
}