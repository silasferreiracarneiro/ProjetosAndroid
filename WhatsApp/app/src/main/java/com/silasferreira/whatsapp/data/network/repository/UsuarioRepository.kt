package com.silasferreira.whatsapp.data.network.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.silasferreira.whatsapp.model.Usuario

interface UsuarioRepository {

    fun savedUser(user: Usuario): Task<AuthResult>
    fun signIn(user: Usuario): Task<AuthResult>
    fun loggedIn(): FirebaseUser?
    fun logout()
    fun createUserInDatabase(user: Usuario)
    fun deleteUserInAuthentication(): Task<Void>?
    fun savedImageUser(image: ByteArray)
    fun updateNameUser(name: String)
    fun searchUser(): DatabaseReference
    fun getListUser(): DatabaseReference
}