package com.example.instagram.ui.login

import com.example.instagram.data.network.repository.UserRepository
import com.example.instagram.ui.base.BaseInteractor
import com.google.firebase.auth.FirebaseUser

class LoginInteractor(var repositoyUser: UserRepository): BaseInteractor(), LoginContract.Interactor {

    override fun signIn(): FirebaseUser? {
        return repositoyUser.signIn()
    }
}