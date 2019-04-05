package com.example.instagram.ui.comment

import android.os.Bundle
import com.example.instagram.R
import com.example.instagram.model.Comment
import com.example.instagram.ui.base.BaseActivity
import com.example.instagram.utils.AppConstants.Companion.ID_POSTING
import kotlinx.android.synthetic.main.activity_comment.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class CommentActivity : BaseActivity(), CommentContract.View {

    @Inject lateinit var presenter: CommentContract.Presenter<CommentContract.View, CommentContract.Interactor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        getActivityComponent().inject(this)
        presenter.onAttach(this)

        var idPosting = intent.extras.get(ID_POSTING) as String

        setSupportActionBar(toolbarHome)
        supportActionBar?.title = getString(R.string.comments)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp)

        btnSendComment.setOnClickListener{
            var comment = Comment(
                idPosting,
                edtMessageComment.text.toString()
            )

            presenter.savedComment(comment)
        }
    }
}
