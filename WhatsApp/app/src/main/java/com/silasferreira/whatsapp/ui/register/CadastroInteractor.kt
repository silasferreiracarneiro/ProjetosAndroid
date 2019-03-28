package com.silasferreira.whatsapp.ui.register

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.silasferreira.whatsapp.data.network.repository.UsuarioRepository
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.ui.base.BaseInteractor

class CadastroInteractor(val usuarioRepository: UsuarioRepository) : BaseInteractor(), CadastroContract.Interactor {

    override fun createUser(user: Usuario): Task<AuthResult> {
        return usuarioRepository.savedUser(user)
    }

    override fun savedUser(user: Usuario) {
        usuarioRepository.createUserInDatabase(user)
    }

    override fun deleteUserInAuthentication(): Task<Void>? {
        return usuarioRepository.deleteUserInAuthentication()
    }
}