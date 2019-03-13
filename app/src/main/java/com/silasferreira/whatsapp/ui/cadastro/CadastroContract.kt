package com.silasferreira.whatsapp.ui.cadastro

import com.silasferreira.whatsapp.domain.Usuario

interface CadastroContract {

    interface Presenter{
        fun createUser(user: Usuario)
    }

    interface View{
        fun setMessageUser(text: String)
        fun getActivity(): CadastroActivity
        fun finishActivity()
    }
}