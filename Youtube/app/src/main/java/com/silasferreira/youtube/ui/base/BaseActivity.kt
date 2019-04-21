package com.example.instagram.ui.base

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.silasferreira.whatsapp.ui.base.MvpView
import com.silasferreira.youtube.App
import com.silasferreira.youtube.R
import com.silasferreira.youtube.di.component.ActivityComponent
import com.silasferreira.youtube.di.component.DaggerActivityComponent
import com.silasferreira.youtube.di.module.ActivityModule
import com.silasferreira.youtube.utils.NetworkUtils

abstract class BaseActivity : AppCompatActivity(), MvpView{

    private lateinit var mActivityComponent: ActivityComponent

    private val awards = arrayListOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    private val REQUEST_PERMISSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mActivityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .applicationComponent((application as App).getComponent())
            .build()
    }

     override fun showMessage(message: String) {
         Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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

    fun getActivityComponent(): ActivityComponent {
        return mActivityComponent
    }

    override fun onGetString(id: Int): String {
        return getString(id)
    }

    override fun validatedPermissions() {
        var arrayRequest = arrayListOf<String>()

        awards.forEach{
            if(ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED){
                arrayRequest.add(it)
            }
        }

        if(arrayRequest.size > 0){
            val array = arrayOfNulls<String>(arrayRequest.size)
            ActivityCompat.requestPermissions(this, arrayRequest.toArray(array), REQUEST_PERMISSION)
        }
    }

    override fun alterPermission() {
        var builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.permissao_negada))
        builder.setMessage(getString(R.string.necessita_de_permissao))
        builder.setCancelable(false)
        builder.setPositiveButton(getString(R.string.confirma)){ _, _ ->
            finish()
        }
        builder.create().show()
    }
 }