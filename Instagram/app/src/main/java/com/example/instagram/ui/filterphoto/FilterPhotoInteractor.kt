package com.example.instagram.ui.filterphoto

import com.example.instagram.data.network.repository.FeedRepository
import com.example.instagram.data.network.repository.FollowerRepository
import com.example.instagram.data.network.repository.PostingRepository
import com.example.instagram.data.network.repository.UserRepository
import com.example.instagram.model.Feed
import com.example.instagram.model.Posting
import com.example.instagram.model.User
import com.example.instagram.ui.base.BaseInteractor
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.UploadTask

class FilterPhotoInteractor(var repository: PostingRepository,
                            var repositoryUser: UserRepository,
                            var repositoryFollowerRepository: FollowerRepository,
                            var feedRequest: FeedRepository): BaseInteractor(), FilterPhotoContract.Interactor {

    override fun savedFeed(feed: Feed, user: User) {
        feedRequest.savedFeed(feed, user)
    }

    override fun updateUser(user: User) {
        repositoryUser.createUser(user)
    }

    override fun getUser(): DatabaseReference {
        return repositoryUser.getUser()
    }

    override fun savedPhoto(photo: ByteArray?, identity: String): UploadTask {
        return repository.savedPhoto(photo, identity)
    }

    override fun publishPhoto(publish: Posting) {
        repository.publishPhoto(publish)
    }

    override fun getAllFollowing(idUser: String): DatabaseReference? {
        return repositoryFollowerRepository.getAllFollowing(idUser)
    }
}