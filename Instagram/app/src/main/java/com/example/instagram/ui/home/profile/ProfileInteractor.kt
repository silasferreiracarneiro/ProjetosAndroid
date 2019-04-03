package com.example.instagram.ui.home.profile

import com.example.instagram.data.network.repository.PostingRepository
import com.example.instagram.data.network.repository.UserRepository
import com.example.instagram.ui.base.BaseInteractor
import com.google.firebase.database.DatabaseReference

class ProfileInteractor(var repository: UserRepository, var postingRepository: PostingRepository): BaseInteractor(), ProfileContract.Interactor {

    override fun getUser(): DatabaseReference {
        return repository.getUser()
    }

    override fun loadPosting(identity: String): DatabaseReference {
        return postingRepository.getAllPosting(identity)
    }
}