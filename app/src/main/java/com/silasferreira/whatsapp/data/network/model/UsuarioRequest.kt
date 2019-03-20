package com.silasferreira.whatsapp.data.network.model

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.silasferreira.whatsapp.data.network.FirebaseModule.Companion.authFirebase
import com.silasferreira.whatsapp.data.network.repository.UsuarioRepository
import com.silasferreira.whatsapp.domain.Usuario

class UsuarioRequest: UsuarioRepository {

    private val auth: FirebaseAuth = authFirebase()

    override fun savedUser(user: Usuario): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(user.email, user.senha)
    }
}