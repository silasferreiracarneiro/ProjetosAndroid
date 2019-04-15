package com.example.trailermovie.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trailermovie.R
import com.example.trailermovie.ui.home.HomeActivity
import com.example.trailermovie.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtCadastre.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btnLogin.setOnClickListener{
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}
