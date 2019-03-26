package com.silasferreira.whatsapp.data.network.repository

import com.google.firebase.database.DatabaseReference
import com.silasferreira.whatsapp.model.Conversation
import com.silasferreira.whatsapp.model.Group
import com.silasferreira.whatsapp.model.MessageUser

interface ConversationRepository {
    fun saveConversation(conversation: Conversation)
    fun sendMessage(message: MessageUser)
    fun getMessages(idRecipient: String, idSender: String): DatabaseReference
    fun getListConversation(): DatabaseReference
    fun savedGroup(group: Group): DatabaseReference
}
