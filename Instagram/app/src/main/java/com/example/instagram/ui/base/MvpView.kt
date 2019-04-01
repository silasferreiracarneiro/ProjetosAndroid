package com.silasferreira.whatsapp.ui.base

import androidx.annotation.StringRes

interface MvpView {

    abstract fun showMessage(message: String)

    abstract fun isNetworkConnected(): Boolean

    abstract fun onError(@StringRes resId: Int)

    abstract fun onError(message: String)

    abstract fun onFinish()

    abstract fun onGetString(id: Int): String

    abstract fun alterPermission()

    abstract fun validatedPermissions()
}