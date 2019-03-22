package com.silasferreira.whatsapp.ui.home.conversation

import com.google.firebase.database.DatabaseReference
import com.silasferreira.whatsapp.data.network.repository.UsuarioRepository

class ConversationInteractor(var repository: UsuarioRepository): ConversationContract.Integractor {

    override fun getListUser(): DatabaseReference {
        return repository.getListUser()
    }
}