package com.silasferreira.whatsapp.data.network.repository

import com.silasferreira.whatsapp.model.MessageUser

interface MessageRepository {
    fun sendMessage(message: MessageUser)
}
