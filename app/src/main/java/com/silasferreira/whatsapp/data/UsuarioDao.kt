package com.silasferreira.whatsapp.data

import com.google.android.gms.tasks.OnCompleteListener
import com.silasferreira.whatsapp.data.dto.UsuarioDto
import com.silasferreira.whatsapp.di.FirebaseConfig
import com.silasferreira.whatsapp.ui.cadastro.CadastroActivity
import java.lang.Exception

class UsuarioDao {

    private val auth = FirebaseConfig.authFirebase()

    fun savedUser(user: UsuarioDto, activity: CadastroActivity): Exception? {

        var response = auth.createUserWithEmailAndPassword(user.email, user.senha).addOnCompleteListener(
            activity, OnCompleteListener { })

        return if (response.isSuccessful) null else response.exception!!
    }
}