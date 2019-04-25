package com.silasferreira.youtube.ui.home

import com.example.instagram.ui.base.BaseInteractor
import com.silasferreira.youtube.data.network.VideoRepository
import com.silasferreira.youtube.data.network.model.VideoResponse

class HomeInteractor (
    var repository: VideoRepository
) : BaseInteractor(), HomeContract.Interactor {

    override suspend fun getVideo(search: String): VideoResponse? {
        return  repository.getVideo(search)
    }
}