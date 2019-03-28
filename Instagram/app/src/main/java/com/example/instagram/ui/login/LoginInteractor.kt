package com.example.instagram.ui.login

import com.example.instagram.data.network.repository.UserRepository
import com.example.instagram.model.User
import com.example.instagram.ui.base.BaseInteractor
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

class LoginInteractor(var repositoyUser: UserRepository): BaseInteractor(), LoginContract.Interactor {

    override fun loggedIn(): FirebaseUser? {
        return repositoyUser.loggedIn()
    }

    override fun signInUser(user: User): Task<AuthResult> {
        return repositoyUser.signInUser(user)
    }
}