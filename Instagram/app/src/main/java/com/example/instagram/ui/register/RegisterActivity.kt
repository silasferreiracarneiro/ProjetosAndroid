package com.example.instagram.ui.register

import android.os.Bundle
import com.example.instagram.R
import com.example.instagram.ui.base.BaseActivity
import javax.inject.Inject

class RegisterActivity : BaseActivity(), RegisterContract.View {

    @Inject lateinit var presenter: RegisterContract.Presenter<RegisterContract.View, RegisterContract.Interactor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        getActivityComponent().inject(this)
        presenter.onAttach(this)
    }
}
