package com.example.instagram.ui.friendprofile

import com.example.instagram.ui.base.BasePresenter

class FriendProfilePresenter<V: FriendProfileContract.View, I: FriendProfileContract.Interactor>
    constructor(var interactorFriend: I):
    BasePresenter<V, I>(interactorFriend), FriendProfileContract.Presenter<V, I> {
}