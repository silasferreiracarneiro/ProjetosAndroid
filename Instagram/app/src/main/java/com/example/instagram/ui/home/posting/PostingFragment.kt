package com.example.instagram.ui.home.posting


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.instagram.R
import com.example.instagram.ui.base.BaseFragment
import javax.inject.Inject

class PostingFragment : BaseFragment(), PostingContract.View {

    @Inject lateinit var presenter: PostingContract.Presenter<PostingContract.View, PostingContract.Interactor>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =     inflater.inflate(R.layout.fragment_posting, container, false)

        val component = getActivityComponent()

        if(component != null){
            component.inject(this)
            presenter.onAttach(this)
        }

        return view
    }

    override fun setUp(view: View) {

    }
}
