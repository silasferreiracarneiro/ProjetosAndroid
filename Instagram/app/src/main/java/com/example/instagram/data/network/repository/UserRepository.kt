package com.example.instagram.data.network.repository

import com.example.instagram.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query
import com.google.firebase.storage.UploadTask

interface UserRepository {
    fun createUserInAuthentication(user: User): Task<AuthResult>
    fun createUser(user: User)
    fun uploadImageUser(photo: ByteArray): UploadTask
    fun loggedIn(): FirebaseUser?
    fun signInUser(user: User): Task<AuthResult>
    fun getUser(): DatabaseReference
    fun signOut()
    fun searchUser(caracter: String): Query
    fun getUserKey(email: String): DatabaseReference
}