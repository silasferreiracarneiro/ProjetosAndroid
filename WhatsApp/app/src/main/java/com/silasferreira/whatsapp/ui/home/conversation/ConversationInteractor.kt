package com.silasferreira.whatsapp.ui.home.conversation

import com.google.firebase.database.DatabaseReference
import com.silasferreira.whatsapp.data.network.repository.ConversationRepository
import com.silasferreira.whatsapp.data.network.repository.UsuarioRepository

class ConversationInteractor(var repository: ConversationRepository): ConversationContract.Integractor {

    override fun getListConversation(): DatabaseReference {
        return repository.getListConversation()
    }
}