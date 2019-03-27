package com.silasferreira.whatsapp.model

import java.io.Serializable


class Conversation(var idRecipient: String = "",
                   var idSender: String = "",
                   var lastMessage: String = "",
                   var usuario: Usuario = Usuario(),
                   var group: Group = Group(),
                   var groupConversation: Boolean = false) : Serializable {
}