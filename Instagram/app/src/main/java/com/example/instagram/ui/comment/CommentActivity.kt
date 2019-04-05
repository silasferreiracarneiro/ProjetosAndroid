package com.example.instagram.ui.comment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.instagram.R
import kotlinx.android.synthetic.main.toolbar.*

class CommentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        setSupportActionBar(toolbarHome)
        supportActionBar?.title = getString(R.string.comments)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp)
    }
}
