package com.silasferreira.whatsapp.model

import java.io.Serializable

class Usuario(
    var uid: String = "",
    var nome: String = "",
    var email: String = "",
    var senha: String = "",
    var foto: String = "",
    var nameUser: String = ""
) : Serializable