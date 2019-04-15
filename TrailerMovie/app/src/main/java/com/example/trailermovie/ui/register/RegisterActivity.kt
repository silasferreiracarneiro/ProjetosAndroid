package com.example.trailermovie.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trailermovie.R
import kotlinx.android.synthetic.main.toolbar_logo.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.novo_usuario)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}
