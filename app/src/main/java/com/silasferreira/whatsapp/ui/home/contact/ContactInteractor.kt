package com.silasferreira.whatsapp.ui.home.contact

import com.google.firebase.database.DatabaseReference
import com.silasferreira.whatsapp.data.network.repository.UsuarioRepository

class ContactInteractor(var repository: UsuarioRepository): ContactContract.Integractor {

    override fun getListUser(): DatabaseReference {
        return repository.getListUser()
    }
}