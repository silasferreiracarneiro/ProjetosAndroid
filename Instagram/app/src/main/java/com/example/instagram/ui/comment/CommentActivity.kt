package com.example.instagram.ui.comment

import android.os.Bundle
import com.example.instagram.R
import com.example.instagram.ui.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class CommentActivity : BaseActivity(), CommentContract.View {

    @Inject lateinit var presenter: CommentContract.Presenter<CommentContract.View, CommentContract.Interactor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        getActivityComponent().inject(this)
        presenter.onAttach(this)

        setSupportActionBar(toolbarHome)
        supportActionBar?.title = getString(R.string.comments)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp)


    }
}
