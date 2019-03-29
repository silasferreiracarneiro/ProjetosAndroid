package com.example.instagram.ui.home

import com.example.instagram.data.network.repository.UserRepository
import com.example.instagram.ui.base.BaseContract

class HomeInteractor(var userRepository: UserRepository): BaseContract(), HomeContract.Interactor {

    override fun signOut() {
        userRepository.signOut()
    }
}