package com.example.instagram.ui.comment

import com.example.instagram.data.prefs.PreferencesHelper
import com.example.instagram.ui.base.BasePresenter
import javax.inject.Inject

class CommentPresenter<V: CommentContract.View, I: CommentContract.Interactor>
    @Inject constructor(var feedIterator: I, pref: PreferencesHelper):
    BasePresenter<V, I>(feedIterator), CommentContract.Presenter<V, I> {


}