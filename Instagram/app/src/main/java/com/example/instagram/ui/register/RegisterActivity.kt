package com.example.instagram.ui.register

import android.os.Bundle
import android.view.View
import com.example.instagram.R
import com.example.instagram.model.User
import com.example.instagram.ui.base.BaseActivity
import com.example.instagram.utils.NetworkUtils
import kotlinx.android.synthetic.main.activity_register.*
import javax.inject.Inject

class RegisterActivity : BaseActivity(), RegisterContract.View {

    @Inject lateinit var presenter: RegisterContract.Presenter<RegisterContract.View, RegisterContract.Interactor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        getActivityComponent().inject(this)
        presenter.onAttach(this)

        btnRegister.setOnClickListener{
            var user = User(
                edtNameUsuario?.text.toString(),
                edtEmailUsuario?.text.toString(),
                edtPasswordUsuario?.text.toString(),
                edtNameUsuario?.text.toString(),
                ""
            )
            if(NetworkUtils.isNetworkConnected(this)){
                presenter.createUser(user)
            }else{
                onError(getString(R.string.no_internet))
            }
        }
    }

    override fun setVisibleGoneProgress() {
        progressBarRegister.visibility = View.GONE
    }

    override fun setVisibleProgress() {
        progressBarRegister.visibility = View.VISIBLE
    }
}
