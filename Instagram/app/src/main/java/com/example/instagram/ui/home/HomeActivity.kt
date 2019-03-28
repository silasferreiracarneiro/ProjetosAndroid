package com.example.instagram.ui.home

import android.os.Bundle
import com.example.instagram.R
import com.example.instagram.ui.base.BaseActivity
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeContract.View {

    @Inject lateinit var presenter: HomePresenter<HomeContract.View, HomeContract.Interactor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        getActivityComponent().inject(this)
        presenter.onAttach(this)
    }
}
