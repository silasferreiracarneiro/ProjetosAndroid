package com.example.instagram.ui.home.feed

import com.example.instagram.model.Feed
import com.example.instagram.ui.base.BasePresenter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.*
import javax.inject.Inject

class FeedPresenter<V: FeedContract.View, I: FeedContract.Interactor>
    @Inject constructor(var interactorFeed: I):
    BasePresenter<V, I>(interactorFeed), FeedContract.Presenter<V, I> {

    override fun saveFeed(item: Feed) {
        interactorFeed.saveFeed(item)
    }

    override fun getAllFeed() {

        interactorFeed.getAllFeed().addValueEventListener(object: ValueEventListener{

            override fun onDataChange(p0: DataSnapshot) {
                var feeds = arrayListOf<Feed>()

                p0.children.forEach {
                    var feed = it.getValue(Feed::class.java)
                    feeds.add(feed!!)
                }
                feeds.reverse()
                getMvpView().createFeed(feeds)
            }

            override fun onCancelled(p0: DatabaseError) {
                getMvpView().onError("Erro ao carregar os feeds")
            }
        })
    }
}