package com.silasferreira.whatsapp.ui.setting

import com.google.firebase.database.DatabaseReference
import com.silasferreira.whatsapp.data.network.repository.UsuarioRepository
import com.silasferreira.whatsapp.ui.base.BaseInteractor

class SettingInteractor(var usuarioRepository: UsuarioRepository): BaseInteractor(), SettingContract.Interactor {

    override fun uploadImage(image: ByteArray) {
        this.usuarioRepository.savedImageUser(image)
    }

    override fun searchUserPhoto(): DatabaseReference {
        return this.usuarioRepository.searchUserPhoto()
    }
}