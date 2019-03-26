package com.silasferreira.whatsapp.ui.registergroup

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.silasferreira.whatsapp.data.network.repository.ConversationRepository
import com.silasferreira.whatsapp.data.network.repository.UsuarioRepository
import com.silasferreira.whatsapp.model.Group
import com.silasferreira.whatsapp.ui.base.BaseInteractor

class RegisterGroupInteractor(var repository: ConversationRepository, var usuarioRepository: UsuarioRepository) : BaseInteractor(), RegisterGroupContract.Interactor {

    override fun savedGroup(group: Group): DatabaseReference {
        return repository.savedGroup(group)
    }

    override fun getUser(): DatabaseReference {
        return usuarioRepository.searchUser()
    }
}