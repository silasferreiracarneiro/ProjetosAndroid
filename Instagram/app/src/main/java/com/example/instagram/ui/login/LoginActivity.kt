package com.example.instagram.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.instagram.R
import com.example.instagram.model.User
import com.example.instagram.ui.base.BaseActivity
import com.example.instagram.ui.home.HomeActivity
import com.example.instagram.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginContract.View {

    @Inject lateinit var presenter : LoginContract.Presenter<LoginContract.View, LoginContract.Interactor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        getActivityComponent().inject(this)
        presenter.onAttach(this)

        txtRegister.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btnLogin.setOnClickListener{
            var user = User(
                "",
                edtEmail?.text.toString(),
                edtPassword?.text.toString()
            )
            presenter.signInUser(user)
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.loggedIn()
    }

    override fun goToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    override fun setVisibleGoneProgress() {
        progressBarLogin.visibility = View.GONE
    }

    override fun setVisibleProgress() {
        progressBarLogin.visibility = View.VISIBLE
    }
}
