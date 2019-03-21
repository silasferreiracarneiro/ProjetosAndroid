package com.silasferreira.whatsapp.ui.setting

import com.google.firebase.storage.UploadTask
import com.silasferreira.whatsapp.data.network.repository.UsuarioRepository
import com.silasferreira.whatsapp.data.prefs.PreferencesHelper
import com.silasferreira.whatsapp.ui.base.BaseInteractor

class SettingInteractor(var usuarioRepository: UsuarioRepository, var preferenceHelper: PreferencesHelper): BaseInteractor(), SettingContract.Interactor {

    override fun uploadImage(image: ByteArray): UploadTask {
        return this.usuarioRepository.savedImageUser(preferenceHelper.getUserId(), image)
    }
}