package com.silasferreira.whatsapp.data.network.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.silasferreira.whatsapp.domain.Usuario

interface UsuarioRepository {

    fun savedUser(user: Usuario): Task<AuthResult>
}