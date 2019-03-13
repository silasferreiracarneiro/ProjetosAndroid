package com.silasferreira.whatsapp.domain

import com.silasferreira.whatsapp.data.dto.UsuarioDto

class Usuario(
    var nome: String,
    var email: String,
    var senha: String
) {

    fun toDto() : UsuarioDto{
        return UsuarioDto(this.nome, this.email, this.senha)
    }
}