package com.example.instagram.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.instagram.App
import com.example.instagram.R
import com.example.instagram.di.component.ActivityComponent
import com.example.instagram.di.component.DaggerActivityComponent
import com.example.instagram.di.module.ActivityModule
import com.example.instagram.utils.NetworkUtils
import com.silasferreira.whatsapp.ui.base.MvpView

abstract class BaseActivity : AppCompatActivity(), MvpView, BaseFragment.Callback{

    private lateinit var mActivityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mActivityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .applicationComponent((application as App).getComponent())
            .build()
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
         TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
     }

     override fun onFragmentDetached(tag: String) {
         TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
     }
    fun getActivityComponent(): ActivityComponent {
        return mActivityComponent
    }

    override fun onGetString(id: Int): String {
        return getString(id)
    }
 }