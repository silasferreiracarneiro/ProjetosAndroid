package com.example.instagram.ui.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.instagram.di.component.ActivityComponent
import com.example.instagram.utils.NetworkUtils
import com.silasferreira.whatsapp.ui.base.MvpView

abstract class BaseFragment : Fragment(), MvpView {

    private var mActivity: BaseActivity? = null

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
}