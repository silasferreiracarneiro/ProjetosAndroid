package com.silasferreira.whatsapp.model

class Conversation(var idRecipient: String,
                   var idSender: String,
                   var lastMessage: String,
                   var usuario: Usuario) {
}