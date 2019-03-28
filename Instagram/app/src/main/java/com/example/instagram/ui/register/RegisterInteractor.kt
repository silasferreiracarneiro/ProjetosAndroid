package com.example.instagram.ui.register

import com.example.instagram.data.network.repository.UserRepository
import com.example.instagram.model.User
import com.example.instagram.ui.base.BaseInteractor
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class RegisterInteractor(var repository: UserRepository) : BaseInteractor(), RegisterContract.Interactor {

    override fun createUserInAuthentication(user: User): Task<AuthResult> {
        return repository.createUserInAuthentication(user)
    }

    override fun createUser(user: User) {
        repository.createUser(user)
    }
}