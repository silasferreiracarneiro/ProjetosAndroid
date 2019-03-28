package com.example.instagram.ui.home

import com.example.instagram.ui.base.BasePresenter
import javax.inject.Inject

class HomePresenter<V: HomeContract.View, I: HomeContract.Interactor>
    @Inject constructor(var homeInteractor: I):
    BasePresenter<V, I>(homeInteractor), HomeContract.Presenter<V, I> {
}