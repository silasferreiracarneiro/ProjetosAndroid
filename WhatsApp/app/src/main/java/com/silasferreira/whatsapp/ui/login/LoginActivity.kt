package com.silasferreira.whatsapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.silasferreira.whatsapp.R
import com.silasferreira.whatsapp.model.Usuario
import com.silasferreira.whatsapp.ui.base.BaseActivity
import com.silasferreira.whatsapp.ui.register.CadastroActivity
import com.silasferreira.whatsapp.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginContract.View {

    @Inject
    lateinit var presenter: LoginContract.Presenter<LoginContract.View, LoginContract.Interactor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        getActivityComponent().inject(this)
        presenter.onAttach(this)
    }

    fun signIn(view: View) {
        var user = Usuario(
            "",
            "",
            edtEmail.text.toString(),
            edtSenha.text.toString()
        )
        this.presenter.signIn(user)
    }

    fun openAccount(view: View){
        startActivity(Intent(applicationContext, CadastroActivity::class.java))
    }

    override fun goHome() {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    override fun onStart() {
        super.onStart()
        presenter.loggedIn()
    }
}
