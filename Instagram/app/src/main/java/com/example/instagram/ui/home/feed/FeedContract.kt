package com.example.instagram.ui.home.feed

import com.example.instagram.model.Feed
import com.google.firebase.database.DatabaseReference
import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface FeedContract {

    interface View: MvpView{
        fun createFeed(list: ArrayList<Feed>)
    }

    interface Presenter<V: FeedContract.View, I: FeedContract.Interactor>: MvpPresenter<V, I>{
        fun getAllFeed()
        fun saveFeed(item: Feed)
    }

    interface Interactor: MvpInteractor{
        fun getAllFeed() : DatabaseReference
        fun saveFeed(item: Feed)
    }
}