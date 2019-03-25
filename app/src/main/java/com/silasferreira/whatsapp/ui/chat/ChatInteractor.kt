package com.silasferreira.whatsapp.ui.chat

import com.google.firebase.auth.FirebaseUser
import com.silasferreira.whatsapp.data.network.repository.MessageRepository
import com.silasferreira.whatsapp.data.network.repository.UsuarioRepository
import com.silasferreira.whatsapp.model.MessageUser
import com.silasferreira.whatsapp.ui.base.BaseInteractor

class ChatInteractor(var repositoryMessage : MessageRepository,
                     var repositoryUsuario: UsuarioRepository) : BaseInteractor(), ChatContract.Interactor {

    override fun sendMessage(message: MessageUser) {
        repositoryMessage.sendMessage(message)
    }

    override fun getCurrencyUser(): FirebaseUser? {
        return repositoryUsuario.loggedIn()
    }
}