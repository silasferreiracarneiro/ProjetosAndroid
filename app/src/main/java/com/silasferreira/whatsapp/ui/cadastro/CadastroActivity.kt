package com.silasferreira.whatsapp.ui.cadastro

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.silasferreira.whatsapp.App
import com.silasferreira.whatsapp.R
import com.silasferreira.whatsapp.domain.Usuario
import com.silasferreira.whatsapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_cadastro.*
import javax.inject.Inject

class CadastroActivity : BaseActivity(), CadastroContract.View {

    @Inject
    lateinit var presenter : CadastroContract.Presenter<CadastroContract.View, CadastroContract.Interactor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        (application as App).getComponent().inject(this)
    }

    fun createUser(view: View){

        var user = Usuario(
            txtNome?.text.toString(),
            txtEmail?.text.toString(),
            txtSenha?.text.toString()
        )

        this.presenter.createUser(user)
    }

    override fun setMessageUser(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }

    override fun finishActivity() {
        this.finish()
    }
}
