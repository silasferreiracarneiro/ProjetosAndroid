package com.example.instagram.ui.comment

import com.example.instagram.data.network.repository.FeedRepository
import com.example.instagram.data.network.repository.UserRepository
import com.example.instagram.ui.base.BaseInteractor

class CommentInteractor(feedRepository: FeedRepository, userRepository: UserRepository): BaseInteractor(), CommentContract.Interactor {
}