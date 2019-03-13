package com.silasferreira.whatsapp.ui.cadastro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.silasferreira.whatsapp.R
import com.silasferreira.whatsapp.domain.Usuario
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity(), CadastroContract.View {

    private lateinit var presenter : CadastroPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        presenter = CadastroPresenter(this)
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

    override fun getActivity(): CadastroActivity {
        return this
    }

    override fun finishActivity() {
        this.finish()
    }
}