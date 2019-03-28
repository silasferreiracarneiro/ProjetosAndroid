package com.silasferreira.whatsapp.ui.group

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.silasferreira.whatsapp.data.network.repository.UsuarioRepository
import com.silasferreira.whatsapp.ui.base.BaseInteractor

class GroupInteractor(var repositoryUsuario: UsuarioRepository): BaseInteractor(), GroupContract.Interactor {

    override fun getListUser(): DatabaseReference {
        return repositoryUsuario.getListUser()
    }

    override fun getCurrencyUser(): FirebaseUser? {
        return repositoryUsuario.loggedIn()
    }
}