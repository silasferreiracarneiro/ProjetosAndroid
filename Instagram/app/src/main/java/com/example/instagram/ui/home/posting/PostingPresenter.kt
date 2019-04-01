package com.example.instagram.ui.home.posting

import com.example.instagram.ui.base.BasePresenter

class PostingPresenter<V: PostingContract.View, I: PostingContract.Interactor>
    constructor(repositoryPosting: I):
    BasePresenter<V, I>(repositoryPosting), PostingContract.Presenter<V, I> {
}