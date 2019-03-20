package com.silasferreira.whatsapp.ui.home

import com.silasferreira.whatsapp.ui.base.BasePresenter
import javax.inject.Inject

class HomePresenter<V: HomeContract.View, I: HomeContract.Interactor>
    @Inject constructor(var homeInteractor: I):
    BasePresenter<V, I>(homeInteractor), HomeContract.Presenter<V, I>{

    override fun logout() {
        homeInteractor.logout()
        getMvpView().onFinish()
    }
}