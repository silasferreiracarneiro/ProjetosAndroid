package com.example.instagram.ui.base

import android.Manifest
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.instagram.App
import com.example.instagram.R
import com.example.instagram.di.component.ActivityComponent
import com.example.instagram.di.component.DaggerActivityComponent
import com.example.instagram.di.module.ActivityModule
import com.example.instagram.utils.CommonUtils
import com.example.instagram.utils.NetworkUtils
import com.silasferreira.whatsapp.ui.base.MvpView

abstract class BaseActivity : AppCompatActivity(), MvpView, BaseFragment.Callback{

    private var mProgressDialog: ProgressDialog? = null

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

    override fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog?.isShowing!!) {
            mProgressDialog?.cancel()
        }
    }

    override fun showLoading() {
        hideLoading()
        mProgressDialog = CommonUtils().showLoadingDialog(this)
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

     override fun onFragmentAttached() {

     }

     override fun onFragmentDetached(tag: String) {

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
        builder.setTitle("Permissões negadas")
        builder.setMessage("Para utilizar o app é necessário aceitar as permissões")
        builder.setCancelable(false)
        builder.setPositiveButton("Confirmar"){ dialog, which ->
            finish()
        }
        builder.create().show()
    }
 }