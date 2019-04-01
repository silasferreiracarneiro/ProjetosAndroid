package com.example.instagram.ui.home.feed

import com.example.instagram.ui.base.BasePresenter
import javax.inject.Inject

class FeedPresenter<V: FeedContract.View, I: FeedContract.Interactor>
    @Inject constructor(var interactorFeed: I):
    BasePresenter<V, I>(interactorFeed), FeedContract.Presenter<V, I> {
}