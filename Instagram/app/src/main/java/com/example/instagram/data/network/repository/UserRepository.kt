package com.example.instagram.data.network.repository

import com.example.instagram.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface UserRepository {
    fun createUserInAuthentication(user: User): Task<AuthResult>
    fun createUser(user: User)
    fun loggedIn(): FirebaseUser?
    fun signInUser(user: User): Task<AuthResult>
    fun signOut()
}