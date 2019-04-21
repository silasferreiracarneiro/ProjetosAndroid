package com.silasferreira.youtube.ui.home

import com.example.instagram.ui.base.BasePresenter
import javax.inject.Inject

class HomePresenter<V: HomeContract.View, I: HomeContract.Interactor>
    @Inject constructor(var interactorHome: I) :
    BasePresenter<V, I>(interactorHome), HomeContract.Presenter<V, I> {
}