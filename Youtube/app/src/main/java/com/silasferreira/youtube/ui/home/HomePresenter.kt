package com.silasferreira.youtube.ui.home

import com.example.instagram.ui.base.BasePresenter
import kotlinx.coroutines.*
import javax.inject.Inject

class HomePresenter<V: HomeContract.View, I: HomeContract.Interactor>
    @Inject constructor(var interactorHome: I) :
    BasePresenter<V, I>(interactorHome), HomeContract.Presenter<V, I> {

    override fun getVideo(search: String) {

        CoroutineScope(Dispatchers.IO).launch {
            val video = interactorHome.getVideo(search)
            withContext(Dispatchers.Main) {
                getMvpView().setVideo(video?.items ?: emptyList())
            }
        }
    }
}