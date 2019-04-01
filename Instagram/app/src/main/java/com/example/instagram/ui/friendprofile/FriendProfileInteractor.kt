package com.example.instagram.ui.friendprofile

import com.example.instagram.data.network.repository.UserRepository
import com.example.instagram.ui.base.BaseInteractor

class FriendProfileInteractor(var repository: UserRepository): BaseInteractor(), FriendProfileContract.Interactor {
}