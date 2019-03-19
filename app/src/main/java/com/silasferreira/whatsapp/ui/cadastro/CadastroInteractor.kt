package com.silasferreira.whatsapp.ui.cadastro

import com.silasferreira.whatsapp.data.network.repository.UsuarioRepository
import com.silasferreira.whatsapp.domain.Usuario
import com.silasferreira.whatsapp.ui.base.BaseInteractor

class CadastroInteractor(val usuarioRepository: UsuarioRepository) : BaseInteractor(), CadastroContract.Interactor {

    override fun createUser(user: Usuario) {
        usuarioRepository.savedUser(user)
    }
}