package com.silasferreira.whatsapp.ui.login

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.silasferreira.whatsapp.data.network.repository.UsuarioRepository
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.ui.base.BaseInteractor

class LoginInteractor(val usuarioRepository: UsuarioRepository): BaseInteractor(), LoginContract.Interactor {

    override fun signIn(user: Usuario): Task<AuthResult> {
        return usuarioRepository.signIn(user)
    }

    override fun loggedIn(): FirebaseUser? {
        return usuarioRepository.loggedIn()
    }
}