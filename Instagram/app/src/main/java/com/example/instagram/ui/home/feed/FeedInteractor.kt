package com.example.instagram.ui.home.feed

import com.example.instagram.data.network.repository.FeedRepository
import com.example.instagram.data.network.repository.UserRepository
import com.example.instagram.model.Feed
import com.example.instagram.ui.base.BaseInteractor
import com.google.firebase.database.DatabaseReference

class FeedInteractor(repository: UserRepository, var feedRepository: FeedRepository): BaseInteractor(), FeedContract.Interactor {

    override fun saveFeed(item: Feed) {
        feedRepository.updateFeed(item)
    }

    override fun getAllFeed(): DatabaseReference{
        return feedRepository.gelAllFeed()
    }
}