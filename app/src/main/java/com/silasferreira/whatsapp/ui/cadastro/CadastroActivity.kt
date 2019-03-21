package com.silasferreira.whatsapp.ui.cadastro

import android.os.Bundle
import android.view.View
import com.silasferreira.whatsapp.App
import com.silasferreira.whatsapp.R
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.ui.base.BaseActivity
import com.silasferreira.whatsapp.utils.Base64Utils.encode
import kotlinx.android.synthetic.main.activity_cadastro.*
import javax.inject.Inject

class CadastroActivity : BaseActivity(), CadastroContract.View {

    @Inject
    lateinit var presenter : CadastroContract.Presenter<CadastroContract.View, CadastroContract.Interactor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        (application as App).getComponent().inject(this)
        presenter.onAttach(this)
    }

    fun createUser(view: View){

        var user = Usuario(
            encode(txtEmail?.text.toString()),
            txtNome?.text.toString(),
            txtEmail?.text.toString(),
            txtSenha?.text.toString()
        )

        this.presenter.createUser(user)
    }
}
