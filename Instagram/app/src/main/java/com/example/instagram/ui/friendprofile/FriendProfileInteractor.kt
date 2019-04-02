package com.example.instagram.ui.friendprofile

import com.example.instagram.data.network.repository.FollowerRepository
import com.example.instagram.data.network.repository.UserRepository
import com.example.instagram.model.Follower
import com.example.instagram.model.User
import com.example.instagram.ui.base.BaseInteractor
import com.google.firebase.database.DatabaseReference

class FriendProfileInteractor(var repository: UserRepository, var followerRepository: FollowerRepository): BaseInteractor(), FriendProfileContract.Interactor {
    override fun getUserKey(email: String): DatabaseReference {
        return repository.getUserKey(email)
    }

    override fun upadteUser(user: User) {
        repository.createUser(user)
    }

    override fun getUser(): DatabaseReference {
        return repository.getUser()
    }

    override fun following(email: String): DatabaseReference? {
        return followerRepository.following(email)
    }

    override fun savedFollower(follower: Follower) {
        followerRepository.savedFollower(follower)
    }
}