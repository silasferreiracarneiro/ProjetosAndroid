package com.silasferreira.whatsapp.model

import com.google.firebase.database.Exclude

class Usuario(
    @Exclude var uid: String,
    var nome: String,
    var email: String,
    @Exclude var senha: String
)