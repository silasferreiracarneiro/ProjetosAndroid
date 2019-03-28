package com.silasferreira.whatsapp.model

import java.util.*

class MessageUser(
    var senderUser: String = "",
    var recipientUser: String = "",
    var message: String = "",
    var dataMessage: Date = Date(),
    var image: String = ""
) {
}