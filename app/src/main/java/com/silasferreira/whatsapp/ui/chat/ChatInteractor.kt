package com.silasferreira.whatsapp.ui.chat

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.silasferreira.whatsapp.data.network.repository.ConversationRepository
import com.silasferreira.whatsapp.data.network.repository.UsuarioRepository
import com.silasferreira.whatsapp.model.Conversation
import com.silasferreira.whatsapp.model.MessageUser
import com.silasferreira.whatsapp.ui.base.BaseInteractor

class ChatInteractor(var repositoryConversation : ConversationRepository,
                     var repositoryUsuario: UsuarioRepository) : BaseInteractor(), ChatContract.Interactor {

    override fun sendMessage(message: MessageUser) {
        repositoryConversation.sendMessage(message)
    }

    override fun getCurrencyUser(): FirebaseUser? {
        return repositoryUsuario.loggedIn()
    }

    override fun getMessages(idRecipient: String, idSender: String): DatabaseReference {
        return repositoryConversation.getMessages(idRecipient, idSender)
    }

    override fun saveConversation(conversation: Conversation) {
        repositoryConversation.saveConversation(conversation)
    }
}