package com.example.instagram.ui.friendprofile

import com.example.instagram.model.Follower
import com.example.instagram.model.User
import com.google.firebase.database.DatabaseReference
import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface FriendProfileContract {

    interface View: MvpView{
        fun setTextButtonFollowing(text: String, enableField: Boolean)
        fun setUserPae(user: User?)
    }

    interface Presenter<V: FriendProfileContract.View, I: FriendProfileContract.Interactor>: MvpPresenter<V, I>{
        fun following(user: User)
        fun savedFollower(follower: Follower)
        fun getUserKey(email: String)
    }

    interface Interactor: MvpInteractor{
        fun following(email: String): DatabaseReference?
        fun getUser(): DatabaseReference
        fun upadteUser(user: User)
        fun savedFollower(follower: Follower)
        fun getUserKey(email: String): DatabaseReference
    }
}