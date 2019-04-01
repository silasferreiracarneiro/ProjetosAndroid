package com.example.instagram.ui.home.feed


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.instagram.R
import com.example.instagram.ui.base.BaseFragment
import javax.inject.Inject

class FeedFragment : BaseFragment(), FeedContract.View {

    @Inject lateinit var presenter: FeedContract.Presenter<FeedContract.View, FeedContract.Interactor>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_feed, container, false)

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
