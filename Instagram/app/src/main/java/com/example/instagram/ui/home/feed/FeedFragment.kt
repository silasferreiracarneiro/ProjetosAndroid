package com.example.instagram.ui.home.feed


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.instagram.R
import com.example.instagram.model.Feed
import com.example.instagram.ui.base.BaseFragment
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import kotlinx.android.synthetic.main.fragment_feed.*
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
        presenter.getAllFeed()
    }

    override fun createFeed(list: ArrayList<Feed>) {
        recyclerFeed.layoutManager = LinearLayoutManager(context)
        recyclerFeed.adapter = FeedAdapter(list, presenter)
        recyclerFeed.adapter?.notifyDataSetChanged()
    }
}
