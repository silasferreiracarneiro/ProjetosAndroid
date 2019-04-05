package com.example.instagram.ui.comment

import com.example.instagram.data.network.repository.FeedRepository
import com.example.instagram.data.network.repository.UserRepository
import com.example.instagram.model.Comment
import com.example.instagram.ui.base.BaseInteractor
import com.google.firebase.database.DatabaseReference

class CommentInteractor(var feedRepository: FeedRepository,
                        var userRepository: UserRepository): BaseInteractor(), CommentContract.Interactor {

    override fun getAllComment(idPosting: String): DatabaseReference {
        return feedRepository.getAllComment(idPosting)
    }

    override fun getuser(email: String): DatabaseReference {
        return userRepository.getUserKey(email)
    }

    override fun savedComment(comment: Comment) {
        feedRepository.savedComment(comment)
    }
}