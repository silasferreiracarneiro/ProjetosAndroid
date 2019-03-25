package com.silasferreira.whatsapp.data.network.model

import com.google.firebase.database.DatabaseReference
import com.silasferreira.whatsapp.data.network.FirebaseModule
import com.silasferreira.whatsapp.data.network.repository.MessageRepository
import com.silasferreira.whatsapp.model.MessageUser
import com.silasferreira.whatsapp.utils.AppConstants.Companion.PATH_MESSAGE

class MessageRequest : MessageRepository {

    private val reference: DatabaseReference = FirebaseModule.database()

    override fun sendMessage(message: MessageUser) {
         var ref = reference.child(PATH_MESSAGE)
            .child(message.senderUser)
            .child(message.recipientUser)
            .push()

        ref.setValue(message)
    }
}