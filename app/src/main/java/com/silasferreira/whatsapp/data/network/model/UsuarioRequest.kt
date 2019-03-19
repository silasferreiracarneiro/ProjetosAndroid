package com.silasferreira.whatsapp.data.network.model


import com.google.firebase.auth.FirebaseAuth
import com.silasferreira.whatsapp.data.network.FirebaseModule.Companion.authFirebase
import com.silasferreira.whatsapp.data.network.repository.UsuarioRepository
import com.silasferreira.whatsapp.domain.Usuario

class UsuarioRequest: UsuarioRepository {

    private val auth: FirebaseAuth = authFirebase()

    override fun savedUser(user: Usuario) {
        var response = auth.createUserWithEmailAndPassword(user.email, user.senha)
        //return if (response.isSuccessful) null else response.exception!!
    }
}