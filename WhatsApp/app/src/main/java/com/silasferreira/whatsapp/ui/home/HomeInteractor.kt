package com.silasferreira.whatsapp.ui.home

import com.silasferreira.whatsapp.data.network.repository.UsuarioRepository
import com.silasferreira.whatsapp.ui.base.BaseInteractor

class HomeInteractor(val repository: UsuarioRepository) : BaseInteractor(), HomeContract.Interactor {

    override fun logout() {
        repository.logout()
    }
}