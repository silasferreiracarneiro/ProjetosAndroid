package com.silasferreira.youtube.ui.home

import com.example.instagram.ui.base.BaseInteractor
import com.silasferreira.youtube.data.network.VideoRepository

class HomeInteractor(var repository: VideoRepository) : BaseInteractor(), HomeContract.Interactor {
}