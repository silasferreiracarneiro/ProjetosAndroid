package com.example.instagram.ui.base

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.instagram.di.component.ActivityComponent
import com.example.instagram.utils.NetworkUtils
import com.silasferreira.whatsapp.ui.base.MvpView

abstract class BaseFragment : Fragment(), MvpView {

    private var mActivity: BaseActivity? = null
    private val awards = arrayListOf(Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA, Manifest.permission.INTERNET)

    private val REQUEST_PERMISSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp(view)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            this.mActivity = context
            context.onFragmentAttached()
        }
    }

    override fun isNetworkConnected(): Boolean {
        return NetworkUtils.isNetworkConnected(context!!)
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    override fun showMessage(message: String) {
        if (mActivity != null) {
            mActivity?.showMessage(message)
        }
    }

    override fun onError(resId: Int) {
        if(mActivity != null){
            mActivity?.onError(resId)
        }
    }

    override fun onError(message: String) {
        if(mActivity != null){
            mActivity?.onError(message)
        }
    }

    override fun onFinish() {
        if(mActivity != null){
            mActivity?.onFinish()
        }
    }

    fun getActivityComponent(): ActivityComponent? {
        return if (mActivity != null) {
            mActivity?.getActivityComponent()
        } else null
    }

    fun getBaseActivity(): BaseActivity {
        return mActivity!!
    }

    fun onFragmentAttached() {

    }

    fun onFragmentDetached(tag: String) {

    }

    protected abstract fun setUp(view: View)

    interface Callback {

        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }

    override fun onGetString(id: Int): String {
        return getString(id)
    }

    override fun validatedPermissions() {
        var arrayRequest = arrayListOf<String>()

        awards.forEach{
            if(ContextCompat.checkSelfPermission(context!!, it) != PackageManager.PERMISSION_GRANTED){
                arrayRequest.add(it)
            }
        }

        if(arrayRequest.size > 0){
            val array = arrayOfNulls<String>(arrayRequest.size)
            ActivityCompat.requestPermissions(activity!!, arrayRequest.toArray(array), REQUEST_PERMISSION)
        }
    }

    override fun alterPermission() {
        var builder = AlertDialog.Builder(context!!)
        builder.setTitle("Permissões negadas")
        builder.setMessage("Para utilizar o app é necessário aceitar as permissões")
        builder.setCancelable(false)
        builder.setPositiveButton("Confirmar"){ dialog, which ->
            onFinish()
        }
        builder.create().show()
    }
}