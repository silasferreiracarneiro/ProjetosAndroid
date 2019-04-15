package com.example.trailermovie.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trailermovie.R
import kotlinx.android.synthetic.main.toolbar.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}
