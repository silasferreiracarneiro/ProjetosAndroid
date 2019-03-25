package com.silasferreira.whatsapp.data.network.model

import com.google.firebase.database.DatabaseReference
import com.silasferreira.whatsapp.data.network.FirebaseModule
import com.silasferreira.whatsapp.data.network.repository.ConversationRepository
import com.silasferreira.whatsapp.model.Conversation
import com.silasferreira.whatsapp.model.MessageUser
import com.silasferreira.whatsapp.utils.AppConstants.Companion.PATH_CONVERSATION
import com.silasferreira.whatsapp.utils.AppConstants.Companion.PATH_MESSAGE

class ConversationRequest : ConversationRepository {

    private val reference: DatabaseReference = FirebaseModule.database()

    override fun sendMessage(message: MessageUser) {
        sendSender(message)
        sendRecipient(message)
    }

    override fun getMessages(idRecipient: String, idSender: String): DatabaseReference {

        return reference.child(PATH_MESSAGE)
                        .child(idSender)
                        .child(idRecipient)
    }

    private fun sendRecipient(message: MessageUser){
        var ref = reference.child(PATH_MESSAGE)
            .child(message.recipientUser)
            .child(message.senderUser)
            .push()

        ref.setValue(message)
    }

    private fun sendSender(message: MessageUser){
        var refSender = reference.child(PATH_MESSAGE)
            .child(message.senderUser)
            .child(message.recipientUser)
            .push()

        refSender.setValue(message)
    }

    override fun saveConversation(conversation: Conversation) {
        var refSender = reference.child(PATH_CONVERSATION)
            .child(conversation.idRecipient)
            .child(conversation.idSender)

        refSender.setValue(conversation)
    }

    override fun getListConversation(): DatabaseReference {
        return reference.child(PATH_CONVERSATION)
    }
}