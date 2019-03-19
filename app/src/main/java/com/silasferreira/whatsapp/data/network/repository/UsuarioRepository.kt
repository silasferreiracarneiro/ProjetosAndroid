package com.silasferreira.whatsapp.data.network.repository

import com.silasferreira.whatsapp.domain.Usuario

interface UsuarioRepository {

    fun savedUser(user: Usuario)
}