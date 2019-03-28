package com.example.instagram.data.network.repository

import com.example.instagram.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface UserRepository {
    fun createUserInAuthentication(user: User): Task<AuthResult>
    fun createUser(user: User)
}