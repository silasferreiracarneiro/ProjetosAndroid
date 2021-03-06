package com.example.instagram.ui.base

import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView
import com.silasferreira.youtube.R
import java.lang.Exception

open class BasePresenter<V : MvpView, I : MvpInteractor>(val interactor: I): MvpPresenter<V, I> {

    private lateinit var view: V

    override fun getMvpView(): V {
        return view
    }

    override fun getMvpInteractor(): I {
        return interactor
    }

    override fun onAttach(mvpView: V) {
        this.view = mvpView
    }

    override fun isViewAttached(): Boolean {
        return this.view != null
    }

    override fun checkViewAttached() {
        if (!isViewAttached()) throw MvpViewNotAttachedException()
    }

    override fun handleApiError(error: Exception) {
        getMvpView().onError(R.string.some_error)
        return
    }

    class MvpViewNotAttachedException :
        RuntimeException("Please call Presenter.onAttach(MvpView) before" + " requesting data to the Presenter")

}