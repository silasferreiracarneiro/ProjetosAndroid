package com.silasferreira.whatsapp.ui.base

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.silasferreira.whatsapp.App
import com.silasferreira.whatsapp.R
import com.silasferreira.whatsapp.utils.NetworkUtils

abstract class BaseActivity : AppCompatActivity(), MvpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as App).getComponent().inject(this)
    }

    override fun showMessage(message: String) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(R.string.some_error), Toast.LENGTH_SHORT).show()
        }
    }

    override fun isNetworkConnected(): Boolean {
        return NetworkUtils.isNetworkConnected(applicationContext)
    }

    override fun onError(resId: Int) {
        this.onError(getString(resId))
    }

    override fun onError(message: String) {
        this.showMessage(message)
    }

    override fun onFinish() {
        this.finish()
    }
}